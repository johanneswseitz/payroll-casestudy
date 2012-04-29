package payrollcasestudy.transactions;

import org.junit.Rule;
import org.junit.Test;
import payrollcasestudy.DatabaseResource;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.transactions.add.AddSalariedEmployeeTransaction;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.core.Is.is;
import static payrollcasestudy.TestConstants.*;

public class PaydayTransactionTest {

    @Rule
    public DatabaseResource databaseResource = new DatabaseResource();

    @Test
    public void testSingleSalariedEmployee() throws Exception {
        int employeeId = 1;
        Transaction addSalariedEmployeeTransaction = new AddSalariedEmployeeTransaction(employeeId,"Bob", "Home", 1000.0);
        addSalariedEmployeeTransaction.execute();

        Employee employee = databaseResource.getInstance().getEmployee(employeeId);
        Calendar payDate = new GregorianCalendar(2001, 10, 30);
        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();

        PayCheck payCheck = paydayTransaction.getPaycheck(employeeId);
        assertThat(payCheck.getDate(), is(payDate));
        assertThat(payCheck.getGrossPay(), is(closeTo(1000.0, FLOAT_ACCURACY)));
        assertThat(payCheck.getField("Disposition"), is("Hold"));
        assertThat(payCheck.getDeductions(), is(closeTo(0, FLOAT_ACCURACY)));
        assertThat(payCheck.getNetPay(), is(closeTo(1000.0, FLOAT_ACCURACY)));
    }
}
