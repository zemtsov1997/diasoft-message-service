package ru.diasoft.message.api.model.discord;

import feign.Headers;
import feign.RequestLine;

public interface IDiscordMessageClient<T extends IDiscrodTextMessage> {

    @RequestLine("POST /discord/text_message/send")
    @Headers("Content-Type: application/json")
    void sendTextMessage(T obj);

}
