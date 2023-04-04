import static org.junit.jupiter.api.Assertions.*;

import data.employee.EmployeeRecord;
import data.event.Event;
import data.event.EventType;
import data.reports.EmployeeFinancialReport;
import data.reports.MonthlySalaryReport;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeRecordsTest {

    private PayrollProcessor payrollProcessor = new PayrollProcessor();

    @BeforeEach
    void setUp() {
        List<EmployeeRecord> employeeRecords = new ArrayList<>();
        employeeRecords.add(new EmployeeRecord(1, "EMP1", "Bill", "Gates", "Software Engineer",
                new Event(EventType.ONBOARD, LocalDate.of(2023, Month.APRIL, 1), "04-10-2023", "Note")));
        employeeRecords.add(new EmployeeRecord(2, "EMP2", "Jane", "Smith", "QA",
                new Event(EventType.REIMBURSEMENT, LocalDate.of(2023, Month.JANUARY, 6), "10-10-2022", "Traveling expenses")));
        employeeRecords.add(new EmployeeRecord(3, "EMP3", "Bob", "Jokin", "BA",
                new Event(EventType.EXIT, LocalDate.of(2023, Month.SEPTEMBER, 4), "11-04-2022", "Leaving for further study")));
        employeeRecords.add(new EmployeeRecord(4, "EMP2", "Jane", "Smith", "QA",
                new Event(EventType.BONUS, LocalDate.of(2023, Month.NOVEMBER, 16), "1000", "Performance bonus of year 2022")));
        employeeRecords.add(new EmployeeRecord(5, "EMP1", "Bill", "Gates", "Software Engineer",
                new Event(EventType.SALARY, LocalDate.of(2023, Month.APRIL, 28), "3000", "Salary received")));
        employeeRecords.add(new EmployeeRecord(6, "EMP6", "Anna", "Ruth", "QA",
                new Event(EventType.SALARY, LocalDate.of(2023, Month.APRIL, 25), "5000", "Salary received")));
        payrollProcessor.setEmployeeRecords(employeeRecords);
    }

    @Test
    void testGetTotalNumberOfEmployees() {
        assertEquals(6, payrollProcessor.getTotalNumberOfEmployees());
    }

    @Test
    void testGetEmployeesJoinedInMonth() {
        List<EmployeeRecord> result = payrollProcessor.getEmployeesJoinedInMonth(YearMonth.of(2023, Month.APRIL));

        assertEquals(1, result.size());
        assertEquals("EMP1", result.get(0).getEmployeeId());
        assertEquals("Bill", result.get(0).getFirstName());

    }

    @Test
    void testGetEmployeesExitedInMonth() {
        List<EmployeeRecord> result = payrollProcessor.getEmployeesExitedInMonth(YearMonth.of(2023, Month.SEPTEMBER));
        // Verify
        assertEquals(1, result.size());
        assertEquals("EMP3", result.get(0).getEmployeeId());
        assertEquals("Bob", result.get(0).getFirstName());
    }

    @Test
    void testGetMonthlySalaryReport() {
        int month = 4;
        int salary = 8000;
        Map<Integer, MonthlySalaryReport> report = payrollProcessor.getMonthlySalaryReport(YearMonth.of(2023, Month.JANUARY));
        assertEquals(BigDecimal.valueOf(salary), report.get(month).getTotalSalary());
        assertEquals(2, report.get(month).getTotalEmployees());
    }

    @Test
    void testGetEmployeeWiseFinancialReport() {
        Map<String, EmployeeFinancialReport> report = payrollProcessor.getEmployeeWiseFinancialReport();
        var salary1 = 3000;
        var salary2 = 5000;
        assertEquals(BigDecimal.valueOf(3000), report.get("EMP1").getTotalAmountPaid());
        assertEquals(BigDecimal.valueOf(5000), report.get("EMP6").getTotalAmountPaid());
    }
//
//    @Test
//    void testGetMonthlyAmountReleasedReport() {
//        Map<Integer, MonthlyAmountReport> report = employeeRecords.getMonthlyAmountReleasedReport(YearMonth.of(2022, Month.JANUARY));
//        assertEquals(BigDecimal.valueOf(300
}