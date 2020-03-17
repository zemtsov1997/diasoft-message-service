package ru.diasoft.message.service.model.vk;

import ru.diasoft.message.api.enums.VerificationStatus;
import ru.diasoft.message.api.model.IVerificationMessage;
import ru.diasoft.message.api.model.vk.IVkTextMessage;

import java.util.Objects;
import java.util.UUID;

public class VerificationVkTextMessage implements IVkTextMessage, IVerificationMessage {

    private Integer userId;
    private String message;

    private UUID processUuid;

    private VerificationStatus verificationStatus;
    private Integer verificationCode;

    public VerificationVkTextMessage() { }

    public VerificationVkTextMessage(Integer userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public VerificationVkTextMessage(IVkTextMessage message) {
        this.userId = message.getUserId();
        this.message = message.getMessage();
    }

    @Override
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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
        VerificationVkTextMessage that = (VerificationVkTextMessage) o;
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
