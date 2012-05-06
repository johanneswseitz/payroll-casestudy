package payrollcasestudy.entities;

import java.util.Calendar;

public class ServiceCharge {
    private Double amount;
    private Calendar date;

    public ServiceCharge(Calendar date, double amount) {
        this.amount = amount;
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public Calendar getDate() {
        return date;
    }
}
