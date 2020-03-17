package ru.diasoft.service.api.dao;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.diasoft.service.api.model.INumberPhone;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

public interface NumberPhoneControllerDao<T extends INumberPhone> {

    @GetMapping(value = "/numberPhones", produces = MediaType.APPLICATION_JSON_VALUE)
    List<T> findAll();

    @GetMapping(value = "/numberPhones/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    T find(@PathVariable(name = "id") @Min(1) Long id);

    @PostMapping(value = "/numberPhones", produces = MediaType.APPLICATION_JSON_VALUE)
    T create(@Valid @RequestBody T profile);

    @PutMapping(value = "/numberPhones/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    T update(@PathVariable(name = "id") @Min(1) Long id, @Valid @RequestBody T newProfile);

    @DeleteMapping(value = "/numberPhones/{id}")
    void delete(@PathVariable(name = "id") @Min(1) Long id);

}
