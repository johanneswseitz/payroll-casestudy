package payrollcasestudy.transactions.add;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.UnionAffiliation;
import payrollcasestudy.transactions.Transaction;

import java.util.Calendar;

public class AddServiceChargeTransaction implements Transaction{

    PayrollDatabase database = PayrollDatabase.globalPayrollDatabase;

    private final int memberId;
    private final Calendar date;
    private final double amount;

    public AddServiceChargeTransaction(int memberId, Calendar date, double amount) {
        this.memberId = memberId;
        this.date = date;
        this.amount = amount;
    }

    @Override
    public void execute() {
        Employee unionMember = database.getUnionMember(memberId);
        UnionAffiliation unionAffiliation = unionMember.getUnionAffiliation();
        unionAffiliation.addServiceCharge(date, amount);
    }
}
