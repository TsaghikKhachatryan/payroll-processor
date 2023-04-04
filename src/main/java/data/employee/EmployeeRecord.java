package data.employee;

import data.event.Event;
import java.math.BigDecimal;
import java.time.YearMonth;

public class EmployeeRecord {

    private Integer sequenceNumber;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String designation;
    private Event event;

    public EmployeeRecord(Integer sequenceNumber, String employeeId, String firstName, String lastName, String designation,
                          Event event) {
        this.sequenceNumber = sequenceNumber;
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.designation = designation;
        this.event = event;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public YearMonth getOnboardDate() {
        return YearMonth.from(event.getEventDate());
    }

    public BigDecimal getSalaryAmount() {
        return new BigDecimal(event.getEventValue());

    }

    @Override
    public String toString() {
        return "EmployeeRecord{" +
                "employeeId='" + employeeId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", designation='" + designation + '\'' +
                ", event=" + event +
                '}';
    }
}