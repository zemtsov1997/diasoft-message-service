package ru.diasoft.message.service.repository;

import org.springframework.data.repository.CrudRepository;
import ru.diasoft.message.service.entity.MimeMailHistory;

public interface MimeMailHistoryRepository extends CrudRepository<MimeMailHistory, Long> {
}
