package payrollcasestudy.transactions.add;

import org.junit.Rule;
import org.junit.Test;
import payrollcasestudy.DatabaseResource;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.paymentclassifications.CommissionedPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.entities.paymentmethods.HoldMethod;
import payrollcasestudy.entities.paymentschedule.BiweeklyPaymentSchedule;
import payrollcasestudy.transactions.Transaction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static payrollcasestudy.TestConstants.*;

/**
 * No Listing
 */
public class AddCommissionedEmployeeTransactionTest {

    @Rule
    public DatabaseResource database = new DatabaseResource();

    @Test
    public void testAddCommissionedEmployee(){
        int employeeId = 1;
        Transaction addEmployeeTransaction =
                new AddCommissionedEmployeeTransaction(employeeId, "Michael", "Home", 200.0 , 20.0);
        addEmployeeTransaction.execute();
        Employee employee = database.getInstance().getEmployee(employeeId);
        assertThat(employee.getName(), is("Michael"));

        PaymentClassification paymentClassification = employee.getPaymentClassification();
        assertThat(paymentClassification, is(instanceOf(CommissionedPaymentClassification.class)));
        CommissionedPaymentClassification commissionedClassification =
                (CommissionedPaymentClassification) paymentClassification;
        assertThat(commissionedClassification.getCommissionRate(), is(closeTo(20.0, FLOAT_ACCURACY)));
        assertThat(commissionedClassification.getMonthlySalary(), is(closeTo(200.0, FLOAT_ACCURACY)));

        assertThat(employee.getPaymentSchedule(), is(instanceOf(BiweeklyPaymentSchedule.class)));
        assertThat(employee.getPaymentMethod(), is(instanceOf(HoldMethod.class)));
    }

}
