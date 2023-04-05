import static org.junit.jupiter.api.Assertions.*;

import data.employee.EmployeeRecord;
import data.event.Event;
import data.event.EventType;
import data.reports.EmployeeFinancialReport;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import processor.impl.CsvDataProcessor;
import processor.impl.TextDataProcessor;

class EmployeeRecordsTest {

    private static final String TXT_FILE_PATH = "src/main/resources/employeeRecord.txt";
    private static final String CSV_FILE_PATH = "src/main/resources/employeeRecord.csv";
    private static final String INVALID_FILE_PATH = "invalid";

    private final PayrollProcessor payrollProcessor = new PayrollProcessor();

    @BeforeEach
    void setUp() {
        List<EmployeeRecord> employeeRecords = new ArrayList<>();
        employeeRecords.add(new EmployeeRecord(1, "EMP1", "Bill", "Gates", "Software Engineer",
                new Event(EventType.ONBOARD, LocalDate.of(2023, Month.APRIL, 1), "04-10-2023", "Note")));
        employeeRecords.add(new EmployeeRecord(2, "EMP2", "Jane", "Smith", "QA",
                new Event(EventType.REIMBURSEMENT, LocalDate.of(2023, Month.APRIL, 6), "1000", "Traveling expenses")));
        employeeRecords.add(new EmployeeRecord(3, "EMP3", "Bob", "Jokin", "BA",
                new Event(EventType.EXIT, LocalDate.of(2023, Month.SEPTEMBER, 4), "11-04-2022", "Leaving for further study")));
        employeeRecords.add(new EmployeeRecord(4, "EMP2", "Jane", "Smith", "QA",
                new Event(EventType.BONUS, LocalDate.of(2023, Month.APRIL, 16), "1000", "Performance bonus of year 2022")));
        employeeRecords.add(new EmployeeRecord(5, "EMP1", "Bill", "Gates", "Software Engineer",
                new Event(EventType.SALARY, LocalDate.of(2023, Month.APRIL, 28), "3000", "Salary received")));
        employeeRecords.add(new EmployeeRecord(6, "EMP6", "Anna", "Ruth", "QA",
                new Event(EventType.SALARY, LocalDate.of(2023, Month.APRIL, 25), "5000", "Salary received")));
        payrollProcessor.setEmployeeRecords(employeeRecords);
    }

    @Test
    void processEmployeeRecord_validTxtFilePath() {
        var dataProcessorMock = Mockito.mock(TextDataProcessor.class);

       // payrollProcessor.processEmployeeRecord(TXT_FILE_PATH);
        dataProcessorMock.readEmployeeRecord(TXT_FILE_PATH);
        Mockito.verify(dataProcessorMock, Mockito.times(1)).readEmployeeRecord(TXT_FILE_PATH);
    }


    @Test
    void processEmployeeRecord_validCsvFilePath() {
        var dataProcessorMock = Mockito.mock(CsvDataProcessor.class);

        dataProcessorMock.readEmployeeRecord(CSV_FILE_PATH);
        Mockito.verify(dataProcessorMock, Mockito.times(1)).readEmployeeRecord(CSV_FILE_PATH);
    }

    @Test
    void processEmployeeRecord_invalidFilePath_throwsIllegalArgumentException() {
        var payrollProcessor = new PayrollProcessor();

        var exception = assertThrows(IllegalArgumentException.class,
                () -> payrollProcessor.processEmployeeRecord(INVALID_FILE_PATH));
        assertEquals("Invalid file path", exception.getMessage());
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
        assertEquals(1, result.size());
        assertEquals("EMP3", result.get(0).getEmployeeId());
        assertEquals("Bob", result.get(0).getFirstName());
    }

    @Test
    void testGetMonthlySalaryReport() {
        var employee1 = payrollProcessor.getEmployeeRecords().get(4);
        var employee2 = payrollProcessor.getEmployeeRecords().get(5);
        var salary2 = BigDecimal.valueOf(Long.parseLong(employee1.getEvent().getEventValue()));
        var salary1 = BigDecimal.valueOf(Long.parseLong(employee2.getEvent().getEventValue()));
        var monthlySalaryReport = salary1.add(salary2);

        var report = payrollProcessor.getMonthlySalaryReport(YearMonth.of(2023, Month.APRIL));

        assertEquals(monthlySalaryReport, report.getTotalSalary());
        assertEquals(2, report.getTotalEmployees());
    }

    @Test
    void testGetEmployeeWiseFinancialReport() {
        var employee1 = payrollProcessor.getEmployeeRecords().get(4);
        var employee2 = payrollProcessor.getEmployeeRecords().get(5);
        var salary2 = BigDecimal.valueOf(Long.parseLong(employee1.getEvent().getEventValue()));
        var salary1 = BigDecimal.valueOf(Long.parseLong(employee2.getEvent().getEventValue()));

        Map<String, EmployeeFinancialReport> report = payrollProcessor.getEmployeeWiseFinancialReport();

        assertEquals(salary2, report.get(employee1.getEmployeeId()).getTotalAmountPaid());
        assertEquals(salary1, report.get(employee2.getEmployeeId()).getTotalAmountPaid());
    }

    @Test
    void testGetMonthlyAmountReleasedReport() {
        var report = payrollProcessor.getMonthlyAmountReleasedReport(YearMonth.of(2023, Month.APRIL));
        assertEquals(getExpectedTotalAmount(), report.getTotalAmount());
    }

    @Test
    public void testGetYearlyFinancialReport() {
        var result = payrollProcessor.getYearlyFinancialReport(2023);
        var report1 = result.get(0);

        var employeeRecord = payrollProcessor.getEmployeeRecords().get(0);
        assertEquals(payrollProcessor.getEmployeeRecords().size(), result.size());
        assertEquals(employeeRecord.getEmployeeId(), report1.getEmployeeId());
        assertEquals(employeeRecord.getEvent().getEventDate(), report1.getEventDate());
        assertEquals(employeeRecord.getEvent().getEventValue(), report1.getEventValue());
    }

    private BigDecimal getExpectedTotalAmount() {
        return BigDecimal.valueOf(payrollProcessor.getEmployeeRecords().stream()
                .filter(record -> List.of(EventType.SALARY, EventType.BONUS, EventType.REIMBURSEMENT)
                        .contains(record.getEvent().getEventType()))
                .mapToLong(record -> Long.parseLong(record.getEvent().getEventValue()))
                .sum());
    }
}