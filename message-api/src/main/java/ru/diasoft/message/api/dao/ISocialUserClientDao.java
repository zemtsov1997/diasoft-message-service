package ru.diasoft.message.api.dao;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import ru.diasoft.message.api.model.ISocialUser;

import java.util.Map;

public interface ISocialUserClientDao<T extends ISocialUser> {

    @RequestLine("GET /socialUsers/{id}")
    @Headers("Content-Type: application/json")
    T find(@Param(value = "id") Long id);

    @RequestLine("GET /socialUsers/find_by_params")
    @Headers("Content-Type: application/json")
    T find(@QueryMap(encoded = true) Map<String, Object> params);

    @RequestLine("POST /socialUsers")
    @Headers("Content-Type: application/json")
    T create(T obj);

    @RequestLine("PUT /socialUsers/{id}")
    @Headers("Content-Type: application/json")
    T update(@Param(value = "id") Long id, T obj);

    @RequestLine("DELETE /socialUsers/{id}")
    @Headers("Content-Type: application/json")
    T delete(@Param(value = "id") Long id);

}