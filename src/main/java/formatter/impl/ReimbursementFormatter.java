package formatter.impl;

import data.employee.EmployeeRecord;
import data.employee.EmployeeRecordBuilder;
import data.event.Event;
import data.event.EventType;
import formatter.Formatter;
import java.util.List;
import processor.DataProcessor;

public class ReimbursementFormatter implements Formatter {

    @Override
    public EmployeeRecord format(List<String> data) {
        var sequenceNumber = Integer.parseInt(data.get(0));
        var employeeId = data.get(1);
        var eventType = EventType.fromValue(data.get(2));
        var eventValue = data.get(3);
        var eventDate = DataProcessor.getFormattedDate(data.get(4));
        var notes = data.get(5);
        var event = new Event(eventType, eventDate, eventValue, notes);
        return new EmployeeRecordBuilder()
                .setSequenceNumber(sequenceNumber)
                .setEmployeeId(employeeId)
                .setEvent(event)
                .build();
    }
}
