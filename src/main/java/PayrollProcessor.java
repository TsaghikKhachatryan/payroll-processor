import data.employee.EmployeeRecord;
import data.event.EventType;
import data.reports.EmployeeFinancialReport;
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
import data.reports.MonthlySalaryReport;

public class PayrollProcessor {
    Logger logger = Logger.getLogger(PayrollProcessor.class.getName());


    private List<EmployeeRecord> employeeRecords = new ArrayList<>();

    public void setEmployeeRecords(List<EmployeeRecord> employeeRecords) {
        this.employeeRecords = employeeRecords;
    }

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

    public Map<Integer, MonthlySalaryReport> getMonthlySalaryReport(YearMonth date) {
        var salaryReport = new HashMap<Integer, MonthlySalaryReport>();
        for (EmployeeRecord record : employeeRecords) {
            var event = record.getEvent();
            var eventYear = YearMonth.from(event.getEventDate()).getYear();
            if (event.getEventType() == EventType.SALARY && eventYear == date.getYear()) {
                var month = event.getEventDate().getMonth().getValue();
                if (salaryReport.containsKey(month)) {
                    var report = salaryReport.get(month);
                    report.setTotalSalary(report.getTotalSalary().add(record.getSalaryAmount()));
                    report.setTotalEmployees(report.getTotalEmployees() + 1);
                } else {
                    var report = new MonthlySalaryReport(month, record.getSalaryAmount(), 1);
                    salaryReport.put(month, report);
                }
            }
        }
        return salaryReport;
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

    public Map<Integer, MonthlyAmountReport> getMonthlyAmountReleasedReport(YearMonth date) {
        Map<Integer, MonthlyAmountReport> amountReport = new HashMap<>();
        for (EmployeeRecord record : employeeRecords) {
            var eventType = record.getEvent().getEventType();
            var eventYear = YearMonth.from(record.getEvent().getEventDate()).getYear();
            if (isRightEventAndYear(eventType, eventYear, date)) {
                var month = record.getEvent().getEventDate().getMonth().getValue();
                if (amountReport.containsKey(month)) {
                    var report = amountReport.get(month);
                    report.setTotalAmount(report.getTotalAmount().add(record.getSalaryAmount()));
                    report.setTotalEmployees(report.getTotalEmployees() + 1);
                } else {
                    var report = new MonthlyAmountReport(month, record.getSalaryAmount(), 1);
                    amountReport.put(month, report);
                }
            }
        }
        return amountReport;

    }

    public List<YearlyFinancialReport> getYearlyFinancialReport(int year) {
        return employeeRecords.stream()
                .filter(employeeRecord -> employeeRecord.getEvent().getEventDate().getYear() == year)
                .map(record -> new YearlyFinancialReport(record.getEvent(), record.getEmployeeId(),
                        record.getEvent().getEventDate(),
                        record.getEvent().getEventValue()))
                .collect(Collectors.toList());
    }

    private boolean isRightEventAndYear(EventType eventType, int eventYear, YearMonth date) {
        return List.of(EventType.SALARY, EventType.BONUS, EventType.REIMBURSEMENT)
                .contains(eventType) && eventYear == date.getYear();
    }

    private String getFileExtension(String filePath) {
        return Optional.ofNullable(filePath)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filePath.lastIndexOf(".") + 1))
                .orElseThrow(() -> new IllegalArgumentException("Invalid file path"));
    }
}