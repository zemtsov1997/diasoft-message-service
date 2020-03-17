package ru.diasoft.message.api.model.vk;

import feign.Headers;
import feign.RequestLine;

public interface IVkMessageClient<T extends IVkTextMessage> {

    @RequestLine("POST /vk/text_message/send")
    @Headers("Content-Type: application/json")
    void sendTextMessage(T obj);

}
