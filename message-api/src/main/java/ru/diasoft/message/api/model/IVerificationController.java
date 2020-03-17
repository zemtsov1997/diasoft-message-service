package ru.diasoft.message.api.model;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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

public interface IVerificationController <
        T1 extends IVerificationMessage & ISimpleMail,
        T2 extends IVerificationMessage & IMimeMail,
        T3 extends IVerificationMessage & ISms,
        T4 extends IVerificationMessage & IPushMessage,
        T5 extends IVerificationMessage & IDiscordMessage,
        T6 extends IVerificationMessage & ITelegramMessage,
        T7 extends IVerificationMessage & IVkMessage
> {

    @PostMapping(value = "/verification/send_code/simple_mail", produces = MediaType.APPLICATION_JSON_VALUE)
    VerificationStatus sendCodeInSimpleMail(@RequestBody T1 message, @RequestParam(value = "dateTime", required = false) LocalDateTime dateTime);

    @PostMapping(value = "/verification/send_code/mime_mail", produces = MediaType.APPLICATION_JSON_VALUE)
    VerificationStatus sendCodeInMimeMail(@RequestBody T2 message, @RequestParam(value = "dateTime", required = false) LocalDateTime dateTime);

    @PostMapping(value = "/verification/send_code/sms", produces = MediaType.APPLICATION_JSON_VALUE)
    VerificationStatus sendCodeInSms(@RequestBody T3 message, @RequestParam(value = "dateTime", required = false) LocalDateTime dateTime);

    @PostMapping(value = "/verification/send_code/push_message", produces = MediaType.APPLICATION_JSON_VALUE)
    VerificationStatus sendCodeInPushMesage(@RequestBody T4 message, @RequestParam(value = "dateTime", required = false) LocalDateTime dateTime);

    @PostMapping(value = "/verification/send_code/discord_message", produces = MediaType.APPLICATION_JSON_VALUE)
    VerificationStatus sendCodeInDiscordMessage(@RequestBody T5 message, @RequestParam(value = "dateTime", required = false) LocalDateTime dateTime);

    @PostMapping(value = "/verification/send_code/telegram_message", produces = MediaType.APPLICATION_JSON_VALUE)
    VerificationStatus sendCodeInTelegramMessage(@RequestBody T6 message, @RequestParam(value = "dateTime", required = false) LocalDateTime dateTime);

    @PostMapping(value = "/verification/send_code/vk_message", produces = MediaType.APPLICATION_JSON_VALUE)
    VerificationStatus sendCodeInVkMessage(@RequestBody T7 message, @RequestParam(value = "dateTime", required = false) LocalDateTime dateTime);

    @GetMapping(value = "/verification/{processUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    VerificationStatus verification(@PathVariable(name = "processUuid") UUID processUuid, @RequestParam(value = "code") Integer code);

    @GetMapping(value = "/verification/{processUuid}/get_status", produces = MediaType.APPLICATION_JSON_VALUE)
    VerificationStatus getStatus(@PathVariable(name = "processUuid") UUID processUuid);

}
