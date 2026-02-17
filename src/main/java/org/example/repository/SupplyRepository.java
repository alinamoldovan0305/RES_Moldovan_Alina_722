package org.example.repository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Supply;


import java.io.File;
import java.util.List;
import java.util.Optional;

public class SupplyRepository extends AbstractRepository<Supply> {

    private final ObjectMapper mapper = new ObjectMapper();

    public SupplyRepository(String filePath) {
        super(filePath);
    }

    @Override
    protected List<Supply> loadAll() throws Exception {
        return mapper.readValue(
                new File(filePath),
                new TypeReference<List<Supply>>() {}
        );
    }

    @Override
    protected void saveAll(List<Supply> items) throws Exception {
        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(filePath), items);
    }

    @Override
    public Optional<Supply> findById(int id) throws Exception {
        return loadAll().stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    @Override
    public void update(Supply item) throws Exception {
        List<Supply> supplies = loadAll();
        delete(item.getId());
        supplies.add(item);
        saveAll(supplies);
    }

    @Override
    public void delete(int id) throws Exception {
        List<Supply> supplies = loadAll();
        supplies.removeIf(t -> t.getId() == id);
        saveAll(supplies);
    }
}
