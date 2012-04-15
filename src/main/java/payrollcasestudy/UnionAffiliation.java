package payrollcasestudy;

import java.util.HashMap;
import java.util.Map;

public class UnionAffiliation implements Affiliation {
    private double rate;
    private Map<Integer, ServiceCharge> serviceCharges = new HashMap<Integer, ServiceCharge>();

    public UnionAffiliation(double rate) {
        this.rate = rate;
    }

    public ServiceCharge getServiceCharge(int date) {
        return serviceCharges.get(date);
    }

    public void addServiceCharge(Integer date, double amount) {
        this.serviceCharges.put(date, new ServiceCharge(amount));
    }
}
