package payrollcasestudy.entities.affiliations;

import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.entities.ServiceCharge;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static payrollcasestudy.entities.paymentclassifications.PaymentClassification.isInPayPeriod;

public class UnionAffiliation {
    private double dues;
    private Map<Calendar, ServiceCharge> serviceCharges = new HashMap<Calendar, ServiceCharge>();
    public static final UnionAffiliation NO_AFFILIATION = new UnionAffiliation(-1,0);
    private int memberId;

    public UnionAffiliation(int memberId, double dues) {
        this.memberId = memberId;
        this.dues = dues;
    }

    public ServiceCharge getServiceCharge(Calendar date) {
        return serviceCharges.get(date);
    }

    public void addServiceCharge(Calendar date, double amount) {
        this.serviceCharges.put(date, new ServiceCharge(date, amount));
    }

    public double getDues() {
        return dues;
    }

    public int getMemberId() {
        return memberId;
    }

    public double calculateDeduction(PayCheck payCheck) {
        double totalDeductions = 0;
        totalDeductions += numberOfFridaysInPayPeriod(payCheck.getPayPeriodStart(), payCheck.getPayPeriodEnd()) * dues;
        for (ServiceCharge serviceCharge : serviceCharges.values()){
            if (isInPayPeriod(serviceCharge.getDate(), payCheck)){
                totalDeductions += serviceCharge.getAmount();
            }
        }
        return totalDeductions;
    }

    private int numberOfFridaysInPayPeriod(Calendar payPeriodStart, Calendar payPeriodEnd) {
        int numberOfFridays = 0;
        while (!payPeriodStart.after(payPeriodEnd)){
            if (payPeriodStart.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
                numberOfFridays++;
            }
            payPeriodStart.add(Calendar.DAY_OF_MONTH, 1);
        }
        return numberOfFridays;
    }
}
