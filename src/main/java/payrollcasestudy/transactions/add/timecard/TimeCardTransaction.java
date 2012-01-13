package payrollcasestudy.transactions.add.timecard;

import payrollcasestudy.Employee;
import payrollcasestudy.PayrollDatabase;
import payrollcasestudy.TimeCard;
import payrollcasestudy.paymentclassifications.HourlyPaymentClassification;
import payrollcasestudy.paymentclassifications.PaymentClassification;
import payrollcasestudy.transactions.Transaction;

public class TimeCardTransaction implements Transaction {

    private int date;
    private double hours;
    private int employeeId;

    public TimeCardTransaction(int date, double hours, int employeeId) {
        this.date = date;
        this.hours = hours;
        this.employeeId = employeeId;
    }

    @Override
    public void execute() {
        Employee employee = PayrollDatabase.globalPayrollDatabase.getEmployee(employeeId);
        if (employee != null){
            PaymentClassification paymentClassification = employee.getPaymentClassification();
            if (paymentClassification instanceof HourlyPaymentClassification){
                HourlyPaymentClassification classification = (HourlyPaymentClassification) paymentClassification;
                classification.addTimeCard(new TimeCard(date, hours));
            }
        }
    }
}
