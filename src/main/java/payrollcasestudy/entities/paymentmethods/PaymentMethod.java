package payrollcasestudy.entities.paymentmethods;

import payrollcasestudy.entities.PayCheck;

public interface PaymentMethod {
    void pay(PayCheck payCheck);
}
