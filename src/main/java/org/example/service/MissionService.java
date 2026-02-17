package org.example.service;

import org.example.model.*;

import org.example.repository.EventRepository;
import org.example.repository.SupplyRepository;

import org.example.repository.AstronautRepository;


import java.util.*;
import java.util.stream.Collectors;

public class MissionService {

    private final AstronautRepository astronautRepo;
    private final EventRepository eventRepo;
    private final SupplyRepository supplyRepo;

    public MissionService(
            AstronautRepository astronautRepo,
            EventRepository eventRepo,
            SupplyRepository supplyRepo) {

        this.astronautRepo = astronautRepo;
        this.eventRepo = eventRepo;
        this.supplyRepo = supplyRepo;
    }

    /* Task 1 */
    public List<Astronaut> getAllAstronauts() throws Exception {
        return astronautRepo.findAll();
    }

    public List<Event> getAllEvents() throws Exception {
        return eventRepo.findAll();
    }

    public List<Supply> getAllSupplies() throws Exception {
        return supplyRepo.findAll();
    }

    /* Task 2 */
    public List<Astronaut> filterSpacecraftStatus(String spacecraft) throws Exception {
        return astronautRepo.findAll().stream()
                .filter(t -> Objects.equals(t.getSpacecraft(), spacecraft))
                .filter(t -> t.getStatus() == Status.ACTIVE)
                .toList();
    }

    /* Task 3 */
    public List<Astronaut> getSortedAstronauts() throws Exception {
        return astronautRepo.findAll().stream()
                .sorted(Comparator
                        .comparingInt(Astronaut::getExperienceLevel).reversed()
                        .thenComparing(Astronaut::getName))
                .toList();
    }


    /*Task5*/
    public List<Event> getFirstEvents(int limit) throws Exception {
        return eventRepo.findAll()
                .stream()
                .limit(limit)
                .toList();
    }
    /* Task 6 */
    public List<Map.Entry<Astronaut, Integer>> getTop5AstronautsWithScores()
            throws Exception {

        // 1. Points from missions
        Map<Integer, Integer> missionPoints =
                eventRepo.findAll().stream()
                        .collect(Collectors.groupingBy(
                                Event::getAstronautId,
                                Collectors.summingInt(Event::computedPoints)
                        ));

        // 2. value supplies
        Map<Integer, Integer> suppliesValue =
                supplyRepo.findAll().stream()
                        .collect(Collectors.groupingBy(
                                Supply::getAstronautId,
                                Collectors.summingInt(Supply::getValue)
                        ));

        // 3. Build ranking and sort
        return astronautRepo.findAll().stream()
                .map(d -> {
                    int missionpoints = missionPoints.getOrDefault(d.getId(), 0);
                    int supplypoints = suppliesValue.getOrDefault(d.getId(), 0);
                    int total = missionpoints + supplypoints;
                    return Map.entry(d, total);
                })
                .sorted(
                        Comparator
                                .comparing(Map.Entry<Astronaut, Integer>::getValue)
                                .reversed()
                                .thenComparing(e -> e.getKey().getName())
                )
                .limit(5)
                .toList();
    }
    /* Task 7 */
    public Map<EventType, Long> countEventsByType() throws Exception {
        return eventRepo.findAll().stream()
                .collect(Collectors.groupingBy(
                        Event::getType,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<EventType, Long>comparingByValue().reversed()

                )
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
}
