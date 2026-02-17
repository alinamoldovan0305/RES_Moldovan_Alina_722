package org.example.model;

public class Astronaut {
//    "id": 12,
//            "name": "Horia Pascu",
//            "spacecraft": "Dragon",
//            "status": "INACTIVE",
//            "experienceLevel": 6
    private int id;
    private String name;
    private String spacecraft;
    private Status status;
    private int experienceLevel;

    public Astronaut() {}
    public int getId(){return id;}
    public String getName(){return name;}
    public String getSpacecraft(){return spacecraft;}
    public Status getStatus(){return status;}
    public int getExperienceLevel(){return experienceLevel;}

    @Override
    public String toString() {
//        [#id] name | spacecraft | status | exp=<experienceLevel>
        return "["+id+"]"+ " | "+ spacecraft+" | "+ status+" | exp="+experienceLevel+"\n";
    }
}
