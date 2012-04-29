package payrollcasestudy.transactions.change;

import org.junit.Rule;
import org.junit.Test;
import payrollcasestudy.DatabaseResource;
import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.UnionAffiliation;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ChangeNoMemberTransactionTest {
    @Rule
    public DatabaseResource databaseResource = new DatabaseResource();

    @Test
    public void testChangeMemberTransaction() throws Exception {
        PayrollDatabase database = databaseResource.getInstance();

        int employeeId = 2;
        int memberId = 7734;
        Transaction addEmployeeTransaction =
                new AddHourlyEmployeeTransaction(employeeId, "Bill", "Home", 15.25);
        addEmployeeTransaction.execute();

        Employee employee = database.getEmployee(employeeId);
        UnionAffiliation unionAffiliation = new UnionAffiliation(memberId,92.1);
        employee.setUnionAffiliation(unionAffiliation);
        assertThat(employee.getUnionAffiliation(), is(unionAffiliation));

        database.addUnionMember(memberId, employee);
        assertThat(database.getUnionMember(memberId), is(employee));

        Transaction noMemberTransaction = new ChangeNoMemberTransaction(employeeId);
        noMemberTransaction.execute();

        employee = database.getEmployee(employeeId);
        assertThat(employee.getUnionAffiliation(), is(UnionAffiliation.NO_AFFILIATION));

        assertThat(database.getUnionMember(memberId), is(nullValue()));
    }
}
