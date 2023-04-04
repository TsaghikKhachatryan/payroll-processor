import data.employee.EmployeeRecord;
import data.event.EventType;
import data.reports.EmployeeFinancialReport;
import data.reports.MonthlySalaryReport;
import data.reports.YearlyFinancialReport;
import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import processor.DataProcessor;
import processor.impl.CsvDataProcessor;
import processor.impl.TextDataProcessor;
import data.reports.MonthlyAmountReport;

public class PayrollProcessor {

    private final Logger logger = Logger.getLogger(PayrollProcessor.class.getName());
    private List<EmployeeRecord> employeeRecords = new ArrayList<>();

    public void processEmployeeRecord(String filePath) {
        var fileFormat = getFileExtension(filePath);

        DataProcessor processor = null;
        if (fileFormat.equalsIgnoreCase("txt")) {
            processor = new TextDataProcessor();
        } else if (fileFormat.equalsIgnoreCase("csv")) {
            processor = new CsvDataProcessor();
        } else {
            throw new IllegalArgumentException(String.format("Invalid file format: %s", fileFormat));
        }

        employeeRecords.addAll(processor.readEmployeeRecord(filePath));
        logger.log(Level.INFO, "Employee Details successfully has been processed!");
    }

    public int getTotalNumberOfEmployees() {
        return employeeRecords.size();
    }

    public List<EmployeeRecord> getEmployeesJoinedInMonth(YearMonth date) {
        return employeeRecords.stream()
                .filter(e -> e.getEvent().getEventType() == EventType.ONBOARD
                        && e.getOnboardDate().getYear() == date.getYear()
                        && e.getOnboardDate().getMonth() == date.getMonth())
                .collect(Collectors.toUnmodifiableList());
    }

    public List<EmployeeRecord> getEmployeesExitedInMonth(YearMonth date) {
        return employeeRecords.stream()
                .filter(e -> e.getEvent().getEventType() == EventType.EXIT
                        && e.getOnboardDate().getYear() == date.getYear()
                        && e.getOnboardDate().getMonth() == date.getMonth())
                .collect(Collectors.toUnmodifiableList());
    }

    public MonthlySalaryReport getMonthlySalaryReport(YearMonth date) {
        var totalAmount = BigDecimal.ZERO;
        var totalEmployees = 0;
        for (EmployeeRecord record : employeeRecords) {
            var eventDate = YearMonth.from(record.getEvent().getEventDate());
            var eventType = record.getEvent().getEventType();
            if (EventType.SALARY == eventType && eventDate.equals(date)) {
                totalAmount = totalAmount.add(new BigDecimal(record.getEvent().getEventValue()));
                totalEmployees++;
            }
        }
        return new MonthlySalaryReport(date.getMonth(), totalAmount, totalEmployees);
    }

    public Map<String, EmployeeFinancialReport> getEmployeeWiseFinancialReport() {
        var salaryReport = new HashMap<String, EmployeeFinancialReport>();
        for (EmployeeRecord record : employeeRecords) {
            if (record.getEvent().getEventType() == EventType.SALARY) {
                var employee = record.getEmployeeId();
                if (salaryReport.containsKey(employee)) {
                    var report = salaryReport.get(employee);
                    report.setTotalAmountPaid(report.getTotalAmountPaid().add(record.getSalaryAmount()));
                } else {
                    var report = new EmployeeFinancialReport(record.getEmployeeId(),
                            record.getFirstName(), record.getLastName(), record.getSalaryAmount());
                    salaryReport.put(employee, report);
                }
            }
        }
        return salaryReport;
    }

    public MonthlyAmountReport getMonthlyAmountReleasedReport(YearMonth date) {
        var totalAmount = BigDecimal.ZERO;
        var totalEmployees = 0;
        for (EmployeeRecord record : employeeRecords) {
            var eventDate = YearMonth.from(record.getEvent().getEventDate());
            var eventType = record.getEvent().getEventType();
            if (isRightEventTypeAndDate(eventType, eventDate, date)) {
                totalAmount = totalAmount.add(new BigDecimal(record.getEvent().getEventValue()));
                totalEmployees++;
            }
        }
        return new MonthlyAmountReport(date.getMonth(), totalAmount, totalEmployees);
    }

    public List<YearlyFinancialReport> getYearlyFinancialReport(int year) {
        return employeeRecords.stream()
                .filter(employeeRecord -> employeeRecord.getEvent().getEventDate().getYear() == year)
                .map(record -> new YearlyFinancialReport(record.getEvent(), record.getEmployeeId(),
                        record.getEvent().getEventDate(),
                        record.getEvent().getEventValue()))
                .collect(Collectors.toList());
    }

    public void setEmployeeRecords(List<EmployeeRecord> employeeRecords) {
        this.employeeRecords = employeeRecords;
    }

    public List<EmployeeRecord> getEmployeeRecords() {
        return employeeRecords;
    }

    private boolean isRightEventTypeAndDate(EventType eventType, YearMonth eventYear, YearMonth date) {
        return List.of(EventType.SALARY, EventType.BONUS, EventType.REIMBURSEMENT)
                .contains(eventType) && eventYear.equals(date);
    }

    private String getFileExtension(String filePath) {
        return Optional.ofNullable(filePath)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filePath.lastIndexOf(".") + 1))
                .orElseThrow(() -> new IllegalArgumentException("Invalid file path"));
    }
}