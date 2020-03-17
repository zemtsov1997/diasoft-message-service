package ru.diasoft.message.service.repository;

import org.springframework.data.repository.CrudRepository;
import ru.diasoft.message.service.entity.TelegramHistory;

public interface TelegramHistoryRepository extends CrudRepository<TelegramHistory, Long> {
}
