package payrollcasestudy.transactions.add;

import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.entities.paymentschedule.PaymentSchedule;
import payrollcasestudy.entities.paymentschedule.WeeklyPaymentSchedule;

public class AddHourlyEmployeeTransaction extends AddEmployeeTransaction {
    private double hourlyRate;

    public AddHourlyEmployeeTransaction(int employeeId, String name, String address, double hourlyRate) {
        super(employeeId, name, address);
        this.hourlyRate = hourlyRate;
    }

    @Override
    protected PaymentSchedule getPaymentSchedule() {
        return new WeeklyPaymentSchedule();
    }

    @Override
    protected PaymentClassification getPaymentClassification() {
        return new HourlyPaymentClassification(hourlyRate);
    }
}
