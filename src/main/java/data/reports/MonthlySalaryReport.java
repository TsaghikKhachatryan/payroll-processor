package data.reports;

import java.math.BigDecimal;

public class MonthlySalaryReport {
    private int month;
    private BigDecimal totalSalary;
    private int totalEmployees;

    public MonthlySalaryReport(int month, BigDecimal totalSalary, int totalEmployees) {
        this.month = month;
        this.totalSalary = totalSalary;
        this.totalEmployees = totalEmployees;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setTotalSalary(BigDecimal totalSalary) {
        this.totalSalary = totalSalary;
    }

    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public BigDecimal getTotalSalary() {
        return totalSalary;
    }

    public int getTotalEmployees() {
        return totalEmployees;
    }
}
