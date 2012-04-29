package payrollcasestudy.transactions.change;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.UnionAffiliation;

public class ChangeNoMemberTransaction extends ChangeAffiliationTransaction {

    public ChangeNoMemberTransaction(int employeeId) {
        super(employeeId);
    }

    @Override
    protected UnionAffiliation getAffiliation() {
        return UnionAffiliation.NO_AFFILIATION;
    }

    @Override
    protected void recordMembership(Employee employee) {
        int memberId = employee.getUnionAffiliation().getMemberId();
        database.deleteUnionMember(memberId);
    }
}
