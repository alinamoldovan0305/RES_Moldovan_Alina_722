package org.example;

import org.example.controller.MissionController;
import org.example.model.Astronaut;
import org.example.repository.EventRepository;
import org.example.repository.SupplyRepository;
import org.example.repository.AstronautRepository;
import org.example.service.MissionService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        try {
            AstronautRepository astronautRepo =
                    new AstronautRepository("src/main/resources/astronauts.json");
            EventRepository eventRepo =
                    new EventRepository("src/main/resources/events.json");
            SupplyRepository supplyRepo=
                    new SupplyRepository ("src/main/resources/supplies.json");

            MissionService service =
                    new MissionService(astronautRepo,eventRepo,supplyRepo);

            MissionController controller =
                    new MissionController(service);

            controller.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
