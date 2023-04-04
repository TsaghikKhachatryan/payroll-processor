package data.reports;

import java.math.BigDecimal;
import java.time.Month;

public class MonthlyAmountReport {
    private Month month;
    private BigDecimal totalAmount;
    private int totalEmployees;

    public MonthlyAmountReport(Month month, BigDecimal totalAmount, int totalEmployees) {
        this.month = month;
        this.totalAmount = totalAmount;
        this.totalEmployees = totalEmployees;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public int getTotalEmployees() {
        return totalEmployees;
    }
}
