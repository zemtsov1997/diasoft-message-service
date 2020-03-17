package ru.diasoft.message.service.client;

import com.google.firebase.messaging.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.diasoft.message.api.model.notification.IPushMessage;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class NotificationClient {

    private static final Logger logger = LogManager.getLogger(NotificationClient.class);

    @Autowired private FirebaseMessaging firebaseMessaging;

    /**
     * Метод производит отправку уведомлений пользователям
     * подписанным на заданную тему.
     *
     * @param pushMessage сообщение для отправки
     * @param topic заданная тема
     */
    public String sendByTopic(IPushMessage pushMessage, String topic) {
        Message message = Message.builder().setTopic(topic)
                .setWebpushConfig(WebpushConfig.builder()
                        .putHeader("ttl", pushMessage.getTtlInSeconds())
                        .setNotification(createBuilder(pushMessage).build())
                        .build())
                .build();

        String response = null;
        try {
            response = firebaseMessaging
                    .sendAsync(message)
                    .get();
        } catch (InterruptedException e) {
            logger.error(e);
        } catch (ExecutionException e) {
            logger.error(e);
        }
        return response;
    }

    /**
     * Метод реализует отправку персонального
     * уведомления пользователю
     *
     * @param pushMessage сообщение для отправки
     */
    public String sendPersonal(IPushMessage pushMessage) {
        Message message = Message.builder().setToken(pushMessage.getClientToken())
                .setWebpushConfig(WebpushConfig.builder()
                        .putHeader("ttl", pushMessage.getTtlInSeconds())
                        .setNotification(createBuilder(pushMessage).build())
                        .build())
                .build();

        String response = null;
        try {
            response = firebaseMessaging
                    .sendAsync(message)
                    .get();
        } catch (InterruptedException e) {
            logger.error(e);
        } catch (ExecutionException e) {
            logger.error(e);
        }
        return response;
    }

    /**
     * Метод подписывет на тему указанных пользователей
     *
     * @param topic заданная тема
     * @param clientTokens токены пользователя
     */
    public void subscribeUsers(String topic, List<String> clientTokens) {
        try {
            for (String token : clientTokens) {
                TopicManagementResponse response = firebaseMessaging
                        .subscribeToTopic(Collections.singletonList(token), topic);
            }
        } catch (FirebaseMessagingException e) {
            logger.error(e);
        }
    }

    private WebpushNotification.Builder createBuilder(IPushMessage pushMessage){
        WebpushNotification.Builder builder = WebpushNotification.builder();
        builder
                .setTitle(pushMessage.getTitle())
                .setBody(pushMessage.getBody())
                .setImage(pushMessage.getUrlImage())
                .setIcon(pushMessage.getUrlIcon())
                .addAction(new WebpushNotification.Action(pushMessage.getUrlAction(), "Открыть"));
        return builder;
    }

}
