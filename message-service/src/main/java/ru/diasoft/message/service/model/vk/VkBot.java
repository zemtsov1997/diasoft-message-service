package ru.diasoft.message.service.model.vk;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;
import ru.diasoft.message.service.entity.SocialUser;
import ru.diasoft.message.service.model.UniversalNetworkBusinessLogic;
import ru.diasoft.message.service.service.SocialUserService;

public class VkBot {

    private String authToken;
    private Integer groupId;

    private Group group;

    private UniversalNetworkBusinessLogic universalNetworkBusinessLogic;

    private SocialUserService socialUserService;

    public VkBot(String authToken, Integer groupId, UniversalNetworkBusinessLogic universalNetworkBusinessLogic, SocialUserService socialUserService) {
        this.authToken = authToken;
        this.groupId = groupId;
        this.universalNetworkBusinessLogic = universalNetworkBusinessLogic;
        this.socialUserService = socialUserService;
        this.init();
    }

    private void init() {
        group = new Group(groupId, authToken);

        group.onSimpleTextMessage(message -> {
            SocialUser socialUser = socialUserService.findByVkUserId(message.authorId());
                new Message()
                .from(group)
                .to(message.authorId())
                .text(universalNetworkBusinessLogic.setMessage(message.getText(), socialUser != null ? socialUser.getId() : null))
                .send();
            }
        );
    }

    public void sendMessage(Integer userId, String text) {
        new Message()
                .from(group)
                .to(userId)
                .text(text)
                .send();
    }
}
