package ru.diasoft.message.service.repository;

import org.springframework.data.repository.CrudRepository;
import ru.diasoft.message.service.entity.SmsHistory;

public interface SmsHistoryRepository extends CrudRepository<SmsHistory, Long> {
}
