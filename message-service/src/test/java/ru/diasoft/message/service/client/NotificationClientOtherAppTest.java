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
import ru.diasoft.message.api.dto.notification.PushMessageDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {
        "classpath:notification.recipient.properties"
})
@DisplayName("Testing NotificationClientOtherAppTest")
public class NotificationClientOtherAppTest {

    @LocalServerPort
    private int port;

    @Value("${notification.recipient.token}")
    private String recipientToken;

    @Test
    @DisplayName("send notification text message")
    void sendDiscordTextMessage() {
        PushMessageDto pushMessageDto = new PushMessageDto();
        pushMessageDto.setTitle("Bubble Nebula");
        pushMessageDto.setClientToken(recipientToken);
        pushMessageDto.setBody("It's found today at 11:36");
        pushMessageDto.setUrlIcon("https://peter-gribanov.github.io/serviceworker/Bubble-Nebula.jpg");
        pushMessageDto.setUrlImage("https://peter-gribanov.github.io/serviceworker/Bubble-Nebula.jpg");
        pushMessageDto.setUrlAction("https://www.nasa.gov/feature/goddard/2016/hubble-sees-a-star-inflating-a-giant-bubble");
        pushMessageDto.setTtlInSeconds("0");

        NotificationClientOtherApp client = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(NotificationClientOtherApp.class, "http://localhost:"+port);

        client.send(pushMessageDto);
    }

}
