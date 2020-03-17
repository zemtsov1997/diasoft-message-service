package ru.diasoft.message.service.repository;

import org.springframework.data.repository.CrudRepository;
import ru.diasoft.message.service.entity.VerificationHistory;

import java.util.UUID;

public interface VerificationHistoryRepository extends CrudRepository<VerificationHistory, Long> {

    VerificationHistory findFirstByProcessUuidOrderByDateTimeDesc(UUID processUuid);

    VerificationHistory findFirstBySocialUserIdOrderByDateTimeDesc(Long socialUserId);

}
