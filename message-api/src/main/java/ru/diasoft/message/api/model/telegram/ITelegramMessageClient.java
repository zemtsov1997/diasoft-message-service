package ru.diasoft.message.api.model.telegram;

import feign.Headers;
import feign.RequestLine;

public interface ITelegramMessageClient<T extends ITelegramTextMessage> {

    @RequestLine("POST /telegram/text_message/send")
    @Headers("Content-Type: application/json")
    void sendTextMessage(T obj);

}
