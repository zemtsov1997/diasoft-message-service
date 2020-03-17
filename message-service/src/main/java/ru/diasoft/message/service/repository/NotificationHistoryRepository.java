package ru.diasoft.message.service.repository;

import org.springframework.data.repository.CrudRepository;
import ru.diasoft.message.service.entity.NotificationHistory;

public interface NotificationHistoryRepository extends CrudRepository<NotificationHistory, Long> {
}
