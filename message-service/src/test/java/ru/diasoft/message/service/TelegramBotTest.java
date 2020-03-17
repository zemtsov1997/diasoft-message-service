package ru.diasoft.message.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.diasoft.message.service.model.telegram.TelegramBot;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {
        "classpath:bot.telegram.recipient.properties"
})
@DisplayName("Testing TelegramBotTest")
public class TelegramBotTest {

    @Autowired private TelegramBot telegramBot;

    @Value("${bot.telegram.recipient.chat.id}")
    private String recipientChatId;

    @Test
    @DisplayName("send message")
    void sendMessage() {
        telegramBot.sendMsg(recipientChatId, "Ваш пароль 111111");
    }

    @Test
    @DisplayName("send img")
    void sendImage() {
        telegramBot.sendImage(recipientChatId, "AgADAgAD6qcxGwnPsUgOp7-MvnQ8GecvSw0ABGvTl7ObQNPNX7UEAAEC", "Заголовок");
    }

}
