package payrollcasestudy.transactions.change;

import org.junit.Rule;
import org.junit.Test;
import payrollcasestudy.DatabaseResource;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.UnionAffiliation;
import payrollcasestudy.transactions.add.AddEmployeeTransaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static payrollcasestudy.TestConstants.*;

public class ChangeMemberTransactionTest {

    @Rule
    public DatabaseResource databaseResource = new DatabaseResource();

    @Test
    public void testChangeMemberTransaction() throws Exception {
        int employeeId = 2;
        int memberId = 7734;
        AddEmployeeTransaction addEmployeeTransaction =
                new AddHourlyEmployeeTransaction(employeeId, "Bill", "Home", 15.25);
        addEmployeeTransaction.execute();

        ChangeMemberTransaction changeMemberTransaction =
                new ChangeMemberTransaction(employeeId, memberId, 99.42);
        changeMemberTransaction.execute();

        Employee employee = databaseResource.getInstance().getEmployee(employeeId);
        assertThat(employee.getUnionAffiliation(), is(notNullValue()));

        UnionAffiliation unionAffiliation = employee.getUnionAffiliation();
        assertThat(unionAffiliation.getDues(), is(closeTo(99.42, FLOAT_ACCURACY)));

        Employee member = databaseResource.getInstance().getUnionMember(memberId);
        assertThat(member, is(employee));
    }
}
