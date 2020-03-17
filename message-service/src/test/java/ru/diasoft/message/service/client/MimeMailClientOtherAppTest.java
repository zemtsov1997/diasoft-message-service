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
import ru.diasoft.message.api.dto.mail.MimeMailDto;
import ru.diasoft.message.api.enums.HtmlTemplate;

import java.util.HashMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {
        "classpath:email.recipient.properties",
        "classpath:email.sender.properties"
})
@DisplayName("Testing MimeMailClientOtherAppTest")
public class MimeMailClientOtherAppTest {

    @LocalServerPort
    private int port;

    @Value("${email.recipient.login}")
    private String recipientEmail;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Test
    @DisplayName("send mime mail")
    void sendDiscordTextMessage() {
        MimeMailDto mimeMail = new MimeMailDto(fromMail, recipientEmail,
                "Test mime mail",
                new HashMap<String, Object>(),
                HtmlTemplate.REGISTRATION);

        MimeMailClientOtherApp client = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(MimeMailClientOtherApp.class, "http://localhost:"+port);

        client.send(mimeMail);
    }

}
