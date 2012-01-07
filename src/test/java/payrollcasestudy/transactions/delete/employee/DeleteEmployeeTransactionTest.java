package payrollcasestudy.transactions.delete.employee;

import org.junit.Rule;
import org.junit.Test;
import payrollcasestudy.Employee;
import payrollcasestudy.transactions.DatabaseResource;
import payrollcasestudy.transactions.add.employee.AddCommissionedEmployeeTransaction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Listing 19-9
 */
public class DeleteEmployeeTransactionTest {

    @Rule
    public DatabaseResource database = new DatabaseResource();

    @Test
    public void testDeleteEmployees() throws Exception {
        int employeeId = 3;
        AddCommissionedEmployeeTransaction addEmployeeTransaction =
                new AddCommissionedEmployeeTransaction(employeeId, "Lance", "Home", 2500.0, 3.2);
        addEmployeeTransaction.execute();

        Employee employee = database.getInstance().getEmployee(employeeId);
        assertThat(employee, is(notNullValue()));

        DeleteEmployeeTransaction deleteTransaction = new DeleteEmployeeTransaction(employeeId);
        deleteTransaction.execute();

        employee = database.getInstance().getEmployee(employeeId);
        assertThat(employee, is(nullValue()));
    }

}
