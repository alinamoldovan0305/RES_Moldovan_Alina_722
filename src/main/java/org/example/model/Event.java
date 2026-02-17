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

    public EventType getType() {
        return type;
    }

    public int getBasePoints(){return basePoints;}

    public int computedPoints() {
        return switch (type) {
            case  EVA-> basePoints +2*day;
            case SYSTEM_FAILURE -> basePoints -3 -day;
            case SCIENCE -> basePoints + (day% 4);
            case MEDICAL -> basePoints - 2 * (day % 3);
            case COMMUNICATION -> basePoints +5;

        };
    };

}
