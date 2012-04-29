package payrollcasestudy.transactions.add;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.affiliations.Affiliation;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.UnionAffiliation;
import payrollcasestudy.transactions.Transaction;

public class AddServiceChargeTransaction implements Transaction{

    PayrollDatabase database = PayrollDatabase.globalPayrollDatabase;

    public AddServiceChargeTransaction(int memberId, int date, double amount) {
        Employee unionMember = database.getUnionMember(memberId);
        UnionAffiliation unionAffiliation = null;
        for (Affiliation affiliation:  unionMember.getAffiliations()){
            if (affiliation instanceof UnionAffiliation){
                unionAffiliation = (UnionAffiliation) affiliation;
            }
        }
        if (unionAffiliation == null) return;
        unionAffiliation.addServiceCharge(date, amount);
    }

    @Override
    public void execute() {
    }
}
