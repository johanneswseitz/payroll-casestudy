package payrollcasestudy.entities.paymentclassifications;

import payrollcasestudy.entities.PayCheck;

public interface PaymentClassification {
    double calculatePay(PayCheck payCheck);
}
