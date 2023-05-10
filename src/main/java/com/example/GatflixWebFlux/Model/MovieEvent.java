package com.example.GatflixWebFlux.Model;

import java.util.Objects;

public class MovieEvent {

    private Long eventID;
    private String eventType;

    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public MovieEvent(Long eventID, String eventType) {
        this.eventID = eventID;
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "FilmeEvent{" +
                "eventID=" + eventID +
                ", eventType='" + eventType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieEvent that = (MovieEvent) o;
        return Objects.equals(eventID, that.eventID) && Objects.equals(eventType, that.eventType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventID, eventType);
    }

    public MovieEvent() {
        super();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
