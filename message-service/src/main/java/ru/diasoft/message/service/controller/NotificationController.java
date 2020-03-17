package ru.diasoft.message.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.message.api.dto.notification.PushMessageDto;
import ru.diasoft.message.api.model.notification.INotificationController;
import ru.diasoft.message.service.client.NotificationClient;
import ru.diasoft.message.service.entity.NotificationHistory;
import ru.diasoft.message.service.repository.NotificationHistoryRepository;

@RestController
public class NotificationController implements INotificationController<PushMessageDto> {

    @Autowired private NotificationClient notificationClient;
    @Autowired private NotificationHistoryRepository notificationHistoryRepository;

    @Override
    public void send(PushMessageDto obj) {
        notificationClient.sendPersonal(obj);
        notificationHistoryRepository.save(new NotificationHistory(obj));
    }

}
