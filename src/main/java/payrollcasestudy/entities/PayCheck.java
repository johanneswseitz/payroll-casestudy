package payrollcasestudy.entities;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class PayCheck {
    private double grossPay;
    private double deductions;
    private double netPay;
    private Calendar payPeriodStart;
    private Calendar payDate;
    private Map<String, String> fields = new HashMap<String, String>();

    public PayCheck(Calendar payPeriodStart, Calendar payPeriodEnd) {
        this.payPeriodStart = payPeriodStart;
        this.payDate = payPeriodEnd;
    }

    public Calendar getPayPeriodEnd() {
        return this.payDate;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public String getField(String fieldName) {
        return fields.get(fieldName);
    }

    public double getDeductions() {
        return deductions;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    public void setField(String fieldName, String value) {
        fields.put(fieldName, value);
    }

    public Calendar getPayPeriodStart() {
        return payPeriodStart;
    }
}
