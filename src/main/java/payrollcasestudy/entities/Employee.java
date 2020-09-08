package payrollcasestudy.entities;

import payrollcasestudy.entities.affiliations.UnionAffiliation;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.entities.paymentmethods.PaymentMethod;
import payrollcasestudy.entities.paymentschedule.PaymentSchedule;

import java.util.Calendar;

public class Employee {

        private PaymentClassification paymentClassification;
        private PaymentSchedule paymentSchedule;
        private PaymentMethod paymentMethod;
        private final int employeeId;
        private String name;
        private String address;
        private UnionAffiliation unionAffiliation = UnionAffiliation.NO_AFFILIATION;

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
                this.unionAffiliation = unionAffiliation;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public UnionAffiliation getUnionAffiliation() {
                return unionAffiliation;
        }

        public boolean isPayDate(Calendar payDate) {
                return paymentSchedule.isPayDate(payDate);
        }

        public Calendar getPayPeriodStartDay(Calendar payDate) {
                return paymentSchedule.getPayPeriodStartDate(payDate);
        }

        public void payDay(PayCheck payCheck) {
                double grossPay = paymentClassification.calculatePay(payCheck);
                double deductions = unionAffiliation.calculateDeduction(payCheck);
                double netPay = grossPay - deductions;
                payCheck.setGrossPay(grossPay);
                payCheck.setDeductions(deductions);
                payCheck.setNetPay(netPay);
                paymentMethod.pay(payCheck);
        }

}
