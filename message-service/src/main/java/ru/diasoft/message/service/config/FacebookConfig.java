package ru.diasoft.message.service.config;

import com.clivern.racter.BotPlatform;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class FacebookConfig {

    @Value("${bot.facebook.app-id}")
    private String appId;

    @Value("${bot.facebook.verify-token}")
    private String verifyToken;

    @Value("${bot.facebook.page-access-token}")
    private String pageAccessToken;

    @Bean
    public BotPlatform facebookBot() throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("app_id", appId);
        params.put("verify_token", verifyToken);
        params.put("page_access_token", pageAccessToken);
        return new BotPlatform(params);
    }

}
