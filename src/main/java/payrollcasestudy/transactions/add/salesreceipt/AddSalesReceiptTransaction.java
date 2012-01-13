package payrollcasestudy.transactions.add.salesreceipt;

import payrollcasestudy.Employee;
import payrollcasestudy.PayrollDatabase;
import payrollcasestudy.SalesReceipt;
import payrollcasestudy.paymentclassifications.CommissionedPaymentClassification;
import payrollcasestudy.paymentclassifications.PaymentClassification;
import payrollcasestudy.transactions.Transaction;

public class AddSalesReceiptTransaction implements Transaction{

    private int date;
    private double amount;
    private int employeeId;

    public AddSalesReceiptTransaction(int date, double amount, int employeeId) {
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
