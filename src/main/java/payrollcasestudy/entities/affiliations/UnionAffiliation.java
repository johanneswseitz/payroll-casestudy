package payrollcasestudy.entities.affiliations;

import payrollcasestudy.entities.ServiceCharge;

import java.util.HashMap;
import java.util.Map;

public class UnionAffiliation {
    private double dues;
    private Map<Integer, ServiceCharge> serviceCharges = new HashMap<Integer, ServiceCharge>();
    public static final UnionAffiliation NO_AFFILIATION = new UnionAffiliation(-1,0);
    private int memberId;

    public UnionAffiliation(int memberId, double dues) {
        this.memberId = memberId;
        this.dues = dues;
    }

    public ServiceCharge getServiceCharge(int date) {
        return serviceCharges.get(date);
    }

    public void addServiceCharge(Integer date, double amount) {
        this.serviceCharges.put(date, new ServiceCharge(amount));
    }

    public double getDues() {
        return dues;
    }

    public int getMemberId() {
        return memberId;
    }

}
