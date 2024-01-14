package ru.education.repositories;

import ru.education.CustomList;

import java.util.Iterator;
import java.util.Optional;

public class CommonRepository<T extends CommonRepository.IReference> {
    private CustomList<T> storage;

    public CommonRepository() {
        storage = new CustomList();
    }

    public T save(T v) {
        storage.add(v);

        return v;
    }

    public Iterator<T> getAll() {
        return storage.iterator();
    }

    public Optional<T> findById(Long id) {
        Iterator<T> iter = storage.iterator();
        while(iter.hasNext()){
            var next = iter.next();
            if (next.getId() == id) {
                return Optional.of(next);
            }
        }
        return Optional.empty();
    }

    public interface IReference {
        public Long getId();
    }
}
