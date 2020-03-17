package ru.diasoft.service.api.dao;

import java.util.List;
import java.util.Map;

public interface ClientServiceDao<T, M> {

    Long count(Map<String, Object> params);
    List<T> findAll(Map<String, Object> params);
    T find(M id);
    T create(T newObj);
    T update(M id, T newObj);
    void delete(M id);

}
