package payrollcasestudy.entities.paymentmethods;

import payrollcasestudy.entities.PayCheck;

public class HoldMethod implements PaymentMethod{
    @Override
    public void pay(PayCheck payCheck) {
        payCheck.setField("Disposition", "Hold");
    }
}
