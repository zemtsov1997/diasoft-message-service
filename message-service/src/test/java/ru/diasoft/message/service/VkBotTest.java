package ru.diasoft.message.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.diasoft.message.service.model.vk.VkBot;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {
        "classpath:bot.vk.recipient.properties"
})
@DisplayName("Testing TelegramBotTest")
public class VkBotTest {

    @Autowired private VkBot vkBot;

    @Value("${bot.vk.recipient.id:ИД получателя}")
    private Integer userId;

    @Test
    @DisplayName("send message")
    void sendMessage() {
        vkBot.sendMessage(userId, "Привет из вк бота");
    }

}
