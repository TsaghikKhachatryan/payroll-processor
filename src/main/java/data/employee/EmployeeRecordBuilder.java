package data.employee;

import data.event.Event;

public class EmployeeRecordBuilder {
    private Integer sequenceNumber;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String designation;
    private Event event;

    public EmployeeRecordBuilder setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
        return this;
    }

    public EmployeeRecordBuilder setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public EmployeeRecordBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public EmployeeRecordBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public EmployeeRecordBuilder setDesignation(String designation) {
        this.designation = designation;
        return this;
    }

    public EmployeeRecordBuilder setEvent(Event event) {
        this.event = event;
        return this;
    }

    public EmployeeRecord build() {
        return new EmployeeRecord(sequenceNumber, employeeId, firstName, lastName, designation, event);
    }
}
