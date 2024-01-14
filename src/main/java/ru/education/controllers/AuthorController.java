package ru.education.controllers;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.education.entities.Author;
import ru.education.repositories.AuthorRepository;

@RestController
@RequestMapping("/authors")
public class AuthorController extends CommonController<Author> {
    private final AuthorRepository repository;
    private final ModelMapper mapper;

    public AuthorController(AuthorRepository repository, ModelMapper mapper) {
        super(repository);
        this.repository = repository;
        this.mapper = mapper;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Author> onCreate(@RequestBody CreateAuthorDto dto) {
        var author = repository.save(mapper.map(dto, Author.class));

        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @Setter @Getter
    public static class CreateAuthorDto {
        private String name;
    }
}
