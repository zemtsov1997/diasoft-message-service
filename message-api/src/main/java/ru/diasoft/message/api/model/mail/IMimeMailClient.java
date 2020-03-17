package ru.diasoft.message.api.model.mail;

import feign.Headers;
import feign.RequestLine;

public interface IMimeMailClient<T extends IMimeMail> {

    @RequestLine("POST /mimeMail/send")
    @Headers("Content-Type: application/json")
    void send(T obj);

}
