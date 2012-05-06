package payrollcasestudy.entities.paymentschedule;

import java.util.Calendar;

public class BiweeklyPaymentSchedule implements PaymentSchedule{
    @Override
    public boolean isPayDate(Calendar payDate) {
        boolean evenCalendarWeek = payDate.get(Calendar.WEEK_OF_YEAR) % 2 == 0;
        return payDate.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && evenCalendarWeek;
    }

    @Override
    public Calendar getPayPeriodStartDate(Calendar payDate) {
        Calendar payPeriodStartDate = (Calendar) payDate.clone();
        payPeriodStartDate.add(Calendar.DAY_OF_MONTH, -13);
        return payPeriodStartDate;
    }
}
