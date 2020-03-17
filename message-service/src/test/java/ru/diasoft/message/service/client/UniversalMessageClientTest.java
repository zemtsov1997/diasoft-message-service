package ru.diasoft.message.service.client;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.diasoft.message.api.dto.vk.VkTextMessageDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {
        "classpath:bot.vk.recipient.properties"
})
@DisplayName("Testing UniversalMessageClientTest")
public class UniversalMessageClientTest {

    @Autowired private UniversalMessageClient universalMessageClient;

    @Value("${bot.vk.recipient.id:ид получателя}")
    private Integer userId;

    @Test
    @DisplayName("send vk message")
    void sendDiscordTextMessage() {
        VkTextMessageDto textMessage = new VkTextMessageDto(userId, "Ваш баланс такой: .....");
        universalMessageClient.sendMessage(textMessage);
    }

}
