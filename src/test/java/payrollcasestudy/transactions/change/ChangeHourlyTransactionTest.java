package payrollcasestudy.transactions.change;

import org.junit.Rule;
import org.junit.Test;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;
import payrollcasestudy.entities.paymentschedule.WeeklyPaymentSchedule;
import payrollcasestudy.transactions.DatabaseResource;
import payrollcasestudy.transactions.add.AddCommissionedEmployeeTransaction;
import payrollcasestudy.transactions.add.AddEmployeeTransaction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ChangeHourlyTransactionTest {

    @Rule
    public DatabaseResource databaseResource = new DatabaseResource();

    @Test
    public void testChangeHourlyTransaction() throws Exception {
        int employeeId = 3;
        AddEmployeeTransaction addEmployeeTransaction =
                new AddCommissionedEmployeeTransaction(employeeId, "Lance", "Home", 2500, 3.2);
        addEmployeeTransaction.execute();

        ChangeHourlyTransaction changeHourlyTransaction = new ChangeHourlyTransaction(employeeId, 27.52);
        changeHourlyTransaction.execute();

        Employee employee = databaseResource.getInstance().getEmployee(employeeId);
        assertThat(employee.getPaymentClassification(), is(instanceOf(HourlyPaymentClassification.class)));
        HourlyPaymentClassification paymentClassification =
                (HourlyPaymentClassification) employee.getPaymentClassification();
        assertThat(paymentClassification.getHourlyRate(), is(closeTo(27.52, 0.0001)));
        assertThat(employee.getPaymentSchedule(), is(instanceOf(WeeklyPaymentSchedule.class)));
    }
}
