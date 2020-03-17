package ru.diasoft.message.service.client;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import ru.diasoft.message.api.dto.discord.DiscrodTextMessageDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Testing DiscordClientOtherAppTest")
public class DiscordClientOtherAppTest {

    @LocalServerPort
    private int port;

    @Test
    @DisplayName("send discord text message")
    void sendDiscordTextMessage() {
        DiscrodTextMessageDto discrodTextMessageDto =
                new DiscrodTextMessageDto("ид из дискорта", "Тестовое сообщение из Discord");

        DiscordClientOtherApp messageClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(DiscordClientOtherApp.class, "http://localhost:"+port);

        messageClient.sendTextMessage(discrodTextMessageDto);
    }

}
