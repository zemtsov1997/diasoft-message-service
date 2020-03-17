package ru.diasoft.message.service.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.diasoft.message.api.props.PersistenceSocialUserProps;
import ru.diasoft.message.api.props.SocialUserRequestParams;
import ru.diasoft.message.service.entity.SocialUser;
import ru.diasoft.service.api.PersistenceSeparator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SocialUserService {

    @PersistenceContext private EntityManager entityManager;

    public SocialUser findByEmail(String email) {
        Map<String, Object> params = new HashMap<>();
        params.put(SocialUserRequestParams.EMAIL, email);
        List<SocialUser> list = findAll(params);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public SocialUser findByNumberPhone(String numberPhone) {
        Map<String, Object> params = new HashMap<>();
        params.put(SocialUserRequestParams.NUMBER_PHONE, numberPhone);
        List<SocialUser> list = findAll(params);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public SocialUser findByNotificationToken(String notificationToken) {
        Map<String, Object> params = new HashMap<>();
        params.put(SocialUserRequestParams.NOTIFICATION_TOKEN, notificationToken);
        List<SocialUser> list = findAll(params);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public SocialUser findByTelegramChatId(String telegramChatId) {
        Map<String, Object> params = new HashMap<>();
        params.put(SocialUserRequestParams.TELEGRAM_CHAT_ID, telegramChatId);
        List<SocialUser> list = findAll(params);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public SocialUser findByVkUserId(Integer vkUerId) {
        Map<String, Object> params = new HashMap<>();
        params.put(SocialUserRequestParams.VK_USER_ID, vkUerId);
        List<SocialUser> list = findAll(params);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public SocialUser findByDiscordUserId(String discordUerId) {
        Map<String, Object> params = new HashMap<>();
        params.put(SocialUserRequestParams.DISCORD_USER_ID, discordUerId);
        List<SocialUser> list = findAll(params);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public List<SocialUser> findAll(Map<String, Object> params) {
        @SuppressWarnings("unchecked")
        List<SocialUser> list = createQuery("SELECT socialUser FROM SocialUser socialUser", params, true).getResultList();
        return list;
    }

    private Query createQuery(String sql, Map<String, Object> params, Boolean isPagination) {
        PersistenceSeparator separator = new PersistenceSeparator();

        Object linkFieldSort = params.get(SocialUserRequestParams.FIELD_SORT);
        Object linkOrder = params.get(SocialUserRequestParams.ORDER);
        Object linkEmail = params.get(SocialUserRequestParams.EMAIL);
        Object linkNumberPhone = params.get(SocialUserRequestParams.NUMBER_PHONE);
        Object linkNotificationToken = params.get(SocialUserRequestParams.NOTIFICATION_TOKEN);
        Object linkTelegramChatId = params.get(SocialUserRequestParams.TELEGRAM_CHAT_ID);
        Object linkVkUserId = params.get(SocialUserRequestParams.VK_USER_ID);
        Object linkDiscordUserId = params.get(SocialUserRequestParams.DISCORD_USER_ID);

        String fieldSort = validParam(linkFieldSort) ? linkFieldSort.toString() : PersistenceSocialUserProps.DEFAULT_FIELD_SORT;
        String orderString = validParam(linkOrder) ? linkOrder.toString() : PersistenceSocialUserProps.DEFAULT_ORDER;
        Sort.Direction order = validParam(orderString) && orderString.equals("increase") ? Sort.Direction.ASC: Sort.Direction.DESC;

        String email = validParam(linkEmail) ? linkEmail.toString() : null;
        String numberPhone = validParam(linkNumberPhone) ? linkNumberPhone.toString() : null;
        String notificationToken = validParam(linkNotificationToken) ? linkNotificationToken.toString() : null;
        String telegramChatId = validParam(linkTelegramChatId) ? linkTelegramChatId.toString() : null;
        Integer vkUserId = validParam(linkVkUserId) ? Integer.valueOf(linkVkUserId.toString()): null;
        String discordUserId = validParam(linkDiscordUserId) ? linkDiscordUserId.toString() : null;

        if (email != null) sql += separator.get() + " socialUser.email = :email";
        if (numberPhone != null) sql += separator.get() + " socialUser.numberPhone = :numberPhone";
        if (notificationToken != null) sql += separator.get() + " socialUser.notificationToken = :notificationToken";
        if (telegramChatId != null) sql += separator.get() + " socialUser.telegramChatId = :telegramChatId";
        if (vkUserId != null) sql += separator.get() + " socialUser.vkUserId = :vkUserId";
        if (discordUserId != null) sql += separator.get() + " socialUser.discordUserId = :discordUserId";

        sql += " ORDER BY "+fieldSort+" "+order.name();

        Query query = entityManager.createQuery(sql);
        if (email != null) query.setParameter("email", email);
        if (numberPhone != null) query.setParameter("numberPhone", numberPhone);
        if (notificationToken != null) query.setParameter("notificationToken", notificationToken);
        if (telegramChatId != null) query.setParameter("telegramChatId", telegramChatId);
        if (vkUserId != null) query.setParameter("vkUserId", vkUserId);
        if (discordUserId != null) query.setParameter("discordUserId", discordUserId);

        if (isPagination) {
            Integer page = Integer.valueOf(String.valueOf(params.getOrDefault(SocialUserRequestParams.PAGE, PersistenceSocialUserProps.DEFAULT_PAGE)));
            Integer size = Integer.valueOf(String.valueOf(params.getOrDefault(SocialUserRequestParams.SIZE, PersistenceSocialUserProps.DEFAULT_SIZE)));
            query.setFirstResult(page * size);
            query.setMaxResults(size);
        }

        return query;
    }

    private Boolean validParam(Object param) {
        return param != null && !param.toString().isEmpty();
    }

}
