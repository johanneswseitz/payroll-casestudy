package payrollcasestudy.transactions.add.servicecharge;

import payrollcasestudy.Affiliation;
import payrollcasestudy.Employee;
import payrollcasestudy.PayrollDatabase;
import payrollcasestudy.UnionAffiliation;
import payrollcasestudy.transactions.Transaction;

public class ServiceChargeTransaction implements Transaction{

    PayrollDatabase database = PayrollDatabase.globalPayrollDatabase;

    public ServiceChargeTransaction(int memberId, int date, double amount) {
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
