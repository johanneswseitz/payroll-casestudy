package payrollcasestudy.paymentclassifiactions;

/**
 * @author Johannes Seitz
 */
public class CommissionedPaymentClassification implements PaymentClassification {
    private double commissionRate;
    private double monthlySalary;

    public CommissionedPaymentClassification(double commissionRate, double monthlySalary) {
        this.commissionRate = commissionRate;
        this.monthlySalary = monthlySalary;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public double getCommissionRate() {
        return commissionRate;
    }
}
