package ru.diasoft.message.service.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import ru.diasoft.message.service.model.UniversalNetworkBusinessLogic;
import ru.diasoft.message.service.model.telegram.TelegramBot;
import ru.diasoft.message.service.service.SocialUserService;

@Configuration
public class TelegramConfig {

    private static final Logger logger = LogManager.getLogger(TelegramConfig.class);

    @Value("${bot.telegram.name}")
    private String botName;

    @Value("${bot.telegram.auth-token}")
    private String authToken;

    @Autowired private UniversalNetworkBusinessLogic universalNetworkBusinessLogic;
    @Autowired private SocialUserService socialUserService;

    @Bean
    public TelegramBotsApi telegramBotsApi() {
        ApiContextInitializer.init();
        return new TelegramBotsApi();
    }

    @Bean
    public TelegramBot telegramBot(@Autowired TelegramBotsApi telegramBotsApi) {
        TelegramBot telegramBot = new TelegramBot(botName, authToken, universalNetworkBusinessLogic, socialUserService);
        try {
            telegramBotsApi.registerBot(telegramBot);
        } catch (TelegramApiRequestException e) {
            logger.error(e);
        }
        return telegramBot;
    }

}
