package payrollcasestudy.paymentclassifications;

import payrollcasestudy.TimeCard;

import java.util.HashMap;
import java.util.Map;

public class HourlyPaymentClassification implements PaymentClassification{

    private Map<Integer, TimeCard> timeCardMap = new HashMap<Integer, TimeCard>();
    private double hourlyRate;

    public HourlyPaymentClassification(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public TimeCard getTimeCard(int date) {
        return timeCardMap.get(date);
    }

    public void addTimeCard(TimeCard timeCard) {
        timeCardMap.put(timeCard.getDate() ,timeCard);
    }
}
