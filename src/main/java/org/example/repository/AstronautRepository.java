package org.example.repository;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Astronaut;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class AstronautRepository extends AbstractRepository<Astronaut> {

    private final ObjectMapper mapper = new ObjectMapper();

    public AstronautRepository(String filePath) {
        super(filePath);
    }

    @Override
    protected List<Astronaut> loadAll() throws Exception {
        return mapper.readValue(
                new File(filePath),
                new TypeReference<List<Astronaut>>() {}
        );
    }

    @Override
    protected void saveAll(List<Astronaut> items) throws Exception {
        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(filePath), items);
    }

    @Override
    public Optional<Astronaut> findById(int id) throws Exception {
        return loadAll().stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    @Override
    public void update(Astronaut item) throws Exception {
        List<Astronaut> astronauts = loadAll();
        delete(item.getId());
        astronauts.add(item);
        saveAll(astronauts);
    }

    @Override
    public void delete(int id) throws Exception {
        List<Astronaut> astronauts = loadAll();
        astronauts.removeIf(t -> t.getId() == id);
        saveAll(astronauts);
    }
}

