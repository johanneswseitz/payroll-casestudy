package payrollcasestudy.entities;

import java.util.Calendar;

public class TimeCard {
    private final Calendar date;
    private final double hours;

    public TimeCard(Calendar date, double hours) {
        this.date = date;
        this.hours = hours;
    }

    public Calendar getDate() {
        return date;
    }

    public double getHours() {
        return hours;
    }
}
