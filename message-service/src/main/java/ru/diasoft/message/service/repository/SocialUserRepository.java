package ru.diasoft.message.service.repository;

import org.springframework.data.repository.CrudRepository;
import ru.diasoft.message.service.entity.SocialUser;

public interface SocialUserRepository extends CrudRepository<SocialUser, Long> { }
