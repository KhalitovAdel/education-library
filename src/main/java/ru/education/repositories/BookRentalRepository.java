package ru.education.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.education.entities.BookRental;

@RepositoryRestResource(collectionResourceRel = "book-rentals", path = "book-rentals")
public interface BookRentalRepository extends JpaRepository<BookRental, Long>, PagingAndSortingRepository<BookRental, Long> {
}
