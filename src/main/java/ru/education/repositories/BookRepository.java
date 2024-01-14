package ru.education.repositories;

import org.springframework.stereotype.Component;
import ru.education.entities.Book;

import java.util.Objects;

@Component
public class BookRepository extends CommonRepository<Book> {
    public boolean existsWithName(String name) {
        var iterator = this.getAll();
        while(iterator.hasNext()) {
            if (iterator.next().getName().equals(name)) return true;
        }
        return false;
    }
}
