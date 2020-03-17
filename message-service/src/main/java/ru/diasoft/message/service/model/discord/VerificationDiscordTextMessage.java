package ru.diasoft.message.service.model.discord;

import ru.diasoft.message.api.enums.VerificationStatus;
import ru.diasoft.message.api.model.IVerificationMessage;
import ru.diasoft.message.api.model.discord.IDiscrodTextMessage;

import java.util.Objects;
import java.util.UUID;

public class VerificationDiscordTextMessage implements IDiscrodTextMessage, IVerificationMessage {

    private String userId;
    private String message;

    private UUID processUuid;

    private VerificationStatus verificationStatus;
    private Integer verificationCode;

    public VerificationDiscordTextMessage() { }

    public VerificationDiscordTextMessage(String userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public VerificationDiscordTextMessage(IDiscrodTextMessage message) {
        this.userId = message.getUserId();
        this.message = message.getMessage();
    }

    @Override
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        VerificationDiscordTextMessage that = (VerificationDiscordTextMessage) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(message, that.message) &&
                Objects.equals(processUuid, that.processUuid) &&
                verificationStatus == that.verificationStatus &&
                Objects.equals(verificationCode, that.verificationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, message, processUuid, verificationStatus, verificationCode);
    }
}
