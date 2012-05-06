package payrollcasestudy.transactions;

import org.junit.Rule;
import org.junit.Test;
import payrollcasestudy.DatabaseResource;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;
import payrollcasestudy.transactions.add.AddSalariedEmployeeTransaction;
import payrollcasestudy.transactions.add.AddTimeCardTransaction;
import payrollcasestudy.transactions.change.ChangeMemberTransaction;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.util.Calendar.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static payrollcasestudy.TestConstants.*;

public class PaydayTransactionTest {

    @Rule
    public DatabaseResource databaseResource = new DatabaseResource();
    private final GregorianCalendar THURSDAY = new GregorianCalendar(2001, 10, 8);
    private final GregorianCalendar FRIDAY = new GregorianCalendar(2001, 10, 9);

    @Test
    public void testSingleSalariedEmployee() throws Exception {
        int employeeId = 1;
        Transaction addSalariedEmployeeTransaction = new AddSalariedEmployeeTransaction(employeeId,"Bob", "Home", 1000.0);
        addSalariedEmployeeTransaction.execute();

        Calendar payDate = new GregorianCalendar(2001, NOVEMBER, 30);
        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();

        PayCheck payCheck = paydayTransaction.getPaycheck(employeeId);
        assertThat(payCheck.getDate(), is(payDate));
        assertThat(payCheck.getGrossPay(), is(closeTo(1000.0, FLOAT_ACCURACY)));
        assertThat(payCheck.getField("Disposition"), is("Hold"));
        assertThat(payCheck.getDeductions(), is(closeTo(0, FLOAT_ACCURACY)));
        assertThat(payCheck.getNetPay(), is(closeTo(1000.0, FLOAT_ACCURACY)));
    }

    @Test
    public void testSingleSalariedEmployeeWrongDate() throws Exception {
        int empId = 1;
        Transaction addEmployeeTransaction = new AddSalariedEmployeeTransaction(empId, "Bob", "Home", 1000.0);
        addEmployeeTransaction.execute();

        Calendar payDate = new GregorianCalendar(2001, NOVEMBER, 29);
        PaydayTransaction payDayTransaction = new PaydayTransaction(payDate);
        payDayTransaction.execute();

        assertThat(payDayTransaction.getPaycheck(empId), is(nullValue()));
    }

    @Test
    public void testHourlyEmployeeSingleHourlyEmployeeNoTimeCards() throws Exception {
        int employeeId = 2;
        Transaction addEmployeeTransaction = new AddHourlyEmployeeTransaction(employeeId, "Bill", "Home", 15.25);
        addEmployeeTransaction.execute();
        Calendar payDate = FRIDAY;
        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);

        paydayTransaction.execute();

        validateHourlyPaycheck(paydayTransaction, employeeId, payDate, 0.0);
    }

    @Test
    public void testPaySingleHourlyEmployeeOneTimeCard() throws Exception {
        int employeeId = 2;
        Transaction addEmployeeTransaction = new AddHourlyEmployeeTransaction(employeeId, "Bill", "Home", 15.25);
        addEmployeeTransaction.execute();
        Calendar payDate = FRIDAY;

        Transaction addTimeCard = new AddTimeCardTransaction(payDate, 2.0, employeeId);
        addTimeCard.execute();

        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();

        validateHourlyPaycheck(paydayTransaction, employeeId, payDate, 30.5);
    }

    @Test
    public void testPaySingleHourlyEmployeeOvertimeOneTimeCard() throws Exception {
        int employeeId = 2;
        double hourlySalary = 15.25;
        Transaction addEmployeeTransaction = new AddHourlyEmployeeTransaction(employeeId, "Bill", "Home", hourlySalary);
        addEmployeeTransaction.execute();
        Calendar payDate = FRIDAY;

        Transaction addTimeCard = new AddTimeCardTransaction(payDate, 9.0, employeeId);
        addTimeCard.execute();

        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();
        validateHourlyPaycheck(paydayTransaction, employeeId, payDate, (8 + 1.5) * hourlySalary);
    }

    @Test
    public void testPaySingleHourlyEmployeeOnWrongDate() throws Exception {
        int employeeId = 2;
        double hourlySalary = 15.25;
        Transaction addEmployeeTransaction = new AddHourlyEmployeeTransaction(employeeId, "Bill", "Home", hourlySalary);
        addEmployeeTransaction.execute();
        Calendar payDate = THURSDAY;

        Transaction addTimeCard = new AddTimeCardTransaction(payDate, 9.0, employeeId);
        addTimeCard.execute();

        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();

        assertThat(paydayTransaction.getPaycheck(employeeId), is(nullValue()));
    }

    @Test
    public void testPaySingleHourlyEmployeeTwoTimeCards() throws Exception {
        int employeeId = 2;
        double hourlySalary = 15.25;
        Transaction addEmployeeTransaction = new AddHourlyEmployeeTransaction(employeeId, "Bill", "Home", hourlySalary);
        addEmployeeTransaction.execute();
        Calendar payDate = FRIDAY;

        Transaction addTimeCard = new AddTimeCardTransaction(payDate, 2, employeeId);
        addTimeCard.execute();

        Transaction addSecondTimeCard = new AddTimeCardTransaction(THURSDAY, 5, employeeId);
        addSecondTimeCard.execute();

        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();

        validateHourlyPaycheck(paydayTransaction, employeeId, payDate, 7.0 * hourlySalary);
    }

    @Test
    public void testPaySingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriods() throws Exception {
        int employeeId = 2;
        double hourlySalary = 15.25;
        Transaction addEmployeeTransaction = new AddHourlyEmployeeTransaction(employeeId, "Bill", "Home", hourlySalary);
        addEmployeeTransaction.execute();
        Calendar payDate = FRIDAY;

        Transaction addTimeCard = new AddTimeCardTransaction(payDate, 2, employeeId);
        addTimeCard.execute();

        Calendar dateInPreviousTimePeriod = new GregorianCalendar(2001, NOVEMBER, 2);
        Transaction addSecondTimeCard = new AddTimeCardTransaction(dateInPreviousTimePeriod, 5.0, employeeId);
        addSecondTimeCard.execute();

        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();

        validateHourlyPaycheck(paydayTransaction, employeeId, payDate, 2.0 * hourlySalary);
    }

    @Test
    public void testSalariedUnionMemberDues() throws Exception {
        int employeeId = 1;
        Transaction addEmployeeTransaction = new AddSalariedEmployeeTransaction(employeeId, "Bob", "Home", 1000.0);
        addEmployeeTransaction.execute();
        int memberId = 7734;
        double weeklyUnionDues = 9.42;
        ChangeMemberTransaction changeMemberTransaction = new ChangeMemberTransaction(employeeId, memberId, weeklyUnionDues);
        changeMemberTransaction.execute();

        Calendar payDate = new GregorianCalendar(2001, NOVEMBER, 30);
        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();

        int numberOfWeeksInPayPeriod = 5;
        double expectedDues = numberOfWeeksInPayPeriod * weeklyUnionDues;
        validateHourlyPaycheck(paydayTransaction, employeeId, payDate, 1000.0, expectedDues);
    }

    private void validateHourlyPaycheck(PaydayTransaction paydayTransaction, int employeeId, Calendar payDate,
                                        double expectedGross, double expectedDeductions) {
        PayCheck payCheck = paydayTransaction.getPaycheck(employeeId);
        assertThat(payCheck, is(notNullValue()));
        assertThat(payCheck.getDate(), is(payDate));
        assertThat(payCheck.getGrossPay(), is(closeTo(expectedGross, FLOAT_ACCURACY)));
        assertThat(payCheck.getField("Disposition"), is("Hold"));
        assertThat(payCheck.getDeductions(), is(closeTo(expectedDeductions, FLOAT_ACCURACY)));
        assertThat(payCheck.getNetPay(), is(closeTo(expectedGross - expectedDeductions, FLOAT_ACCURACY)));
    }

    private void validateHourlyPaycheck(PaydayTransaction paydayTransaction, int employeeId, Calendar payDate, double expectedAmount) {
        validateHourlyPaycheck(paydayTransaction, employeeId, payDate, expectedAmount, 0.0);
    }
}
