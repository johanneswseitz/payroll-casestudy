package payrollcasestudy.transactions.change;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.transactions.Transaction;

public abstract class ChangeEmployeeTransaction implements Transaction {

    PayrollDatabase database = PayrollDatabase.globalPayrollDatabase;
    private int employeeId;

    public ChangeEmployeeTransaction(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public void execute() {
        Employee employee = database.getEmployee(employeeId);
        changeEmployee(employee);
    }

    public abstract void changeEmployee(Employee employee);
}
