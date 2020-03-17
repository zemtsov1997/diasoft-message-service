package ru.diasoft.message.service.client;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import ru.diasoft.message.api.dto.discord.DiscrodTextMessageDto;
import ru.diasoft.message.api.dto.mail.MimeMailDto;
import ru.diasoft.message.api.dto.mail.SimpleMailDto;
import ru.diasoft.message.api.dto.notification.PushMessageDto;
import ru.diasoft.message.api.dto.sms.SmsDto;
import ru.diasoft.message.api.dto.telegram.TelegramTextMessageDto;
import ru.diasoft.message.api.dto.vk.VkTextMessageDto;
import ru.diasoft.message.api.enums.HtmlTemplate;
import ru.diasoft.message.api.enums.VerificationStatus;
import ru.diasoft.message.service.model.discord.VerificationDiscordTextMessage;
import ru.diasoft.message.service.model.mail.VerificationMimeMail;
import ru.diasoft.message.service.model.mail.VerificationSimpleMail;
import ru.diasoft.message.service.model.notification.VerificationPushMessage;
import ru.diasoft.message.service.model.sms.VerificationSms;
import ru.diasoft.message.service.model.telegram.VerificationTelegramTextMessage;
import ru.diasoft.message.service.model.vk.VerificationVkTextMessage;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {
        "classpath:email.recipient.properties",
        "classpath:email.sender.properties",
        "classpath:notification.recipient.properties",
        "classpath:bot.telegram.recipient.properties",
        "classpath:bot.vk.recipient.properties"
})
@DisplayName("Testing VerificationClientOtherAppTest")
public class VerificationClientOtherAppTest {

    private final static Logger logger = LogManager.getLogger(VerificationClientOtherAppTest.class);

    @LocalServerPort
    private int port;

    @Value("${email.recipient.login}")
    private String recipientEmail;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Value("${notification.recipient.token}")
    private String recipientToken;

    @Value("${bot.telegram.recipient.chat.id}")
    private String recipientChatId;

    @Value("${bot.vk.recipient.id:ид получателя}")
    private Integer userId;

    @Test
    @DisplayName("send verification simple mail message")
    void sendVerifiatiomSimpleMailMessage() {
        VerificationClientOtherApp client = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(VerificationClientOtherApp.class, "http://localhost:"+port);

        SimpleMailDto simpleMail = new SimpleMailDto(fromMail, recipientEmail, "Заголовок", "Ваш код: 1234");

        UUID uuid = UUID.randomUUID();

        VerificationSimpleMail verificationSimpleMail = new VerificationSimpleMail(simpleMail);
        verificationSimpleMail.setVerificationCode(1234);
        verificationSimpleMail.setProcessUuid(uuid);
        verificationSimpleMail.setVerificationStatus(VerificationStatus.SEND);

        VerificationStatus status0 = client.sendCodeInSimpleMail(verificationSimpleMail, null);
        logger.info("status verification: " + status0.name());

        VerificationSimpleMail verificationSimpleMail2 = new VerificationSimpleMail(simpleMail);
        verificationSimpleMail2.setVerificationCode(123467);
        verificationSimpleMail2.setProcessUuid(uuid);
        verificationSimpleMail2.setVerificationStatus(VerificationStatus.SEND);

        VerificationStatus status1 = client.sendCodeInSimpleMail(verificationSimpleMail2, null);
        logger.info("status verification: " + status1.name());

        VerificationSimpleMail verificationSimpleMai3 = new VerificationSimpleMail(simpleMail);
        verificationSimpleMai3.setVerificationCode(123467899);
        verificationSimpleMai3.setProcessUuid(uuid);
        verificationSimpleMai3.setVerificationStatus(VerificationStatus.SEND);

        VerificationStatus status2 = client.sendCodeInSimpleMail(verificationSimpleMai3, null);
        logger.info("status verification: " + status2.name());

        VerificationStatus status3 = client.verification(verificationSimpleMail.getProcessUuid(), 123467899);
        logger.info("status verification: " + status3.name());

    }

    @Test
    @DisplayName("send verification mime mail message")
    void sendVerifiatiomMimeMailMessage() {
        VerificationClientOtherApp client = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(VerificationClientOtherApp.class, "http://localhost:"+port);

        MimeMailDto mimeMail = new MimeMailDto(fromMail, recipientEmail,
                "Test mime mail",
                new HashMap<String, Object>(),
                HtmlTemplate.REGISTRATION);

        UUID uuid = UUID.randomUUID();

        VerificationMimeMail verificationMimeMail = new VerificationMimeMail(mimeMail);
        verificationMimeMail.setVerificationCode(1234);
        verificationMimeMail.setProcessUuid(uuid);
        verificationMimeMail.setVerificationStatus(VerificationStatus.SEND);

        VerificationStatus status0 = client.sendCodeInMimeMail(verificationMimeMail, null);
        logger.info("status verification: " + status0.name());

        VerificationMimeMail verificationMimeMail2 = new VerificationMimeMail(mimeMail);
        verificationMimeMail2.setVerificationCode(123456577);
        verificationMimeMail2.setProcessUuid(uuid);
        verificationMimeMail2.setVerificationStatus(VerificationStatus.SEND);

        VerificationStatus status1 = client.sendCodeInMimeMail(verificationMimeMail2, null);
        logger.info("status verification: " + status1.name());

        VerificationMimeMail verificationMimeMail3 = new VerificationMimeMail(mimeMail);
        verificationMimeMail3.setVerificationCode(12347997);
        verificationMimeMail3.setProcessUuid(uuid);
        verificationMimeMail3.setVerificationStatus(VerificationStatus.SEND);

        VerificationStatus status2 = client.sendCodeInMimeMail(verificationMimeMail3, null);
        logger.info("status verification: " + status2.name());

        VerificationStatus status3 = client.verification(uuid, 12347997);
        logger.info("status verification: " + status3.name());
    }

    @Test
    @DisplayName("send verification sms")
    void sendVerifiatiomSms() {
        VerificationClientOtherApp client = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(VerificationClientOtherApp.class, "http://localhost:"+port);

        List<String> numberPhones = new LinkedList<>();
        numberPhones.add("+79530190297");

        SmsDto smsDto = new SmsDto();
        smsDto.setMessage("Ваш проверочный код: 12345");
        smsDto.setNumberPhoneRecipients(numberPhones);

        UUID uuid = UUID.randomUUID();

        VerificationSms verificationSms = new VerificationSms(smsDto);
        verificationSms.setVerificationCode(1234);
        verificationSms.setProcessUuid(uuid);
        verificationSms.setVerificationStatus(VerificationStatus.SEND);

        VerificationStatus status2 = client.sendCodeInSms(verificationSms, null);
        logger.info("status verification: " + status2.name());

        VerificationStatus status3 = client.verification(uuid, 12347997);
        logger.info("status verification: " + status3.name());
    }

    @Test
    @DisplayName("send verification push message")
    void sendVerifiatiomPushMessage() {
        VerificationClientOtherApp client = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(VerificationClientOtherApp.class, "http://localhost:"+port);

        UUID uuid = UUID.randomUUID();

        PushMessageDto pushMessageDto = new PushMessageDto();
        pushMessageDto.setTitle("Bubble Nebula");
        pushMessageDto.setClientToken(recipientToken);
        pushMessageDto.setBody("It's found today at 11:36");
        pushMessageDto.setUrlIcon("https://peter-gribanov.github.io/serviceworker/Bubble-Nebula.jpg");
        pushMessageDto.setUrlImage("https://peter-gribanov.github.io/serviceworker/Bubble-Nebula.jpg");
        pushMessageDto.setUrlAction("http://localhost:"+port+"/verification/"+uuid.toString()+"?code=1234");
        pushMessageDto.setTtlInSeconds("0");

        VerificationPushMessage message = new VerificationPushMessage(pushMessageDto);
        message.setVerificationCode(1234);
        message.setProcessUuid(uuid);
        message.setVerificationStatus(VerificationStatus.SEND);

        VerificationStatus status2 = client.sendCodeInPushMessage(message, null);
        logger.info("status verification: " + status2.name());

        VerificationStatus status3 = client.verification(uuid, 123467);
        logger.info("status verification: " + status3.name());
    }

    @Test
    @DisplayName("send verification discord message")
    void sendVerifiatiomDiscordMessage() {
        VerificationClientOtherApp client = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(VerificationClientOtherApp.class, "http://localhost:"+port);

        UUID uuid = UUID.randomUUID();

        DiscrodTextMessageDto discrodTextMessageDto =
                new DiscrodTextMessageDto("663756725069021195", "Ваш проверочный код: 1234");

        VerificationDiscordTextMessage discordTextMessage = new VerificationDiscordTextMessage(discrodTextMessageDto);
        discordTextMessage.setVerificationCode(1234);
        discordTextMessage.setProcessUuid(uuid);
        discordTextMessage.setVerificationStatus(VerificationStatus.SEND);

        VerificationStatus status2 = client.sendCodeInDiscordMessage(discordTextMessage, null);
        logger.info("status verification: " + status2.name());

        VerificationStatus status3 = client.verification(uuid, 1234);
        logger.info("status verification: " + status3.name());
    }

    @Test
    @DisplayName("send verification telegram message")
    void sendVerifiatiomTelegramMessage() {
        VerificationClientOtherApp client = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(VerificationClientOtherApp.class, "http://localhost:"+port);

        UUID uuid = UUID.randomUUID();

        TelegramTextMessageDto messageDto = new TelegramTextMessageDto(recipientChatId, "Ваш проверочный код: 1234");

        VerificationTelegramTextMessage message = new VerificationTelegramTextMessage(messageDto);
        message.setVerificationCode(1234);
        message.setProcessUuid(uuid);
        message.setVerificationStatus(VerificationStatus.SEND);

        VerificationStatus status2 = client.sendCodeInTelegramMessage(message, null);
        logger.info("status verification: " + status2.name());

        VerificationStatus status3 = client.verification(uuid, 1234);
        logger.info("status verification: " + status3.name());
    }

    @Test
    @DisplayName("send verification vk message")
    void sendVerifiatiomVkMessage() {
        VerificationClientOtherApp client = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(VerificationClientOtherApp.class, "http://localhost:"+port);

        UUID uuid = UUID.randomUUID();

        VkTextMessageDto textMessage = new VkTextMessageDto(userId, "Ваш проверочный код: 1234");

        VerificationVkTextMessage message = new VerificationVkTextMessage(textMessage);
        message.setVerificationCode(1234);
        message.setProcessUuid(uuid);
        message.setVerificationStatus(VerificationStatus.SEND);

        VerificationStatus status2 = client.sendCodeInVkMessage(message, null);
        /*logger.info("status verification: " + status2.name());

        VerificationStatus status3 = client.verification(uuid, 1234);
        logger.info("status verification: " + status3.name());*/
    }

}
