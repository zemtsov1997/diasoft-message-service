package ru.diasoft.message.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.diasoft.message.api.model.IMessage;
import ru.diasoft.message.api.model.discord.IDiscordMessage;
import ru.diasoft.message.api.model.discord.IDiscrodTextMessage;
import ru.diasoft.message.api.model.mail.IMail;
import ru.diasoft.message.api.model.mail.IMimeMail;
import ru.diasoft.message.api.model.mail.ISimpleMail;
import ru.diasoft.message.api.model.notification.IPushMessage;
import ru.diasoft.message.api.model.sms.ISms;
import ru.diasoft.message.api.model.telegram.ITelegramMessage;
import ru.diasoft.message.api.model.telegram.ITelegramTextMessage;
import ru.diasoft.message.api.model.vk.IVkMessage;
import ru.diasoft.message.api.model.vk.IVkTextMessage;

@Component
public class UniversalMessageClient {

    @Autowired private MailClient mailClient;
    @Autowired private NotificationClient notificationClient;
    @Autowired private DiscordClient discordClient;
    @Autowired private TelegramClient telegramClient;
    @Autowired private VkClient vkClient;
    @Autowired private SmsClient smsClient;

    public void sendMessage(IMessage message) {
        if (message instanceof IMail) {
            if (message instanceof ISimpleMail) mailClient.sendSimpleMessage((ISimpleMail) message); else
                if (message instanceof IMimeMail) mailClient.sendMimeMessage((IMimeMail) message);
        } else
            if (message instanceof IPushMessage) notificationClient.sendPersonal((IPushMessage) message); else
                if (message instanceof IDiscordMessage) discordClient.sendMessage((IDiscrodTextMessage) message); else
                    if (message instanceof ITelegramMessage) telegramClient.sendMessage((ITelegramTextMessage) message); else
                        if (message instanceof IVkMessage) vkClient.sendMessage((IVkTextMessage) message); else
                            if (message instanceof ISms) smsClient.sendSms((ISms) message);
    }

}
