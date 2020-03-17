package ru.diasoft.message.api.model;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import ru.diasoft.message.api.enums.VerificationStatus;
import ru.diasoft.message.api.model.discord.IDiscordMessage;
import ru.diasoft.message.api.model.mail.IMimeMail;
import ru.diasoft.message.api.model.mail.ISimpleMail;
import ru.diasoft.message.api.model.notification.IPushMessage;
import ru.diasoft.message.api.model.sms.ISms;
import ru.diasoft.message.api.model.telegram.ITelegramMessage;
import ru.diasoft.message.api.model.vk.IVkMessage;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IVerificationClient<
        T1 extends IVerificationMessage & ISimpleMail,
        T2 extends IVerificationMessage & IMimeMail,
        T3 extends IVerificationMessage & ISms,
        T4 extends IVerificationMessage & IPushMessage,
        T5 extends IVerificationMessage & IDiscordMessage,
        T6 extends IVerificationMessage & ITelegramMessage,
        T7 extends IVerificationMessage & IVkMessage
> {

    @RequestLine("POST /verification/send_code/simple_mail?dateTime={dateTime}")
    @Headers("Content-Type: application/json")
    VerificationStatus sendCodeInSimpleMail(T1 obj, @Param(value = "dateTime") LocalDateTime dateTime);

    @RequestLine("POST /verification/send_code/mime_mail?dateTime={dateTime}")
    @Headers("Content-Type: application/json")
    VerificationStatus sendCodeInMimeMail(T2 obj, @Param(value = "dateTime") LocalDateTime dateTime);

    @RequestLine("POST /verification/send_code/sms?dateTime={dateTime}")
    @Headers("Content-Type: application/json")
    VerificationStatus sendCodeInSms(T3 obj, @Param(value = "dateTime") LocalDateTime dateTime);

    @RequestLine("POST /verification/send_code/push_message?dateTime={dateTime}")
    @Headers("Content-Type: application/json")
    VerificationStatus sendCodeInPushMessage(T4 obj, @Param(value = "dateTime") LocalDateTime dateTime);

    @RequestLine("POST /verification/send_code/discord_message?dateTime={dateTime}")
    @Headers("Content-Type: application/json")
    VerificationStatus sendCodeInDiscordMessage(T5 obj, @Param(value = "dateTime") LocalDateTime dateTime);

    @RequestLine("POST /verification/send_code/telegram_message?dateTime={dateTime}")
    @Headers("Content-Type: application/json")
    VerificationStatus sendCodeInTelegramMessage(T6 obj, @Param(value = "dateTime") LocalDateTime dateTime);

    @RequestLine("POST /verification/send_code/vk_message?dateTime={dateTime}")
    @Headers("Content-Type: application/json")
    VerificationStatus sendCodeInVkMessage(T7 obj, @Param(value = "dateTime") LocalDateTime dateTime);

    @RequestLine("GET /verification/{processUuid}?code={code}")
    @Headers("Content-Type: application/json")
    VerificationStatus verification(@Param(value = "processUuid") UUID processUuid, @Param(value = "code") Integer code);

    @RequestLine("GET /verification/{processUuid}/get_status")
    @Headers("Content-Type: application/json")
    VerificationStatus getStatus(@Param(value = "processUuid") UUID processUuid);

}
