package payrollcasestudy.transactions.add.employee;

import payrollcasestudy.paymentclassifications.CommissionedPaymentClassification;
import payrollcasestudy.paymentclassifications.PaymentClassification;
import payrollcasestudy.paymentschedule.BiweeklyPaymentSchedule;
import payrollcasestudy.paymentschedule.PaymentSchedule;

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
        return new CommissionedPaymentClassification(commissionRate, monthlySalary);
    }
}
