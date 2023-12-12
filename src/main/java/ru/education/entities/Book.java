package ru.education.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Book {
    @Id
    @GeneratedValue()
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    @ManyToOne
    private Author author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<BookRental> allRentals = List.of();

    public Long getHoursInUse() {
        if (allRentals == null) return 0L;

        return allRentals
                .stream()
                .mapToLong(BookRental::getHoursInUse)
                .reduce(0L, Long::sum);
    }
}
