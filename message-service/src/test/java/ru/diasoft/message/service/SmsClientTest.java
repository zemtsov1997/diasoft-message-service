package ru.diasoft.message.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.diasoft.message.api.dto.sms.SmsDto;
import ru.diasoft.message.service.client.SmsClient;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {
        "classpath:email.recipient.properties",
        "classpath:email.sender.properties",
        "classpath:sms.sender.properties"
})
@DisplayName("Testing SmsServiceTest")
public class SmsClientTest {

    @Autowired private SmsClient smsClient;

    @Test
    @DisplayName("send sms")
    void sendSms() {
        List<String> numberPhones = new LinkedList<>();
        numberPhones.add("ваш номер");

        SmsDto smsDto = new SmsDto();
        smsDto.setMessage("МНе большое тестированиеее ENISH...");
        smsDto.setNumberPhoneRecipients(numberPhones);

        smsClient.sendSms(smsDto);
    }

    @Test
    @DisplayName("cost sms")
    void costSms() {
        List<String> numberPhones = new LinkedList<>();
        numberPhones.add("ваш номер");

        SmsDto smsDto = new SmsDto();
        smsDto.setMessage("Я Алексей, тестирую сообщение ENGLISH...");
        smsDto.setNumberPhoneRecipients(numberPhones);

        smsClient.getCostSms(smsDto);
    }

    @Test
    @DisplayName("balance testing")
    void getBalance() {
        System.out.println("Ваш баланс: " + smsClient.getBalance());
    }

}
