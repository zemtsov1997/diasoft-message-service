package ru.diasoft.message.service.config;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.diasoft.message.service.model.UniversalNetworkBusinessLogic;
import ru.diasoft.message.service.model.discord.DiscordBot;
import ru.diasoft.message.service.service.SocialUserService;

import javax.security.auth.login.LoginException;

@Configuration
public class DiscordConfig {

    @Value("${bot.discord.auth-token}")
    private String authToken;

    @Autowired private UniversalNetworkBusinessLogic universalNetworkBusinessLogic;
    @Autowired private SocialUserService socialUserService;

    @Bean
    public DiscordBot discordBot() {
        return new DiscordBot(universalNetworkBusinessLogic, socialUserService);
    }

    @Bean
    public JDA jdaBuilder(@Autowired DiscordBot discordBot) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        builder.setToken(authToken);
        builder.addEventListeners(discordBot);
        return builder.build();
    }

}
