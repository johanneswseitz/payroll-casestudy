package payrollcasestudy.transactions;

import org.junit.After;
import org.junit.Before;
import payrollcasestudy.PayrollDatabase;

public class DatabaseAwareTest {
    protected PayrollDatabase payrollDatabase;

    @Before
    public void setUp(){
        payrollDatabase = PayrollDatabase.globalPayrollDatabase;
    }

    @After
    public void tearDown(){
        payrollDatabase.clear();
    }
}
