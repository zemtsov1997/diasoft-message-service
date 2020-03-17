package ru.diasoft.message.service.repository;

import org.springframework.data.repository.CrudRepository;
import ru.diasoft.message.service.entity.SimpleMailHistory;

public interface SimpleMailHistoryRepository extends CrudRepository<SimpleMailHistory, Long> {
}
