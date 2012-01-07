package payrollcasestudy.transactions.delete.employees;

import org.junit.Before;
import org.junit.Test;
import payrollcasestudy.Employee;
import payrollcasestudy.transactions.DatabaseAwareTest;
import payrollcasestudy.transactions.add.employees.AddCommissionedEmployeeTransaction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Listing 19-9
 */
public class DeleteEmployeeTransactionTest extends DatabaseAwareTest {

    @Test
    public void testDeleteEmployees() throws Exception {
        int employeeId = 3;
        AddCommissionedEmployeeTransaction addEmployeeTransaction =
                new AddCommissionedEmployeeTransaction(employeeId, "Lance", "Home", 2500.0, 3.2);
        addEmployeeTransaction.execute();

        Employee employee = payrollDatabase.getEmployee(employeeId);
        assertThat(employee, is(notNullValue()));

        DeleteEmployeeTransaction deleteTransaction = new DeleteEmployeeTransaction(employeeId);
        deleteTransaction.execute();

        employee = payrollDatabase.getEmployee(employeeId);
        assertThat(employee, is(nullValue()));
    }

}
