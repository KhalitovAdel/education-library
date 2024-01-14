package ru.education.entities;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.education.repositories.CommonRepository;

import java.time.Duration;
import java.util.Date;
import java.util.Random;

@Getter
@Setter
public class BookRental implements CommonRepository.IReference {
    private Long id = new Random().nextLong(0L, 100L);

    @NotNull
    private Book book;

    @NotNull
    private String userName;

    @NotNull
    private Date endRentalDate;

    private Date createdAt = new Date();

    public boolean isExpire() {
        return endRentalDate.after(new Date());
    }

    public Long getHoursInUse() {
        Duration duration = Duration.between(createdAt.toInstant(), new Date().toInstant());

        return duration.toHours();
    }
}
