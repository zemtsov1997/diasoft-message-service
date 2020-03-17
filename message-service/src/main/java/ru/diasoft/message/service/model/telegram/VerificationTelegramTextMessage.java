package ru.diasoft.message.service.model.telegram;

import ru.diasoft.message.api.enums.VerificationStatus;
import ru.diasoft.message.api.model.IVerificationMessage;
import ru.diasoft.message.api.model.telegram.ITelegramTextMessage;

import java.util.Objects;
import java.util.UUID;

public class VerificationTelegramTextMessage implements ITelegramTextMessage, IVerificationMessage {

    private String chatId;
    private String message;

    private UUID processUuid;

    private VerificationStatus verificationStatus;
    private Integer verificationCode;

    public VerificationTelegramTextMessage() { }

    public VerificationTelegramTextMessage(String chatId, String message) {
        this.chatId = chatId;
        this.message = message;
    }

    public VerificationTelegramTextMessage(ITelegramTextMessage message) {
        this.chatId = message.getChatId();
        this.message = message.getMessage();
    }

    @Override
    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public UUID getProcessUuid() {
        return processUuid;
    }

    public void setProcessUuid(UUID processUuid) {
        this.processUuid = processUuid;
    }

    @Override
    public VerificationStatus getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(VerificationStatus verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    @Override
    public Integer getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(Integer verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerificationTelegramTextMessage that = (VerificationTelegramTextMessage) o;
        return Objects.equals(chatId, that.chatId) &&
                Objects.equals(message, that.message) &&
                Objects.equals(processUuid, that.processUuid) &&
                verificationStatus == that.verificationStatus &&
                Objects.equals(verificationCode, that.verificationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, message, processUuid, verificationStatus, verificationCode);
    }
}
