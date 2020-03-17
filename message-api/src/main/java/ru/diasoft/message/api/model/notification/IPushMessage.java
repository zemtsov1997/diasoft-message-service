package ru.diasoft.message.api.model.notification;

import ru.diasoft.message.api.model.IMessage;

public interface IPushMessage extends IMessage {

    Long getId();
    String getClientToken();
    String getTitle();
    String getBody();
    String getUrlIcon();
    String getUrlImage();
    String getUrlAction();
    String getTtlInSeconds();

}
