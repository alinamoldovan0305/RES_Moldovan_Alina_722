package org.example.repository;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Event;


import java.io.File;
import java.util.List;
import java.util.Optional;

public class EventRepository extends AbstractRepository<Event> {

    private final ObjectMapper mapper = new ObjectMapper();

    public EventRepository(String filePath) {
        super(filePath);
    }

    @Override
    protected List<Event> loadAll() throws Exception {
        return mapper.readValue(
                new File(filePath),
                new TypeReference<List<Event>>() {}
        );
    }

    @Override
    protected void saveAll(List<Event> items) throws Exception {
        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(filePath), items);
    }

    @Override
    public Optional<Event> findById(int id) throws Exception {
        return loadAll().stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    @Override
    public void update(Event item) throws Exception {
        List<Event> events = loadAll();
        delete(item.getId());
        events.add(item);
        saveAll(events);
    }

    @Override
    public void delete(int id) throws Exception {
        List<Event> events = loadAll();
        events.removeIf(t -> t.getId() == id);
        saveAll(events);
    }
}
