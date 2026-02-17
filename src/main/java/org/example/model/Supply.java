package org.example.model;

public class Supply {
//        "id": 10,
//                "astronautId": 15,
//                "type": "FOOD",
//                "value": 5
    private int id;
    private int astronautId;
    private SupplyType type;
    private int value;

    public Supply() {}

    public int getId(){return id;}
    public int getAstronautId(){return astronautId;}
    public SupplyType getSupplyType() {
        return type;
    }
    public int getValue(){return value;}
}
