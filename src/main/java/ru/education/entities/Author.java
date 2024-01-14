package ru.education.entities;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.education.repositories.CommonRepository;

import java.util.Random;

@Getter
@Setter
public class Author implements CommonRepository.IReference {
    private Long id = new Random().nextLong(0L, 100L);

    @NotNull
    private String name;
}
