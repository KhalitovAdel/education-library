package ru.education.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.education.repositories.CommonRepository;

import java.util.Iterator;

public abstract class CommonController<T extends CommonRepository.IReference> {
    private final CommonRepository<T> repository;

    CommonController(CommonRepository<T> repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterator<T>> list() {
        var myObjects = repository.getAll();

        return ResponseEntity.ok(myObjects);
    }
}
