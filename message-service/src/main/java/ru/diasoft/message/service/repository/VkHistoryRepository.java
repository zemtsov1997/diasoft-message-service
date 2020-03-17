package ru.diasoft.message.service.repository;

import org.springframework.data.repository.CrudRepository;
import ru.diasoft.message.service.entity.VkHistory;

public interface VkHistoryRepository extends CrudRepository<VkHistory, Long> {
}
