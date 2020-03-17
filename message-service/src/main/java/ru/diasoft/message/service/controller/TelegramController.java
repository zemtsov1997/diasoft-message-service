package ru.diasoft.message.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.message.api.dto.telegram.TelegramTextMessageDto;
import ru.diasoft.message.api.model.telegram.ITelegramMessageController;
import ru.diasoft.message.service.client.TelegramClient;
import ru.diasoft.message.service.entity.TelegramHistory;
import ru.diasoft.message.service.repository.TelegramHistoryRepository;

@RestController
public class TelegramController implements ITelegramMessageController<TelegramTextMessageDto> {

    @Autowired private TelegramClient telegramClient;
    @Autowired private TelegramHistoryRepository telegramHistoryRepository;

    @Override
    public void sendTextMessage(TelegramTextMessageDto obj) {
        telegramClient.sendMessage(obj);
        telegramHistoryRepository.save(new TelegramHistory(obj));
    }

}
