package com.sogoodlabs.planner.data.common.events;

import java.io.Serializable;

public class Event {
    private EventType eventType;
    private Serializable payload;

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Serializable getPayload() {
        return payload;
    }

    public void setPayload(Serializable payload) {
        this.payload = payload;
    }
}
