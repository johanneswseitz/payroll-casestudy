package payrollcasestudy.transactions.add.employees;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import payrollcasestudy.Employee;
import payrollcasestudy.PayrollDatabase;
import payrollcasestudy.paymentclassifiactions.PaymentClassification;
import payrollcasestudy.paymentclassifiactions.SalariedClassification;
import payrollcasestudy.paymentmethods.HoldMethod;
import payrollcasestudy.paymentschedule.MonthlyPaymentSchedule;
import payrollcasestudy.transactions.Transaction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class AddSalariedEmployeeTest {
    private PayrollDatabase payrollDatabase;
    protected double FLOATING_POINT_ACCURACY = 0.000001;

    @Before
    public void setUp(){
        payrollDatabase = PayrollDatabase.globalPayrollDatabase;
    }

    @Test
    public void testAddSalariedEmployee(){
        int employeeId = 1;
        Transaction addEmployeeTransaction =
                new AddSalariedEmployeeTransaction(employeeId, "Bob", "Home", 1000.0);
        addEmployeeTransaction.execute();

        Employee employee = payrollDatabase.getEmployee(employeeId);
        assertThat(employee.getName(), is(equalTo("Bob")));

        PaymentClassification paymentClassification = employee.getPaymentClassification();
        assertThat(paymentClassification, is(instanceOf(SalariedClassification.class)));
        SalariedClassification salariedClassification = (SalariedClassification) paymentClassification;
        assertThat(salariedClassification.getSalary(), closeTo(1000.0, FLOATING_POINT_ACCURACY));

        assertThat(employee.getPaymentSchedule(), is(instanceOf(MonthlyPaymentSchedule.class)));
        assertThat(employee.getPaymentMethod(), is(instanceOf(HoldMethod.class)));
    }

    @After
    public void tearDown(){
        payrollDatabase.clear();
    }

}
