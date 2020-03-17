package ru.diasoft.message.service.client;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import ru.diasoft.message.api.dto.SocialUserDto;
import ru.diasoft.message.api.props.SocialUserRequestParams;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Testing SocialUserClientOtherAppTest")
public class SocialUserClientOtherAppTest {

    @LocalServerPort
    private int port;

    @Test
    @DisplayName("create method from client")
    public void create() {
        SocialUserClientOtherApp messageClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(SocialUserClientOtherApp.class, "http://localhost:"+port);

        SocialUserDto socialUser = new SocialUserDto();
        socialUser.setEmail("TestEmail");

        messageClient.create(socialUser);
    }

    @Test
    @DisplayName("update method from client")
    public void update() {
        SocialUserClientOtherApp messageClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(SocialUserClientOtherApp.class, "http://localhost:"+port);

        SocialUserDto socialUser = new SocialUserDto();
        socialUser.setEmail("TestEmail@mail.ru");

        SocialUserDto socialUser1 = messageClient.create(socialUser);
        socialUser1.setEmail("test2email.@outlookc.com");

        SocialUserDto socialUser2 = messageClient.update(socialUser1.getId(), socialUser1);
        System.out.println(socialUser2.getEmail());
    }

    @Test
    @DisplayName("delete method from client")
    public void delete() {
        SocialUserClientOtherApp messageClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(SocialUserClientOtherApp.class, "http://localhost:"+port);

        SocialUserDto socialUser = new SocialUserDto();
        socialUser.setEmail("TestEmail@mail.ru");

        SocialUserDto socialUser1 = messageClient.create(socialUser);
        socialUser1.setEmail("test2email.@outlookc.com");

        messageClient.delete(socialUser1.getId());
    }

    @Test
    @DisplayName("findById method from client")
    public void findById() {
        SocialUserClientOtherApp messageClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(SocialUserClientOtherApp.class, "http://localhost:"+port);

        SocialUserDto socialUser = new SocialUserDto();
        socialUser.setEmail("TestEmail@mail.ru");

        SocialUserDto socialUser1 = messageClient.create(socialUser);

        SocialUserDto socialUser2 = messageClient.find(socialUser1.getId());

        System.out.println(socialUser2.getEmail());
    }

    @Test
    @DisplayName("findByParams method from client")
    public void findByParams() {
        SocialUserClientOtherApp messageClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(SocialUserClientOtherApp.class, "http://localhost:"+port);

        SocialUserDto socialUser = new SocialUserDto();
        socialUser.setEmail("TestEmail@mail.ru");
        socialUser.setDiscordUserId("123123123");

        SocialUserDto socialUser1 = messageClient.create(socialUser);

        Map<String, Object> map = new HashMap<>();
        map.put(SocialUserRequestParams.EMAIL, socialUser1.getEmail());
        map.put(SocialUserRequestParams.DISCORD_USER_ID, socialUser1.getDiscordUserId());

        SocialUserDto socialUser2 = messageClient.find(map);

        System.out.println(socialUser2.getEmail());
    }

}
