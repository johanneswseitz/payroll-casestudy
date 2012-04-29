package payrollcasestudy.entities;

public class SalesReceipt {
    private int date;
    private double amount;

    public SalesReceipt(int date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    public int getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }
}
