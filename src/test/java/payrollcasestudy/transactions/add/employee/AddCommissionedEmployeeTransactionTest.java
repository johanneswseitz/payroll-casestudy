package payrollcasestudy.transactions.add.employee;

import org.junit.Test;
import payrollcasestudy.Employee;
import payrollcasestudy.paymentclassifiactions.CommissionedPaymentClassification;
import payrollcasestudy.paymentclassifiactions.PaymentClassification;
import payrollcasestudy.paymentmethods.HoldMethod;
import payrollcasestudy.paymentschedule.BiweeklyPaymentSchedule;
import payrollcasestudy.transactions.DatabaseAwareTest;
import payrollcasestudy.transactions.Transaction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * No Listing
 */
public class AddCommissionedEmployeeTransactionTest extends DatabaseAwareTest{
    protected double FLOATING_POINT_ACCURACY = 0.000001;

    @Test
    public void testAddCommissionedEmployee(){
        int employeeId = 1;
        Transaction addEmployeeTransaction =
                new AddCommissionedEmployeeTransaction(employeeId, "Michael", "Home", 200.0 , 20.0);
        addEmployeeTransaction.execute();
        Employee employee = payrollDatabase.getEmployee(employeeId);
        assertThat(employee.getName(), is(equalTo("Michael")));

        PaymentClassification paymentClassification = employee.getPaymentClassification();
        assertThat(paymentClassification, is(instanceOf(CommissionedPaymentClassification.class)));
        CommissionedPaymentClassification commissionedClassification =
                (CommissionedPaymentClassification) paymentClassification;
        assertThat(commissionedClassification.getCommissionRate(), is(closeTo(20.0, FLOATING_POINT_ACCURACY)));
        assertThat(commissionedClassification.getMonthlySalary(), is(closeTo(200.0, FLOATING_POINT_ACCURACY)));

        assertThat(employee.getPaymentSchedule(), is(instanceOf(BiweeklyPaymentSchedule.class)));
        assertThat(employee.getPaymentMethod(), is(instanceOf(HoldMethod.class)));
    }

}
