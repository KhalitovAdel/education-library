package ru.education.controllers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.education.entities.Book;
import ru.education.repositories.AuthorRepository;
import ru.education.repositories.BookRepository;

@RestController
@RequestMapping("/books")
public class BookController extends CommonController<Book> {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final BookRepository repository;
    private final AuthorRepository authorRepository;

    public BookController(BookRepository repository, AuthorRepository authorRepository) {
        super(repository);
        this.repository = repository;
        this.authorRepository = authorRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    // Обрабтчик для создании книги
    public ResponseEntity<Book> onCreate(@RequestBody CreateBookDto createBookDto) throws Exception {
        logger.info("Try to create book with payload = {}", createBookDto);
        if (repository.existsWithName(createBookDto.name)) throw new Exception("Already exists with the same name");

        var author = authorRepository
                .findById(createBookDto.authorId)
                .orElseThrow(EntityNotFoundException::new);

        var payload = new Book();
        payload.setAuthor(author);
        payload.setName(createBookDto.name);

        return new ResponseEntity<>(repository.save(payload), HttpStatus.CREATED);
    }



    @Builder @Getter
    @Setter
    public static class CreateBookDto {
        @NotNull
        String name;
        @NotNull
        Long authorId;

        @Override
        public String toString() {
            return "CreateBookDto{" +
                    "name='" + name + '\'' +
                    ", authorId=" + authorId +
                    '}';
        }
    }
}
