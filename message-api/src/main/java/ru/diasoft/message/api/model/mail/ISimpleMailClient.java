package ru.diasoft.message.api.model.mail;

import feign.Headers;
import feign.RequestLine;

public interface ISimpleMailClient<T extends ISimpleMail> {

    @RequestLine("POST /simpleMail/send")
    @Headers("Content-Type: application/json")
    void send(T obj);

}
