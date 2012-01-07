package payrollcasestudy.transactions.add.employees;

import payrollcasestudy.paymentclassifiactions.PaymentClassification;
import payrollcasestudy.paymentclassifiactions.SalariedClassification;
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
