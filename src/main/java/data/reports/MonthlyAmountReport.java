package data.reports;

import java.math.BigDecimal;

public class MonthlyAmountReport {
    private Integer month;
    private BigDecimal totalAmount;
    private int totalEmployees;

    public MonthlyAmountReport(Integer month, BigDecimal totalAmount, int totalEmployees) {
        this.month = month;
        this.totalAmount = totalAmount;
        this.totalEmployees = totalEmployees;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public BigDecimal getTotalAmount() {
        return getTotalAmount();
    }

    public int getTotalEmployees() {
        return totalEmployees;
    }
}
