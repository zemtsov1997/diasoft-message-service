package ru.diasoft.message.api.model.telegram;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ITelegramMessageController<T extends ITelegramTextMessage> {

    @PostMapping(value = "/telegram/text_message/send", produces = MediaType.APPLICATION_JSON_VALUE)
    void sendTextMessage(@RequestBody T obj);

}
