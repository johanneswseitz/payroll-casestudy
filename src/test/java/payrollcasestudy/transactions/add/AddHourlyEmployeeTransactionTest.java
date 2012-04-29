package payrollcasestudy.transactions.add;

import org.junit.Rule;
import org.junit.Test;
import payrollcasestudy.DatabaseResource;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.entities.paymentmethods.HoldMethod;
import payrollcasestudy.entities.paymentschedule.WeeklyPaymentSchedule;
import payrollcasestudy.transactions.Transaction;
import static payrollcasestudy.TestConstants.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * No Listing
 */
public class AddHourlyEmployeeTransactionTest  {

    @Rule
    public DatabaseResource database = new DatabaseResource();

    @Test
    public void testAddHourlyEmployee(){
        int employeeId = 1;
        Transaction addEmployeeTransaction =
                new AddHourlyEmployeeTransaction(employeeId, "Steve", "Home", 20.0);
        addEmployeeTransaction.execute();
        Employee employee = database.getInstance().getEmployee(employeeId);
        assertThat(employee.getName(), is("Steve"));

        PaymentClassification paymentClassification = employee.getPaymentClassification();
        assertThat(paymentClassification, is(instanceOf(HourlyPaymentClassification.class)));
        HourlyPaymentClassification hourlyPaymentClassification =
                (HourlyPaymentClassification) paymentClassification;
        assertThat(hourlyPaymentClassification.getHourlyRate(), is(closeTo(20.0, FLOAT_ACCURACY)));

        assertThat(employee.getPaymentSchedule(), is(instanceOf(WeeklyPaymentSchedule.class)));
        assertThat(employee.getPaymentMethod(), is(instanceOf(HoldMethod.class)));
    }

}
