package data.reports;

import java.math.BigDecimal;

public class EmployeeFinancialReport {

    private String employeeId;
    private BigDecimal totalAmountPaid;

    public EmployeeFinancialReport(String employeeId,BigDecimal totalAmountPaid) {
        this.employeeId = employeeId;
        this.totalAmountPaid = totalAmountPaid;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public BigDecimal getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public void setTotalAmountPaid(BigDecimal totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
    }

    @Override
    public String toString() {
        return "EmployeeFinancialReport{" +
                "employeeId='" + employeeId + '\'' +
                ", totalAmountPaid=" + totalAmountPaid +
                '}';
    }
}
