package data.event;

import java.time.LocalDate;

public class Event {

    private EventType eventType;
    private LocalDate eventDate;
    private String eventValue;
    private String notes;

    public Event(EventType eventType, LocalDate eventDate, String eventValue, String notes) {
        this.eventType = eventType;
        this.eventDate = eventDate;
        this.eventValue = eventValue;
        this.notes = notes;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
