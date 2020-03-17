package ru.diasoft.message.api.model.notification;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface INotificationController<T extends IPushMessage> {

    @PostMapping(value = "/notification/sendPersonal", produces = MediaType.APPLICATION_JSON_VALUE)
    void send(@RequestBody T obj);

}
