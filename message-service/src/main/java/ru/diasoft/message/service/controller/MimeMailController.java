package ru.diasoft.message.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.message.api.dto.mail.MimeMailDto;
import ru.diasoft.message.api.model.mail.IMimeMailController;
import ru.diasoft.message.service.client.MailClient;
import ru.diasoft.message.service.entity.MimeMailHistory;
import ru.diasoft.message.service.repository.MimeMailHistoryRepository;

@RestController
public class MimeMailController implements IMimeMailController<MimeMailDto> {

    @Autowired private MailClient mailClient;
    @Autowired private MimeMailHistoryRepository mimeMailHistoryRepository;

    @Override
    public void send(MimeMailDto obj) {
        mailClient.sendMimeMessage(obj);
        mimeMailHistoryRepository.save(new MimeMailHistory(obj));
    }

}
