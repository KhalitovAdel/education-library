package ru.education.controllers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.education.entities.BookRental;
import ru.education.repositories.BookRentalRepository;
import ru.education.repositories.BookRepository;

import java.util.Date;

@RestController
@RequestMapping("/book-rentals")
public class BooksRentalController extends CommonController<BookRental> {
    private final BookRepository bookRepository;
    private final BookRentalRepository repository;

    public BooksRentalController(BookRepository bookRepository, BookRentalRepository repository) {
        super(repository);
        this.bookRepository = bookRepository;
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST)
    // Обработчик для создании связи клиента и книги
    public ResponseEntity<BookRental> onCreate(@RequestBody CreateBookRentalDto dto) {
        var book = bookRepository
                .findById(dto.bookId)
                .orElseThrow(EntityNotFoundException::new);

        var payload = new BookRental();
        payload.setBook(book);
        payload.setEndRentalDate(dto.endRentalDate);
        payload.setUserName(dto.userName);

        return new ResponseEntity<>(repository.save(payload), HttpStatus.CREATED);
    }

    @Builder @Getter @Setter
    public static class CreateBookRentalDto {
        @NotNull
        private Long bookId;
        @NotNull
        private String userName;
        @NotNull
        private Date endRentalDate;
    }
}
