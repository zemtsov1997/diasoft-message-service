package ru.diasoft.message.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.message.api.dto.sms.SmsDto;
import ru.diasoft.message.api.model.sms.ISmsController;
import ru.diasoft.message.service.client.SmsClient;
import ru.diasoft.message.service.entity.SmsHistory;
import ru.diasoft.message.service.repository.SmsHistoryRepository;

import java.math.BigDecimal;

@RestController
public class SmsController implements ISmsController<SmsDto> {

    @Autowired private SmsClient smsClient;
    @Autowired private SmsHistoryRepository smsHistoryRepository;

    @Override
    public void send(SmsDto obj) {
        smsClient.sendSms(obj);
        smsHistoryRepository.save(new SmsHistory(obj));
    }

    @Override
    public BigDecimal cost(SmsDto obj) {
        return smsClient.getCostSms(obj);
    }

    @Override
    public BigDecimal balance() {
        return smsClient.getBalance();
    }

}
