package com.sogoodlabs.planner.data.common.events;

import java.io.Serializable;

public class Event {
    private EventType eventType;
    private String payload;

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
