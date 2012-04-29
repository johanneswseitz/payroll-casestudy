package payrollcasestudy.transactions.add;

import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.entities.paymentclassifications.SalariedClassification;
import payrollcasestudy.entities.paymentschedule.MonthlyPaymentSchedule;
import payrollcasestudy.entities.paymentschedule.PaymentSchedule;

public class AddSalariedEmployeeTransaction extends AddEmployeeTransaction{

    private double salary;

    public AddSalariedEmployeeTransaction(int employeeId, String name, String address, double salary) {
        super(employeeId, name, address);
        this.salary = salary;
    }

    @Override
    protected PaymentSchedule getPaymentSchedule() {
        return new MonthlyPaymentSchedule();
    }

    @Override
    protected PaymentClassification getPaymentClassification() {
        return new SalariedClassification(salary);
    }
}
