package processor;

import data.employee.EmployeeRecord;
import data.event.EventType;
import exception.NoSuchEventTypeException;
import formatter.impl.BonusFormatter;
import formatter.impl.ExitFormatter;
import formatter.Formatter;
import formatter.impl.OnboardFormatter;
import formatter.impl.ReimbursementFormatter;
import formatter.impl.SalaryFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public interface DataProcessor {

    String DATE_FORMAT = "MM-dd-yyyy";
    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    List<EmployeeRecord> readEmployeeRecord(String filePath);

    List<String> splitAndTrimEmployeeRecord(String data);

    static LocalDate getFormattedDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    static Formatter extractFormatterType(List<String> employeeRecord) {
        var eventType = findEventType(employeeRecord);
        switch (eventType) {
            case "ONBOARD":
                return new OnboardFormatter();
            case "SALARY":
                return new SalaryFormatter();
            case "BONUS":
                return new BonusFormatter();
            case "EXIT":
                return new ExitFormatter();
            case "REIMBURSEMENT":
                return new ReimbursementFormatter();
            default:
                throw new NoSuchEventTypeException(String.format("Unrecognized event type: %s", eventType));
        }
    }

    private static String findEventType(List<String> employeeRecord) {
        return employeeRecord.stream()
                .map(String::trim)
                .filter(data -> Arrays.stream(EventType.values())
                        .anyMatch(eType -> data.equalsIgnoreCase(eType.name())))
                .findFirst()
                .orElseThrow(() -> new NoSuchEventTypeException("Employee Record does not contain any event type"));
    }
}
