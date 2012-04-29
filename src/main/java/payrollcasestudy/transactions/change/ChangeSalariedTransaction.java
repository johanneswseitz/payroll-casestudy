package payrollcasestudy.transactions.change;

import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.entities.paymentclassifications.SalariedClassification;
import payrollcasestudy.entities.paymentschedule.MonthlyPaymentSchedule;
import payrollcasestudy.entities.paymentschedule.PaymentSchedule;

public class ChangeSalariedTransaction extends ChangeClassificationTransaction{
    private double newSalary;

    public ChangeSalariedTransaction(int employeeId, double newSalary) {
        super(employeeId);
        this.newSalary = newSalary;
    }

    @Override
    PaymentClassification getNewPaymentClassification() {
        return new SalariedClassification(newSalary);
    }

    @Override
    PaymentSchedule getNewPaymentSchedule() {
        return new MonthlyPaymentSchedule();
    }
}
