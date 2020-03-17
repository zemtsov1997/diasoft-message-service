package ru.diasoft.message.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.diasoft.message.api.model.TimeClient;
import ru.diasoft.message.api.model.vk.IVkTextMessage;
import ru.diasoft.message.service.model.vk.VkBot;

@Component
public class VkClient implements TimeClient<IVkTextMessage> {

    @Autowired private VkBot vkBot;

    public void sendMessage(IVkTextMessage textMessage) {
        vkBot.sendMessage(textMessage.getUserId(), textMessage.getMessage());
    }

}
