package ru.diasoft.message.api.model.discord;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IDiscordMessageController<T extends IDiscrodTextMessage> {

    @PostMapping(value = "/discord/text_message/send", produces = MediaType.APPLICATION_JSON_VALUE)
    void sendTextMessage(@RequestBody T obj);

}
