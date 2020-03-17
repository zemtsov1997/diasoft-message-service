package ru.diasoft.message.api.dao;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.diasoft.message.api.model.ISocialUser;

import java.util.Map;

public interface ISocialUserControllerDao<T extends ISocialUser> {

    @GetMapping(value = "/socialUsers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    T find(@PathVariable(name = "id") Long id);

    @GetMapping(value = "/socialUsers/find_by_params", produces = MediaType.APPLICATION_JSON_VALUE)
    T find(@RequestParam(required = false) Map<String, Object> params);

    @PostMapping(value = "/socialUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    T create(@RequestBody T obj);

    @PutMapping(value = "/socialUsers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    T update(@PathVariable(name = "id") Long id, @RequestBody T newObj);

    @DeleteMapping(value = "/socialUsers/{id}")
    void delete(@PathVariable(name = "id") Long id);

}