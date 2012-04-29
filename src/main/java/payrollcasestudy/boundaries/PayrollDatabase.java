package payrollcasestudy.boundaries;

import payrollcasestudy.entities.Employee;

import java.util.*;

/**
 * Listing 19-3
 * Listing 19-4
 */
public class PayrollDatabase {
    public static PayrollDatabase globalPayrollDatabase = new PayrollDatabase();

    private Map<Integer, Employee> employees = new HashMap<Integer, Employee>();
    public Map<Integer, Employee> unionMembers = new HashMap<Integer, Employee>();


    public Employee getEmployee(int employeeId) {
        return employees.get(employeeId);
    }

    public void addEmployee(int employeeId, Employee employee) {
        employees.put(employeeId, employee);
    }

    public void clear(){
        employees.clear();
        unionMembers.clear();
    }

    public void deleteEmployee(int employeeId) {
        employees.put(employeeId, null);
    }

    public Employee getUnionMember(int memberId) {
        return unionMembers.get(memberId);
    }

    public void addUnionMember(int memberId, Employee employee) {
        unionMembers.put(memberId, employee);
    }

    public void deleteUnionMember(int memberId) {
        unionMembers.remove(memberId);
    }

    public Set<Integer> getAllEmployeeIds() {
        return employees.keySet();
    }
}
