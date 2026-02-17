package org.example.repository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T> {

    protected final String filePath;

    protected AbstractRepository(String filePath) {
        this.filePath = filePath;
    }

    /* Low-level persistence */
    protected abstract List<T> loadAll() throws Exception;
    protected abstract void saveAll(List<T> items) throws Exception;

    /* CRUD operations */

    public List<T> findAll() throws Exception {
        return loadAll();
    }

    public abstract Optional<T> findById(int id) throws Exception;

    public void create(T item) throws Exception {
        List<T> items = loadAll();
        items.add(item);
        saveAll(items);
    }

    public abstract void update(T item) throws Exception;

    public abstract void delete(int id) throws Exception;
}

