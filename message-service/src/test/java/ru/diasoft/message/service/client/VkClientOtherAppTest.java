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
import ru.diasoft.message.api.dto.vk.VkTextMessageDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {
        "classpath:bot.vk.recipient.properties"
})
@DisplayName("Testing TelegramClientOtherAppTest")
public class VkClientOtherAppTest {

    @LocalServerPort
    private int port;

    @Value("${bot.vk.recipient.id:ид получателя}")
    private Integer userId;

    @Test
    @DisplayName("send vk text message")
    void sendTextMessage() {
        VkTextMessageDto textMessage = new VkTextMessageDto(userId, "Ваш баланс такой: .....");

        VkClientOtherApp messageClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(VkClientOtherApp.class, "http://localhost:"+port);

        messageClient.sendTextMessage(textMessage);
    }

}
