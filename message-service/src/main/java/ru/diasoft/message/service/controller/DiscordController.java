package ru.diasoft.message.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.message.api.dto.discord.DiscrodTextMessageDto;
import ru.diasoft.message.api.model.discord.IDiscordMessageController;
import ru.diasoft.message.service.client.DiscordClient;
import ru.diasoft.message.service.entity.DiscordTextMessageHistory;
import ru.diasoft.message.service.repository.DiscordTextMessageHistoryRepository;

@RestController
public class DiscordController implements IDiscordMessageController<DiscrodTextMessageDto> {

    @Autowired private DiscordClient discordClient;
    @Autowired private DiscordTextMessageHistoryRepository discordHistoryRepository;

    @Override
    public void sendTextMessage(DiscrodTextMessageDto obj) {
        discordClient.sendMessage(obj);
        discordHistoryRepository.save(new DiscordTextMessageHistory(obj));
    }
}
