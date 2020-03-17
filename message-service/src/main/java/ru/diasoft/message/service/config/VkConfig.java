package ru.diasoft.message.service.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.diasoft.message.service.model.UniversalNetworkBusinessLogic;
import ru.diasoft.message.service.model.vk.VkBot;
import ru.diasoft.message.service.service.SocialUserService;

@Configuration
public class VkConfig {

    private static final Logger logger = LogManager.getLogger(VkConfig.class);

    @Value("${bot.vk.auth-token}")
    private String authToken;
    @Value("${bot.vk.group-id}")
    private Integer groupId;

    @Autowired private UniversalNetworkBusinessLogic universalNetworkBusinessLogic;
    @Autowired private SocialUserService socialUserService;

    @Bean
    public VkBot vkBot() {
        return new VkBot(authToken, groupId, universalNetworkBusinessLogic, socialUserService);
    }

}
