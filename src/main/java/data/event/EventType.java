package data.event;

import exception.NoSuchEventTypeException;
import java.util.Arrays;

public enum EventType {
    ONBOARD,
    SALARY,
    BONUS,
    EXIT,
    REIMBURSEMENT;

    public static EventType fromValue(String value) {
        return Arrays.stream(EventType.values())
                .filter(event -> event.name().equals(value))
                .findFirst()
                .orElseThrow(() -> new NoSuchEventTypeException("Employee Details does not contain any event type"));
    }
}
