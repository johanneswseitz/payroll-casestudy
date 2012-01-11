package payrollcasestudy.transactions.add.salesreceipt;

import org.junit.Rule;
import org.junit.Test;
import payrollcasestudy.Employee;
import payrollcasestudy.SalesReceipt;
import payrollcasestudy.paymentclassifiactions.CommissionedPaymentClassification;
import payrollcasestudy.paymentclassifiactions.PaymentClassification;
import payrollcasestudy.transactions.DatabaseResource;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.employee.AddCommissionedEmployeeTransaction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class AddSalesReceiptTransactionTest {

    public static final double FLOATING_POINT_ACCURACY = 0.00001;

    @Rule
    public DatabaseResource database = new DatabaseResource();

    @Test
    public void testAddSalesReceipt() throws Exception {
        int employeeId = 2;
        AddCommissionedEmployeeTransaction addCommissionedEmployee =
                new AddCommissionedEmployeeTransaction(employeeId, "Bill", "Home", 15.25, 0.5);
        addCommissionedEmployee.execute();

        Transaction salesReceiptTransaction =
                new AddSalesReceiptTransaction(20011031, 1000.0, employeeId);
        salesReceiptTransaction.execute();

        Employee employee = database.getInstance().getEmployee(employeeId);
        assertThat(employee, is(notNullValue()));
        PaymentClassification paymentClassification = employee.getPaymentClassification();
        CommissionedPaymentClassification commissionedPaymentClassification =
                (CommissionedPaymentClassification) paymentClassification;
        SalesReceipt receipt = commissionedPaymentClassification.getSalesReceipt(20011031);
        assertThat(receipt, is(notNullValue()));
        assertThat(receipt.getAmount(), is(closeTo(1000.0, FLOATING_POINT_ACCURACY)));
    }
}
