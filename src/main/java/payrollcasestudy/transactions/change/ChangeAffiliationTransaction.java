package payrollcasestudy.transactions.change;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.UnionAffiliation;

public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction{

    public ChangeAffiliationTransaction(int employeeId) {
        super(employeeId);
    }

    @Override
    public void changeEmployee(Employee employee) {
        recordMembership(employee);
        employee.setUnionAffiliation(getAffiliation());
    }

    protected abstract UnionAffiliation getAffiliation();

    protected abstract void recordMembership(Employee employee);

}
