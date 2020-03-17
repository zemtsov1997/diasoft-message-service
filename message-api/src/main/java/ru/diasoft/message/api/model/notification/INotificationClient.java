package ru.diasoft.message.api.model.notification;

import feign.Headers;
import feign.RequestLine;

public interface INotificationClient<T extends IPushMessage> {

    @RequestLine("POST /notification/sendPersonal")
    @Headers("Content-Type: application/json")
    void send(T obj);

}
