package ru.education.controllers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.education.entities.Book;
import ru.education.repositories.AuthorRepository;
import ru.education.repositories.BookRepository;

@RepositoryRestController
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final BookRepository repository;
    private final AuthorRepository authorRepository;

    public BookController(BookRepository repository, AuthorRepository authorRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
    }

    @PostMapping("/books")
    // Обрабтчик для создании книги
    public ResponseEntity<Book> onCreate(@RequestBody CreateBookDto createBookDto) {
        logger.info("Try to create book with payload = {}", createBookDto);
        var author = authorRepository
                .findById(createBookDto.authorId)
                .orElseThrow(EntityNotFoundException::new);

        var payload = Book.builder()
                .author(author)
                .name(createBookDto.name)
                .build();

        return new ResponseEntity<>(repository.save(payload), HttpStatus.CREATED);
    }



    @Builder
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
