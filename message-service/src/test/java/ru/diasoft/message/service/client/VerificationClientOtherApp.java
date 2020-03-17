package ru.diasoft.message.service.client;

import ru.diasoft.message.api.model.IVerificationClient;
import ru.diasoft.message.service.model.discord.VerificationDiscordTextMessage;
import ru.diasoft.message.service.model.mail.VerificationMimeMail;
import ru.diasoft.message.service.model.mail.VerificationSimpleMail;
import ru.diasoft.message.service.model.notification.VerificationPushMessage;
import ru.diasoft.message.service.model.sms.VerificationSms;
import ru.diasoft.message.service.model.telegram.VerificationTelegramTextMessage;
import ru.diasoft.message.service.model.vk.VerificationVkTextMessage;

public interface VerificationClientOtherApp extends IVerificationClient<
        VerificationSimpleMail,
        VerificationMimeMail,
        VerificationSms,
        VerificationPushMessage,
        VerificationDiscordTextMessage,
        VerificationTelegramTextMessage,
        VerificationVkTextMessage
> { }
