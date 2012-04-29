package payrollcasestudy.transactions.add;

import payrollcasestudy.entities.paymentclassifications.CommissionedPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.entities.paymentschedule.BiweeklyPaymentSchedule;
import payrollcasestudy.entities.paymentschedule.PaymentSchedule;

/**
 * @author Johannes Seitz
 */
public class AddCommissionedEmployeeTransaction extends AddEmployeeTransaction{
    private double monthlySalary;
    private double commissionRate;

    public AddCommissionedEmployeeTransaction(int employeeId, String name, String address,
                                              double monthlySalary, double commissionRate) {
        super(employeeId, name, address);
        this.monthlySalary = monthlySalary;
        this.commissionRate = commissionRate;
    }

    @Override
    protected PaymentSchedule getPaymentSchedule() {
        return new BiweeklyPaymentSchedule();
    }

    @Override
    protected PaymentClassification getPaymentClassification() {
        return new CommissionedPaymentClassification(monthlySalary, commissionRate);
    }
}
