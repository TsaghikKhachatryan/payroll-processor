package data.reports;

import java.math.BigDecimal;

public class EmployeeFinancialReport {

    private String employeeId;
    private String firstname;
    private String lastName;
    private BigDecimal totalAmountPaid;

    public EmployeeFinancialReport(String employeeId, String firstname, String lastName, BigDecimal totalAmountPaid) {
        this.employeeId = employeeId;
        this.firstname = firstname;
        this.lastName = lastName;
        this.totalAmountPaid = totalAmountPaid;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public void setTotalAmountPaid(BigDecimal totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
    }
}
