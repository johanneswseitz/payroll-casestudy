package payrollcasestudy.entities.paymentschedule;

import java.util.Calendar;

public class WeeklyPaymentSchedule implements PaymentSchedule{
    @Override
    public boolean isPayDate(Calendar payDate) {
        return false;
    }
}
