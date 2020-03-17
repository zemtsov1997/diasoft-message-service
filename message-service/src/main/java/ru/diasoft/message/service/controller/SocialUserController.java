package ru.diasoft.message.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.message.api.dao.ISocialUserControllerDao;
import ru.diasoft.message.service.entity.SocialUser;
import ru.diasoft.message.service.repository.SocialUserRepository;
import ru.diasoft.message.service.service.SocialUserService;
import ru.diasoft.service.api.exeption.EntityNotFoundException;

import java.util.List;
import java.util.Map;

@RestController
public class SocialUserController implements ISocialUserControllerDao<SocialUser> {

    @Autowired private SocialUserRepository socialUserRepository;
    @Autowired private SocialUserService socialUserService;

    @Override
    public SocialUser find(Long id) {
        return socialUserRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public SocialUser find(Map<String, Object> params) {
        List<SocialUser> list = socialUserService.findAll(params);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public SocialUser create(SocialUser obj) {
        return socialUserRepository.save(obj);
    }

    @Override
    public SocialUser update(Long id, SocialUser newObj) {
        return socialUserRepository.findById(id)
                .map(socialUser -> {
                    socialUser.setEmail(newObj.getEmail());
                    socialUser.setNumberPhone(newObj.getNumberPhone());
                    socialUser.setNotificationToken(newObj.getNotificationToken());
                    socialUser.setTelegramChatId(newObj.getTelegramChatId());
                    socialUser.setVkUserId(newObj.getVkUserId());
                    socialUser.setDiscordUserId(newObj.getDiscordUserId());
                    return socialUserRepository.save(socialUser);
                }).orElseGet(() -> {
                    newObj.setId(id);
                    return socialUserRepository.save(newObj);
                });
    }

    @Override
    public void delete(Long id) {
        socialUserRepository.deleteById(id);
    }
}
