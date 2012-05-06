package payrollcasestudy.transactions.add;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.SalesReceipt;
import payrollcasestudy.entities.paymentclassifications.CommissionedPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.transactions.Transaction;

import java.util.Calendar;

public class AddSalesReceiptTransaction implements Transaction{

    private Calendar date;
    private double amount;
    private int employeeId;

    public AddSalesReceiptTransaction(Calendar date, double amount, int employeeId) {
        this.date = date;
        this.amount = amount;
        this.employeeId = employeeId;
    }

    public void execute() {
        Employee employee = PayrollDatabase.globalPayrollDatabase.getEmployee(employeeId);
        if (employee != null){
            PaymentClassification paymentClassification = employee.getPaymentClassification();
            if (paymentClassification instanceof CommissionedPaymentClassification){
                CommissionedPaymentClassification commissionedPaymentClassification =
                        (CommissionedPaymentClassification) paymentClassification;
                commissionedPaymentClassification.addSalesReceipt(new SalesReceipt(date, amount));
            }
        }
    }
}
