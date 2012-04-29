package payrollcasestudy.transactions.change;

import payrollcasestudy.entities.Employee;

public class ChangeAddressTransaction extends ChangeEmployeeTransaction{
    private String newAddress;

    public ChangeAddressTransaction(int employeeId, String newAddress) {
        super(employeeId);
        this.newAddress = newAddress;
    }

    @Override
    public void changeEmployee(Employee employee) {
        employee.setAddress(this.newAddress);
    }
}
