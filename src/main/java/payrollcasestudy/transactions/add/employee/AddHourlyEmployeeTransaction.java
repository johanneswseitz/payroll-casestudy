package payrollcasestudy.transactions.add.employee;

import payrollcasestudy.paymentclassifications.HourlyPaymentClassification;
import payrollcasestudy.paymentclassifications.PaymentClassification;
import payrollcasestudy.paymentschedule.PaymentSchedule;
import payrollcasestudy.paymentschedule.WeeklyPaymentSchedule;

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
