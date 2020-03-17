package ru.diasoft.message.service.client;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import ru.diasoft.message.api.dto.sms.SmsDto;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {
        "classpath:sms.sender.properties"
})
@DisplayName("Testing SmsClientOtherAppTest")
public class SmsClientOtherAppTest {

    @LocalServerPort
    private int port;

    @Test
    @DisplayName("test sms client")
    void sendDiscordTextMessage() {
        List<String> numberPhones = new LinkedList<>();
        numberPhones.add("номер телефона");

        SmsDto smsDto = new SmsDto();
        smsDto.setMessage("МНе большое тестированиеее ENISH...");
        smsDto.setNumberPhoneRecipients(numberPhones);

        SmsClientOtherApp client = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(SmsClientOtherApp.class, "http://localhost:"+port);

        client.send(smsDto);
    }

}
