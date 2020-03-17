package ru.diasoft.message.api.model.mail;

import ru.diasoft.message.api.model.IMessage;

public interface IMail extends IMessage {

    String getFrom();

    String getTo();

    String getSubject();

}
