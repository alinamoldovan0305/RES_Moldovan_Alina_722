package org.example.controller;

import org.example.model.*;
import org.example.service.MissionService;

import java.lang.foreign.Arena;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MissionController {

    private final MissionService service;
    private final Scanner scanner = new Scanner(System.in);

    public MissionController(MissionService service) {
        this.service = service;
    }

    public void start() throws Exception {

        /* Task 1 */
        List<Astronaut> astronauts = service.getAllAstronauts();
        List<Event> events = service.getAllEvents();
        List<Supply> supplies = service.getAllSupplies();

        System.out.println("Astronauts loaded: " + astronauts.size());
        System.out.println("Events loaded: " + events.size());
        System.out.println("Supplies loaded: " + supplies.size());

        astronauts.forEach(System.out::println);


        /* Task 2 */
        System.out.print("\nInput spacecraft: ");
        String spacecraft = scanner.nextLine();
        service.filterSpacecraftStatus(spacecraft)
                .forEach(System.out::println);

        /* Task 3 */
        System.out.println("\n---- SORTED ASTRONAUTS ----");
        List<Astronaut> sorted = service.getSortedAstronauts();
        sorted.forEach(System.out::println);

        /* Task 4 */
        Files.write(
                Path.of("astronauts_sorted.txt"),
                sorted.stream().map(Astronaut::toString).toList().reversed()
        );

        /* Task 5 */
                service.getFirstEvents(5)
                        .forEach(e ->
                                System.out.println(
                                        "Event " + e.getId() +
                                                " -> raw=" + e.getBasePoints() +
                                                " -> computed=" + e.computedPoints()
                                )
                        );
        /* Task 6 */
        System.out.println("\nTop 5 Astronauts:");

        List<Map.Entry<Astronaut, Integer>> ranking =
                service.getTop5AstronautsWithScores();

        int position = 1;
        for (Map.Entry<Astronaut, Integer> entry : ranking) {

            Astronaut a = entry.getKey();
            Integer score = entry.getValue();

            System.out.println(
                    position++ + ". " +
                            a.getName() +
                            " ("
                            +a.getSpacecraft()+ ") -> " +
                            score
            );

        }
        if (!ranking.isEmpty()) {
            System.out.println(
                    "\nLeading Spacecraft: " +
                            ranking.get(0).getKey().getSpacecraft()
            );
        }
        /* Task 7 */
        Files.write(
                Path.of("mission_report.txt"),
                service.countEventsByType().entrySet().stream()
                        .map(e -> e.getKey() + " -> " + e.getValue())
                        .toList()
        );
    }
}