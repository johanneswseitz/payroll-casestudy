package payrollcasestudy.transactions.change;

import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.entities.paymentschedule.PaymentSchedule;
import payrollcasestudy.entities.paymentschedule.WeeklyPaymentSchedule;

public class ChangeHourlyTransaction extends ChangeClassificationTransaction{
    private double hourlySalary;

    public ChangeHourlyTransaction(int employeeId, double hourlySalary) {
        super(employeeId);
        this.hourlySalary = hourlySalary;
    }

    @Override
    PaymentClassification getNewPaymentClassification() {
        return new HourlyPaymentClassification(hourlySalary);
    }

    @Override
    PaymentSchedule getNewPaymentSchedule() {
        return new WeeklyPaymentSchedule();
    }
}
