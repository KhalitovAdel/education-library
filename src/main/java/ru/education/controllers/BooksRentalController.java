package ru.education.controllers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.education.entities.BookRental;
import ru.education.repositories.BookRentalRepository;
import ru.education.repositories.BookRepository;

import java.util.Date;

@RepositoryRestController
public class BooksRentalController {
    private final BookRepository bookRepository;
    private final BookRentalRepository repository;

    public BooksRentalController(BookRepository bookRepository, BookRentalRepository repository) {
        this.bookRepository = bookRepository;
        this.repository = repository;
    }

    @PostMapping("/book-rentals")
    // Обработчик для создании связи клиента и книги
    public ResponseEntity<BookRental> onCreate(@RequestBody CreateBookRentalDto dto) {
        var book = bookRepository
                .findById(dto.bookId)
                .orElseThrow(EntityNotFoundException::new);

        var payload = BookRental.builder()
                .book(book)
                .endRentalDate(dto.endRentalDate)
                .userName(dto.userName)
                .build();

        return new ResponseEntity<>(repository.save(payload), HttpStatus.CREATED);
    }

    @Builder
    public static class CreateBookRentalDto {
        @NotNull
        private Long bookId;
        @NotNull
        private String userName;
        @NotNull
        private Date endRentalDate;
    }
}
