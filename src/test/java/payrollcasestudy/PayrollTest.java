package payrollcasestudy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import payrollcasestudy.employeetransactions.AddCommissionedEmployeeTransaction;
import payrollcasestudy.employeetransactions.AddHourlyEmployeeTransaction;
import payrollcasestudy.employeetransactions.AddSalariedEmployeeTransaction;
import payrollcasestudy.paymentclassifiactions.CommissionedPaymentClassification;
import payrollcasestudy.paymentclassifiactions.HourlyPaymentClassification;
import payrollcasestudy.paymentclassifiactions.PaymentClassification;
import payrollcasestudy.paymentclassifiactions.SalariedClassification;
import payrollcasestudy.paymentmethods.HoldMethod;
import payrollcasestudy.paymentschedule.BiweeklyPaymentSchedule;
import payrollcasestudy.paymentschedule.MonthlyPaymentSchedule;
import payrollcasestudy.paymentschedule.WeeklyPaymentSchedule;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class PayrollTest {

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

    @Test
    public void testAddHourlyEmployee(){
        int employeeId = 1;
        Transaction addEmployeeTransaction =
                new AddHourlyEmployeeTransaction(employeeId, "Steve", "Home", 20.0);
        addEmployeeTransaction.execute();
        Employee employee = payrollDatabase.getEmployee(employeeId);
        assertThat(employee.getName(), is(equalTo("Steve")));

        PaymentClassification paymentClassification = employee.getPaymentClassification();
        assertThat(paymentClassification, is(instanceOf(HourlyPaymentClassification.class)));
        HourlyPaymentClassification hourlyPaymentClassification =
                (HourlyPaymentClassification) paymentClassification;
        assertThat(hourlyPaymentClassification.getHourlyRate(), is(closeTo(20.0, FLOATING_POINT_ACCURACY)));

        assertThat(employee.getPaymentSchedule(), is(instanceOf(WeeklyPaymentSchedule.class)));
        assertThat(employee.getPaymentMethod(), is(instanceOf(HoldMethod.class)));
    }

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
    }

    @After
    public void tearDown(){
        payrollDatabase.clear();
    }
}
