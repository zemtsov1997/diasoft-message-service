package ru.diasoft.message.api.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.LocalDateTime;

public interface TimeClient<T> {

    Logger logger = LogManager.getLogger(TimeClient.class);

    void sendMessage(T message);

    default void waitAndSend(T message, LocalDateTime localDateTime, Integer maxMinutesForSendMessage) {
        if (localDateTime == null) {
            sendMessage(message);
        } else {
            new Thread(() -> {
                Long betweenMillis = Duration.between(LocalDateTime.now(), localDateTime).toMillis();
                Long maxMillis = Long.valueOf(maxMinutesForSendMessage * 60 * 1000);
                if (betweenMillis > 0) {
                    if (maxMillis >= betweenMillis) {
                        try {
                            logger.info("sleep "+(betweenMillis/1000)+" seconds");
                            Thread.sleep(betweenMillis);
                            logger.info("sleep end");
                        } catch (InterruptedException e) {
                            logger.error(e);
                        }
                    } else {
                        try {
                            logger.info("sleep max seconds - "+(betweenMillis/1000));
                            Thread.sleep(maxMillis);
                            logger.info("sleep end");
                        } catch (InterruptedException e) {
                            logger.error(e);
                        }
                    }
                }

                sendMessage(message);
                logger.info("send msg");
            }).start();
        }
    }

}
