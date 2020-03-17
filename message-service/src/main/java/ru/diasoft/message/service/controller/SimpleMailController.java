package ru.diasoft.message.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.message.api.dto.mail.SimpleMailDto;
import ru.diasoft.message.api.model.mail.ISimpleMailController;
import ru.diasoft.message.service.client.MailClient;
import ru.diasoft.message.service.entity.SimpleMailHistory;
import ru.diasoft.message.service.repository.SimpleMailHistoryRepository;

@RestController
public class SimpleMailController implements ISimpleMailController<SimpleMailDto> {

    @Autowired private MailClient mailClient;
    @Autowired private SimpleMailHistoryRepository simpleMailHistoryRepository;

    @Override
    public void send(SimpleMailDto obj) {
        mailClient.sendSimpleMessage(obj);
        simpleMailHistoryRepository.save(new SimpleMailHistory(obj));
    }

}
