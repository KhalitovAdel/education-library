package ru.education.entities;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.education.CustomList;
import ru.education.repositories.CommonRepository;

import java.util.Random;

@Getter
@Setter
public class Book implements CommonRepository.IReference {
    private Long id = new Random().nextLong(0L, 100L);

    @NotNull
    private String name;

    @NotNull
    private Author author;

    CustomList<BookRental> allRentals = new CustomList<>();

    public Long getHoursInUse() {
        if (allRentals == null) return 0L;

        var sum = 0L;

        var current = allRentals.iterator();

        while (current.hasNext()) {
            var next = current.next();
            sum += next.getHoursInUse();
        }

        return sum;
    }
}
