package ru.diasoft.message.api.model.sms;

import feign.Headers;
import feign.RequestLine;

import java.math.BigDecimal;

public interface ISmsClient<T extends ISms> {

    @RequestLine("POST /sms/send")
    @Headers("Content-Type: application/json")
    void send(T obj);

    @RequestLine("POST /sms/cost")
    @Headers("Content-Type: application/json")
    BigDecimal cost(T obj);

    @RequestLine("GET /sms/balance")
    @Headers("Content-Type: application/json")
    BigDecimal balance();

}
