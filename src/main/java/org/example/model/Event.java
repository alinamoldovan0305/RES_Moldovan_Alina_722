package org.example.model;

public class Event {
//    "id": 22,
//            "astronautId": 15,
//            "day": 11,
//            "type": "EVA",
//            "basePoints": 5
    private int id;
    private int astronautId;
    private int day;
    private EventType type;
    private int basePoints;

    public Event() {}
    public int getId(){return id;}
    public int getAstronautId(){return astronautId;}
    public int getDay(){return day;}

    public EventType getEventType() {
        return type;
    }

    public int getBasePoints(){return basePoints;}

}
