package data.reports;

import data.event.Event;
import java.time.LocalDate;

public class YearlyFinancialReport {
    private Event event;
    private String employeeId;
    private LocalDate eventDate;
    private String eventValue;

    public YearlyFinancialReport(Event event, String employeeId, LocalDate eventDate, String eventValue) {
        this.event = event;
        this.employeeId = employeeId;
        this.eventDate = eventDate;
        this.eventValue = eventValue;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventValue() {
        return eventValue;
    }

    public void setEventValue(String eventValue) {
        this.eventValue = eventValue;
    }

    @Override
    public String toString() {
        return "YearlyFinancialReport{" +
                "event=" + event +
                ", employeeId='" + employeeId + '\'' +
                ", eventDate=" + eventDate +
                ", eventValue='" + eventValue + '\'' +
                '}';
    }
}
