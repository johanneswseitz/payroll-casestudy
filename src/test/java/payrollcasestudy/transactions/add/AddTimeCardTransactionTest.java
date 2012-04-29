package payrollcasestudy.transactions.add;

import org.junit.Rule;
import org.junit.Test;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.TimeCard;
import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.transactions.DatabaseResource;
import payrollcasestudy.transactions.Transaction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class AddTimeCardTransactionTest {

    @Rule
    public DatabaseResource database = new DatabaseResource();

    @Test
    public void testTimeCardTransaction(){
        int employeeId = 2;
        AddHourlyEmployeeTransaction addHourlyEmployee =
                new AddHourlyEmployeeTransaction(employeeId, "Billy", "Home", 15.25);
        addHourlyEmployee.execute();

        Transaction timeCardTransaction = new AddTimeCardTransaction(20011031, 8.0, employeeId);
        timeCardTransaction.execute();

        Employee employee = database.getInstance().getEmployee(employeeId);
        assertThat(employee, is(notNullValue()));

        PaymentClassification paymentClassification = employee.getPaymentClassification();
        HourlyPaymentClassification hourlyPaymentClassification = (HourlyPaymentClassification) paymentClassification;
        assertThat(hourlyPaymentClassification, is(notNullValue()));

        TimeCard timeCard =  hourlyPaymentClassification.getTimeCard(20011031);
        assertNotNull(timeCard);
        assertThat(timeCard.getHours() , is(8.0));
    }
}
