package payrollcasestudy.paymentclassifiactions;

public class HourlyPaymentClassification implements PaymentClassification{
    private double hourlyRate;

    public HourlyPaymentClassification(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }
}
