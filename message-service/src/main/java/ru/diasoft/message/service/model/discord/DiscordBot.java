package ru.diasoft.message.service.model.discord;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.diasoft.message.service.entity.SocialUser;
import ru.diasoft.message.service.model.UniversalNetworkBusinessLogic;
import ru.diasoft.message.service.service.SocialUserService;

import javax.annotation.Nonnull;

public class DiscordBot extends ListenerAdapter {

    private static final Logger logger = LogManager.getLogger(DiscordBot.class);

    private UniversalNetworkBusinessLogic universalNetworkBusinessLogic;
    private SocialUserService socialUserService;

    public DiscordBot(UniversalNetworkBusinessLogic universalNetworkBusinessLogic, SocialUserService socialUserService) {
        this.universalNetworkBusinessLogic = universalNetworkBusinessLogic;
        this.socialUserService = socialUserService;
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        logger.debug("We received a message from "+
                event.getAuthor().getName() + ": "+
                event.getMessage().getContentDisplay()
        );
        String message = event.getMessage().getContentRaw();
        SocialUser socialUser = socialUserService.findByDiscordUserId(event.getAuthor().getId());
        String answer = universalNetworkBusinessLogic.setMessage(message, socialUser != null ? socialUser.getId() : null);
        sendPrivateMessage(event.getAuthor(), answer);
    }

    public void sendPrivateMessage(User user, String content) {
        // openPrivateChannel provides a RestAction<PrivateChannel>
        // which means it supplies you with the resulting channel
        user.openPrivateChannel().queue((channel) ->
        {
            channel.sendMessage(content).queue();
        });
    }
}
