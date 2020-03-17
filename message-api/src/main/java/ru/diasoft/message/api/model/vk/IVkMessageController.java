package ru.diasoft.message.api.model.vk;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IVkMessageController<T extends IVkTextMessage> {

    @PostMapping(value = "/vk/text_message/send", produces = MediaType.APPLICATION_JSON_VALUE)
    void sendTextMessage(@RequestBody T obj);

}
