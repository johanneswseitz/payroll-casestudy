package payrollcasestudy.transactions.add;

import org.junit.Rule;
import org.junit.Test;
import payrollcasestudy.DatabaseResource;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.ServiceCharge;
import payrollcasestudy.entities.affiliations.UnionAffiliation;
import payrollcasestudy.transactions.Transaction;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static payrollcasestudy.TestConstants.*;

public class AddServiceChargeTransactionTest {

    @Rule
    public DatabaseResource database = new DatabaseResource();

    @Test
    public void testAddServiceCharge() throws Exception {
        int employeeId = 2;
        Transaction addEmployeeTransaction =
                new AddHourlyEmployeeTransaction(employeeId, "Bill", "Home", 15.25);
        addEmployeeTransaction.execute();

        Employee employee = database.getInstance().getEmployee(employeeId);
        assertThat(employee, is(notNullValue()));

        UnionAffiliation unionAffiliation = new UnionAffiliation(12.5);
        employee.setUnionAffiliation(unionAffiliation);
        int memberId = 86; //Maxwell Smart
        database.getInstance().addUnionMember(memberId, employee);
        assertThat(database.getInstance().getUnionMember(memberId), is(notNullValue()));

        AddServiceChargeTransaction addServiceChargeTransaction = new AddServiceChargeTransaction(memberId, 20011101, 12.95);
        addServiceChargeTransaction.execute();
        ServiceCharge serviceCharge = unionAffiliation.getServiceCharge(20011101);
        assertThat(serviceCharge, is(notNullValue()));
        assertThat(serviceCharge.getAmount(), is(closeTo(12.95, FLOAT_ACCURACY)));
    }
}
