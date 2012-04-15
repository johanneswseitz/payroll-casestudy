package payrollcasestudy;

import payrollcasestudy.paymentclassifications.PaymentClassification;
import payrollcasestudy.paymentmethods.PaymentMethod;
import payrollcasestudy.paymentschedule.PaymentSchedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Employee {
    private PaymentClassification paymentClassification;
    private PaymentSchedule paymentSchedule;
    private PaymentMethod paymentMethod;
    private int employeeId;
    private String name;
    private String address;
    private List<Affiliation> unionAffiliations = new ArrayList<Affiliation>();

    public Employee(int employeeId, String name, String address) {
        this.employeeId = employeeId;
        this.name = name;
        this.address = address;
    }

    public PaymentClassification getPaymentClassification() {
        return paymentClassification;
    }

    public void setPaymentClassification(PaymentClassification paymentClassification) {
        this.paymentClassification = paymentClassification;
    }

    public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getName() {
        return name;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentSchedule getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setUnionAffiliation(UnionAffiliation unionAffiliation) {
        this.unionAffiliations.add(unionAffiliation);
    }

    public List<Affiliation> getAffiliations() {
        return unionAffiliations;
    }
}
