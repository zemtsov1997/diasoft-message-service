package ru.diasoft.message.api.model.telegram;

import ru.diasoft.message.api.model.IMessage;

public interface ITelegramMessage extends IMessage {

    String getChatId();

}
