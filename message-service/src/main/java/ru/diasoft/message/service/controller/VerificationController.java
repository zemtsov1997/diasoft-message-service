package ru.diasoft.message.service.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.message.api.enums.VerificationStatus;
import ru.diasoft.message.api.model.IVerificationController;
import ru.diasoft.message.service.client.*;
import ru.diasoft.message.service.entity.*;
import ru.diasoft.message.service.model.discord.VerificationDiscordTextMessage;
import ru.diasoft.message.service.model.mail.VerificationMimeMail;
import ru.diasoft.message.service.model.mail.VerificationSimpleMail;
import ru.diasoft.message.service.model.notification.VerificationPushMessage;
import ru.diasoft.message.service.model.sms.VerificationSms;
import ru.diasoft.message.service.model.telegram.VerificationTelegramTextMessage;
import ru.diasoft.message.service.model.vk.VerificationVkTextMessage;
import ru.diasoft.message.service.repository.*;
import ru.diasoft.message.service.service.SocialUserService;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class VerificationController implements IVerificationController<
        VerificationSimpleMail,
        VerificationMimeMail,
        VerificationSms,
        VerificationPushMessage,
        VerificationDiscordTextMessage,
        VerificationTelegramTextMessage,
        VerificationVkTextMessage
> {

    private static final Logger logger = LogManager.getLogger(VerificationController.class);

    @Value("${application.max-minutes-for-send-message:15}")
    private Integer maxMinutesForSendMessage;

    @Autowired private SimpleMailHistoryRepository simpleMailHistoryRepository;
    @Autowired private MimeMailHistoryRepository mimeMailHistoryRepository;
    @Autowired private SmsHistoryRepository smsHistoryRepository;
    @Autowired private NotificationHistoryRepository notificationHistoryRepository;
    @Autowired private DiscordTextMessageHistoryRepository discordTextMessageHistoryRepository;
    @Autowired private TelegramHistoryRepository telegramHistoryRepository;
    @Autowired private VkHistoryRepository vkHistoryRepository;

    @Autowired private VerificationHistoryRepository verificationHistoryRepository;

    @Autowired private MailClient mailClient;
    @Autowired private SmsClient smsClient;
    @Autowired private NotificationClient notificationClient;
    @Autowired private DiscordClient discordClient;
    @Autowired private TelegramClient telegramClient;
    @Autowired private VkClient vkClient;

    @Autowired private SocialUserService socialUserService;

    @Override
    public VerificationStatus sendCodeInSimpleMail(VerificationSimpleMail message, LocalDateTime dateTime) {
        mailClient.sendSimpleMessage(message);
        simpleMailHistoryRepository.save(new SimpleMailHistory(message));
        VerificationHistory verificationHistory = new VerificationHistory(message);
        SocialUser socialUser = socialUserService.findByEmail(message.getTo());
        if (socialUser != null) verificationHistory.setSocialUserId(socialUser.getId());
        verificationHistoryRepository.save(verificationHistory);
        return verificationHistory.getVerificationStatus();
    }

    @Override
    public VerificationStatus sendCodeInMimeMail(VerificationMimeMail message, LocalDateTime dateTime) {
        mailClient.sendMimeMessage(message);
        mimeMailHistoryRepository.save(new MimeMailHistory(message));
        VerificationHistory verificationHistory = new VerificationHistory(message);
        SocialUser socialUser = socialUserService.findByEmail(message.getTo());
        if (socialUser != null) verificationHistory.setSocialUserId(socialUser.getId());
        verificationHistoryRepository.save(verificationHistory);
        return verificationHistory.getVerificationStatus();
    }

    @Override
    public VerificationStatus sendCodeInSms(VerificationSms sms, LocalDateTime dateTime) {
        smsClient.waitAndSend(sms, dateTime, maxMinutesForSendMessage);
        smsHistoryRepository.save(new SmsHistory(sms));
        VerificationHistory verificationHistory = new VerificationHistory(sms);
        if (sms.getNumberPhoneRecipients().size() > 0) {
            SocialUser socialUser = socialUserService.findByNumberPhone(sms.getNumberPhoneRecipients().get(0));
            if (socialUser != null) verificationHistory.setSocialUserId(socialUser.getId());
        }
        verificationHistoryRepository.save(verificationHistory);
        return verificationHistory.getVerificationStatus();
    }

    @Override
    public VerificationStatus sendCodeInPushMesage(VerificationPushMessage message, LocalDateTime dateTime) {
        notificationClient.sendPersonal(message);
        notificationHistoryRepository.save(new NotificationHistory(message));
        VerificationHistory verificationHistory = new VerificationHistory(message);
        SocialUser socialUser = socialUserService.findByNotificationToken(message.getClientToken());
        if (socialUser != null) verificationHistory.setSocialUserId(socialUser.getId());
        verificationHistoryRepository.save(verificationHistory);
        return verificationHistory.getVerificationStatus();
    }

    @Override
    public VerificationStatus sendCodeInDiscordMessage(VerificationDiscordTextMessage message, LocalDateTime dateTime) {
        discordClient.waitAndSend(message, dateTime, maxMinutesForSendMessage);
        discordTextMessageHistoryRepository.save(new DiscordTextMessageHistory(message));
        VerificationHistory verificationHistory = new VerificationHistory(message);
        SocialUser socialUser = socialUserService.findByDiscordUserId(message.getUserId());
        if (socialUser != null) verificationHistory.setSocialUserId(socialUser.getId());
        verificationHistoryRepository.save(verificationHistory);
        return verificationHistory.getVerificationStatus();
    }

    @Override
    public VerificationStatus sendCodeInTelegramMessage(VerificationTelegramTextMessage message, LocalDateTime dateTime) {
        telegramClient.waitAndSend(message, dateTime, maxMinutesForSendMessage);
        telegramHistoryRepository.save(new TelegramHistory(message));
        VerificationHistory verificationHistory = new VerificationHistory(message);
        SocialUser socialUser = socialUserService.findByTelegramChatId(message.getChatId());
        if (socialUser != null) verificationHistory.setSocialUserId(socialUser.getId());
        verificationHistoryRepository.save(verificationHistory);
        return verificationHistory.getVerificationStatus();
    }

    @Override
    public VerificationStatus sendCodeInVkMessage(VerificationVkTextMessage message, LocalDateTime dateTime) {
        vkClient.waitAndSend(message, dateTime, maxMinutesForSendMessage);
        vkHistoryRepository.save(new VkHistory(message));
        VerificationHistory verificationHistory = new VerificationHistory(message);
        SocialUser socialUser = socialUserService.findByVkUserId(message.getUserId());
        if (socialUser != null) verificationHistory.setSocialUserId(socialUser.getId());
        verificationHistoryRepository.save(verificationHistory);
        return verificationHistory.getVerificationStatus();
    }

    @Override
    public VerificationStatus verification(UUID processUuid, Integer code) {
        VerificationHistory verification = verificationHistoryRepository.findFirstByProcessUuidOrderByDateTimeDesc(processUuid);
        if (verification == null) {
            logger.info("VerificationHistory is null");
            return null;
        }
        if (verification.getVerificationCode().equals(code)) {
            verification.setVerificationStatus(VerificationStatus.CONFIRMED);
        } else {
            verification.setVerificationStatus(VerificationStatus.REFUSED);
        }
        verificationHistoryRepository.save(verification);
        return verification.getVerificationStatus();
    }

    @Override
    public VerificationStatus getStatus(UUID processUuid) {
        VerificationHistory verification = verificationHistoryRepository.findFirstByProcessUuidOrderByDateTimeDesc(processUuid);
        return verification.getVerificationStatus();
    }
}
