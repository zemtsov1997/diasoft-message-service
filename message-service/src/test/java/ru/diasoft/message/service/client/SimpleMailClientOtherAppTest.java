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
import ru.diasoft.message.api.dto.mail.SimpleMailDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {
        "classpath:email.recipient.properties",
        "classpath:email.sender.properties"
})
@DisplayName("Testing SimpleMailClientOtherAppTest")
public class SimpleMailClientOtherAppTest {

    @LocalServerPort
    private int port;

    @Value("${email.recipient.login}")
    private String recipientEmail;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Test
    @DisplayName("send simple mail")
    void sendDiscordTextMessage() {
        SimpleMailDto simpleMail = new SimpleMailDto(fromMail, recipientEmail, "Заголовок", "Контент");

        SimpleMailClientOtherApp client = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(SimpleMailClientOtherApp.class, "http://localhost:"+port);

        client.send(simpleMail);
    }

}
