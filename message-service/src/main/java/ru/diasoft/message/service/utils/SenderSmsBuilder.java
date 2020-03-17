package ru.diasoft.message.service.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.diasoft.message.api.dto.sms.SenderSmsDto;
import ru.diasoft.message.api.model.sms.ISenderSms;

@Service
public class SenderSmsBuilder {

    @Value("${sms.login}")
    private String smsLogin;

    @Value("${sms.password}")
    private String smsPassword;

    @Value("${sms.sender.name}")
    private String senderName;

    public ISenderSms getDefault() {
        SenderSmsDto senderSmsDto = new SenderSmsDto();
        senderSmsDto.setLogin(smsLogin);
        senderSmsDto.setPassword(smsPassword);
        senderSmsDto.setName(senderName);
        return senderSmsDto;
    }

}
