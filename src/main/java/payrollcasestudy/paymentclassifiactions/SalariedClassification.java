package payrollcasestudy.paymentclassifiactions;

public class SalariedClassification implements PaymentClassification {
    private double salary;

    public SalariedClassification(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}
