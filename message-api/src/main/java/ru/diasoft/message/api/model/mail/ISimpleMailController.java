package ru.diasoft.message.api.model.mail;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ISimpleMailController<T extends ISimpleMail> {

    @PostMapping(value = "/simpleMail/send", produces = MediaType.APPLICATION_JSON_VALUE)
    void send(@RequestBody T obj);

}
