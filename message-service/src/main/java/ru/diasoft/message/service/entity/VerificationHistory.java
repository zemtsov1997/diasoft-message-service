package ru.diasoft.message.service.entity;

import ru.diasoft.message.api.enums.VerificationStatus;
import ru.diasoft.message.api.model.IVerificationMessage;
import ru.diasoft.service.api.converter.UuidAttributeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class VerificationHistory implements IVerificationMessage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime = LocalDateTime.now();

    @Convert(converter = UuidAttributeConverter.class)
    private UUID processUuid = UUID.randomUUID();

    private Long socialUserId;

    private Integer verificationCode;

    private VerificationStatus verificationStatus;

    public VerificationHistory() {}

    public VerificationHistory(IVerificationMessage message) {
        this.verificationCode = message.getVerificationCode();
        this.verificationStatus = message.getVerificationStatus();
        this.processUuid = message.getProcessUuid();
    }

    public Long getSocialUserId() {
        return socialUserId;
    }
    public void setSocialUserId(Long socialUserId) {
        this.socialUserId = socialUserId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public UUID getProcessUuid() {
        return processUuid;
    }
    public void setProcessUuid(UUID processUuid) {
        this.processUuid = processUuid;
    }

    @Override
    public Integer getVerificationCode() {
        return verificationCode;
    }
    public void setVerificationCode(Integer verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public VerificationStatus getVerificationStatus() {
        return verificationStatus;
    }
    public void setVerificationStatus(VerificationStatus verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerificationHistory that = (VerificationHistory) o;
        return Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(processUuid, that.processUuid) &&
                Objects.equals(verificationCode, that.verificationCode) &&
                verificationStatus == that.verificationStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, processUuid, verificationCode, verificationStatus);
    }
}
