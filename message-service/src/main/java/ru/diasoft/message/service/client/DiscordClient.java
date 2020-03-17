package ru.diasoft.message.service.client;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.diasoft.message.api.model.TimeClient;
import ru.diasoft.message.api.model.discord.IDiscrodTextMessage;
import ru.diasoft.message.service.model.discord.DiscordBot;

@Component
public class DiscordClient implements TimeClient<IDiscrodTextMessage> {

    @Autowired private DiscordBot discordBot;
    @Autowired private JDA jda;

    public void sendMessage(IDiscrodTextMessage discrodTextMessage) {
        User user = jda.getUserById(discrodTextMessage.getUserId());
        discordBot.sendPrivateMessage(user, discrodTextMessage.getMessage());
    }

}
