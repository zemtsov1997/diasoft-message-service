package ru.diasoft.message.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.diasoft.message.api.model.TimeClient;
import ru.diasoft.message.api.model.telegram.ITelegramTextMessage;
import ru.diasoft.message.service.model.telegram.TelegramBot;

@Component
public class TelegramClient implements TimeClient<ITelegramTextMessage> {

    @Autowired private TelegramBot telegramBot;

    public void sendMessage(ITelegramTextMessage telegramMessage) {
        telegramBot.sendMsg(telegramMessage.getChatId(), telegramMessage.getMessage());
    }

}
