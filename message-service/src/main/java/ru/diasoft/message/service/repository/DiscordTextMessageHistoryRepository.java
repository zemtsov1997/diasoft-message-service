package ru.diasoft.message.service.repository;

import org.springframework.data.repository.CrudRepository;
import ru.diasoft.message.service.entity.DiscordTextMessageHistory;

public interface DiscordTextMessageHistoryRepository extends CrudRepository<DiscordTextMessageHistory, Long> {
}
