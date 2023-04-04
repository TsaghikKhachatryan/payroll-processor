package data.reports;

import java.math.BigDecimal;
import java.time.Month;

public class MonthlySalaryReport {
    private Month month;
    private BigDecimal totalSalary;
    private int totalEmployees;

    public MonthlySalaryReport(Month month, BigDecimal totalSalary, int totalEmployees) {
        this.month = month;
        this.totalSalary = totalSalary;
        this.totalEmployees = totalEmployees;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
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
