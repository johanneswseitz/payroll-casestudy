package payrollcasestudy;

import java.util.HashMap;
import java.util.Map;

/**
 * Listing 19-3
 * Listing 19-4
 */
public class PayrollDatabase {
    public static PayrollDatabase globalPayrollDatabase = new PayrollDatabase();

    private Map<Integer, Employee> employees = new HashMap<Integer, Employee>();

    public Employee getEmployee(int employeeId) {
        return employees.get(employeeId);
    }

    public void addEmployee(int employeeId, Employee employee) {
        employees.put(employeeId, employee);
    }

    public void clear(){
        employees.clear();
    }
}
