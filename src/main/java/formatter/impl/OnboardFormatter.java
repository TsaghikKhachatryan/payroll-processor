package formatter.impl;

import data.employee.EmployeeRecord;
import data.employee.EmployeeRecordBuilder;
import data.event.Event;
import data.event.EventType;
import formatter.Formatter;
import java.util.List;
import processor.DataProcessor;

public class OnboardFormatter implements Formatter {

    @Override
    public EmployeeRecord format(List<String> data) {
        var sequenceNumber = Integer.parseInt(data.get(0));
        var employeeId = data.get(1);
        var firstName = data.get(2);
        var latName = data.get(3);
        var designation = data.get(4);
        var eventType = EventType.fromValue(data.get(5));
        var eventValue = data.get(6);
        var eventDate = DataProcessor.getFormattedDate(data.get(7));
        var notes = data.get(8);
        var event = new Event(eventType, eventDate, eventValue, notes);
        return new EmployeeRecordBuilder()
                .setSequenceNumber(sequenceNumber)
                .setEmployeeId(employeeId)
                .setFirstName(firstName)
                .setLastName(latName)
                .setDesignation(designation)
                .setEvent(event)
                .build();
    }
}
