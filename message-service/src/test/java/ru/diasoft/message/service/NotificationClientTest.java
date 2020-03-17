package ru.diasoft.message.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.diasoft.message.api.dto.notification.PushMessageDto;
import ru.diasoft.message.service.client.NotificationClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {
        "classpath:email.recipient.properties",
        "classpath:email.sender.properties",
        "classpath:sms.sender.properties",
        "classpath:notification.recipient.properties"
})
@DisplayName("Testing notification-service")
public class NotificationClientTest {

    @Autowired private NotificationClient notificationClient;

    @Value("${notification.recipient.token}")
    private String recipientToken;

    @Test
    @DisplayName("send push message")
    void sendPushMessage() {
        PushMessageDto pushMessageDto = new PushMessageDto();
        pushMessageDto.setClientToken(recipientToken);
        pushMessageDto.setTitle("Bubble Nebula");
        pushMessageDto.setBody("It's found today at 11:36");
        pushMessageDto.setUrlIcon("https://peter-gribanov.github.io/serviceworker/Bubble-Nebula.jpg");
        pushMessageDto.setUrlImage("https://peter-gribanov.github.io/serviceworker/Bubble-Nebula.jpg");
        pushMessageDto.setUrlAction("https://www.nasa.gov/feature/goddard/2016/hubble-sees-a-star-inflating-a-giant-bubble");
        pushMessageDto.setTtlInSeconds("0");

        notificationClient.sendPersonal(pushMessageDto);
    }

}
