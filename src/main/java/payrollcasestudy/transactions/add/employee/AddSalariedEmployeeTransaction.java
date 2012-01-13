package payrollcasestudy.transactions.add.employee;

import payrollcasestudy.paymentclassifications.PaymentClassification;
import payrollcasestudy.paymentclassifications.SalariedClassification;
import payrollcasestudy.paymentschedule.MonthlyPaymentSchedule;
import payrollcasestudy.paymentschedule.PaymentSchedule;

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
