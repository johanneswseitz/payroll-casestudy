package payrollcasestudy.entities.paymentclassifications;

import payrollcasestudy.entities.PayCheck;

import java.util.Calendar;

public abstract class PaymentClassification {
    public abstract double calculatePay(PayCheck payCheck);
    protected boolean isInPayPeriod(Calendar date, PayCheck payCheck) {
        Calendar payPeriodStart = payCheck.getPayPeriodStart();
        Calendar payPeriodEnd = payCheck.getDate();
        return date.equals(payPeriodEnd) || date.equals(payPeriodStart) ||
                (date.after(payPeriodStart) && date.before(payPeriodEnd));
    }
}
