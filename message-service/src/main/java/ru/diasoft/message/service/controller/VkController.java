package ru.diasoft.message.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.message.api.dto.vk.VkTextMessageDto;
import ru.diasoft.message.api.model.vk.IVkMessageController;
import ru.diasoft.message.service.client.VkClient;
import ru.diasoft.message.service.entity.VkHistory;
import ru.diasoft.message.service.repository.VkHistoryRepository;

@RestController
public class VkController implements IVkMessageController<VkTextMessageDto> {

    @Autowired private VkClient vkClient;
    @Autowired private VkHistoryRepository historyRepository;

    @Override
    public void sendTextMessage(VkTextMessageDto obj) {
        vkClient.sendMessage(obj);
        historyRepository.save(new VkHistory(obj));
    }

}
