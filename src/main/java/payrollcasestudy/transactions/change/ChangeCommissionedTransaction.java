package payrollcasestudy.transactions.change;

import payrollcasestudy.entities.paymentclassifications.CommissionedPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.entities.paymentschedule.MonthlyPaymentSchedule;
import payrollcasestudy.entities.paymentschedule.PaymentSchedule;

public class ChangeCommissionedTransaction extends ChangeClassificationTransaction {
    private int monthlySalary;
    private double commissionRate;

    public ChangeCommissionedTransaction(int employeeId, int monthlySalary, double commissionRate) {
        super(employeeId);
        this.monthlySalary = monthlySalary;
        this.commissionRate = commissionRate;
    }

    @Override
    PaymentClassification getNewPaymentClassification() {
        return new CommissionedPaymentClassification(monthlySalary, commissionRate);
    }

    @Override
    PaymentSchedule getNewPaymentSchedule() {
        return new MonthlyPaymentSchedule();
    }
}
