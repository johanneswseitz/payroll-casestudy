package payrollcasestudy.transactions.add;

import org.junit.Rule;
import org.junit.Test;
import payrollcasestudy.DatabaseResource;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.SalesReceipt;
import payrollcasestudy.entities.paymentclassifications.CommissionedPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.transactions.Transaction;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.util.Calendar.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static payrollcasestudy.TestConstants.*;

public class AddSalesReceiptTransactionTest {

    @Rule
    public DatabaseResource database = new DatabaseResource();

    @Test
    public void testAddSalesReceipt() throws Exception {
        int employeeId = 2;
        AddCommissionedEmployeeTransaction addCommissionedEmployee =
                new AddCommissionedEmployeeTransaction(employeeId, "Bill", "Home", 15.25, 0.5);
        addCommissionedEmployee.execute();

        Calendar date = new GregorianCalendar(2001, NOVEMBER, 31);
        Transaction salesReceiptTransaction =
                new AddSalesReceiptTransaction(date, 1000.0, employeeId);
        salesReceiptTransaction.execute();

        Employee employee = database.getInstance().getEmployee(employeeId);
        assertThat(employee, is(notNullValue()));
        PaymentClassification paymentClassification = employee.getPaymentClassification();
        CommissionedPaymentClassification commissionedPaymentClassification =
                (CommissionedPaymentClassification) paymentClassification;
        SalesReceipt receipt = commissionedPaymentClassification.getSalesReceipt(date);
        assertThat(receipt, is(notNullValue()));
        assertThat(receipt.getAmount(), is(closeTo(1000.0, FLOAT_ACCURACY)));
    }
}
