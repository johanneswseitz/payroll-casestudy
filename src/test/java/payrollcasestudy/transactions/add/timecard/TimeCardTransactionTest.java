package payrollcasestudy.transactions.add.timecard;

import org.junit.Rule;
import org.junit.Test;
import payrollcasestudy.Employee;
import payrollcasestudy.paymentclassifiactions.HourlyPaymentClassification;
import payrollcasestudy.paymentclassifiactions.PaymentClassification;
import payrollcasestudy.transactions.DatabaseResource;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.employee.AddHourlyEmployeeTransaction;
import payrollcasestudy.transactions.add.timecard.TimeCard;
import payrollcasestudy.transactions.add.timecard.TimeCardTransaction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class TimeCardTransactionTest {

    @Rule
    public DatabaseResource database = new DatabaseResource();

    @Test
    public void testTimeCardTransaction(){
        int employeeId = 2;
        AddHourlyEmployeeTransaction addHourlyEmployee =
                new AddHourlyEmployeeTransaction(employeeId, "Billy", "Home", 15.25);
        addHourlyEmployee.execute();

        Transaction timeCardTransaction = new TimeCardTransaction(20011031, 8.0, employeeId);
        timeCardTransaction.execute();

        Employee employee = database.getInstance().getEmployee(employeeId);
        assertThat(employee, is(notNullValue()));

        PaymentClassification paymentClassification = employee.getPaymentClassification();
        HourlyPaymentClassification hourlyPaymentClassification = (HourlyPaymentClassification) paymentClassification;
        assertThat(hourlyPaymentClassification, is(notNullValue()));

        TimeCard timeCard =  hourlyPaymentClassification.getTimeCard(20011031);
        assertNotNull(timeCard);
        assertThat(timeCard.getHours() , is(equalTo(8.0)));
    }
}
