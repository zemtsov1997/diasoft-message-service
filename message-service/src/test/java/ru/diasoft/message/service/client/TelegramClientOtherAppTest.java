package ru.diasoft.message.service.client;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import ru.diasoft.message.api.dto.telegram.TelegramTextMessageDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {
        "classpath:bot.telegram.recipient.properties"
})
@DisplayName("Testing TelegramClientOtherAppTest")
public class TelegramClientOtherAppTest {

    @LocalServerPort
    private int port;

    @Value("${bot.telegram.recipient.chat.id}")
    private String recipientChatId;

    @Test
    @DisplayName("send telegram text message")
    void sendDiscordTextMessage() {
        TelegramTextMessageDto textMessage = new TelegramTextMessageDto(recipientChatId, "Ваш баланс такой: .....");

        TelegramClientOtherApp messageClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(TelegramClientOtherApp.class, "http://localhost:"+port);

        messageClient.sendTextMessage(textMessage);
    }

}
