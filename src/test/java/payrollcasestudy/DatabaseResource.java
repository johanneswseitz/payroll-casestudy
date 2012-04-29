package payrollcasestudy;

import org.junit.rules.ExternalResource;
import payrollcasestudy.boundaries.PayrollDatabase;

public class DatabaseResource extends ExternalResource {
    protected PayrollDatabase instance;

    @Override
    public void before(){
        instance = PayrollDatabase.globalPayrollDatabase;
    }

    @Override
    public void after(){
        instance.clear();
    }

    public PayrollDatabase getInstance() {
        return instance;
    }
}
