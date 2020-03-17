package ru.diasoft.message.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.diasoft.message.api.dto.mail.MimeMailDto;
import ru.diasoft.message.api.dto.mail.SimpleMailDto;
import ru.diasoft.message.api.enums.HtmlTemplate;
import ru.diasoft.message.api.model.mail.IMimeMail;
import ru.diasoft.message.api.model.mail.ISimpleMail;
import ru.diasoft.message.service.client.MailClient;

import java.util.HashMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {
        "classpath:email.recipient.properties",
        "classpath:email.sender.properties",
        "classpath:sms.sender.properties"
})
@DisplayName("Testing MailServiceTest")
public class MailClientTest {

    @Autowired private MailClient mailClient;

    @Value("${email.recipient.login}")
    private String recipientEmail;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Test
    @DisplayName("send simple mail")
    void sendSimpleMail() {
        ISimpleMail simpleMail = new SimpleMailDto(
                fromMail,
                recipientEmail,
                "Test simple mail",
                "Hello, this is simple mail"
        );

        mailClient.sendSimpleMessage(simpleMail);
    }

    @Test
    @DisplayName("send mime mail")
    void sendMimeMail() {
        IMimeMail mimeMail = new MimeMailDto(
                fromMail,
                recipientEmail,
                "Test mime mail",
                new HashMap<String, Object>(),
                HtmlTemplate.REGISTRATION
        );

        mailClient.sendMimeMessage(mimeMail);
    }

}
