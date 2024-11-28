package com.deameyesapps.GameLib;

public class IOEvent {
    enum EventType{
        NewFocus,
        KeyEvent
    }
    public GameObject newSelectedObject;
    public EventType eventType;
    public String keyValue;

    public IOEvent(GameObject newSelectedObject, EventType eventType, String keyValue)
    {
        this.newSelectedObject = newSelectedObject;
        this.eventType = eventType;
        this.keyValue = keyValue;
    }
}
