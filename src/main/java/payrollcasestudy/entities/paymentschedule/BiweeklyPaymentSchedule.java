package payrollcasestudy.entities.paymentschedule;

import java.util.Calendar;

public class BiweeklyPaymentSchedule implements PaymentSchedule{
    @Override
    public boolean isPayDate(Calendar payDate) {
        return false;
    }
}
