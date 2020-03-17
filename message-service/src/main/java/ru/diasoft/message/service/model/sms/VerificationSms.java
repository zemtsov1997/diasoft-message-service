package ru.diasoft.message.service.model.sms;

import ru.diasoft.message.api.enums.VerificationStatus;
import ru.diasoft.message.api.model.IVerificationMessage;
import ru.diasoft.message.api.model.sms.ISms;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class VerificationSms implements ISms, IVerificationMessage {

    private Long id;
    private LocalDateTime time;
    private List<String> numberPhoneRecipients;
    private String message;

    private UUID processUuid;

    private VerificationStatus verificationStatus;
    private Integer verificationCode;

    public VerificationSms() { }

    public VerificationSms(ISms sms) {
        this.id = sms.getId();
        this.time = sms.getTime();
        this.numberPhoneRecipients = sms.getNumberPhoneRecipients();
        this.message = sms.getMessage();
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
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public List<String> getNumberPhoneRecipients() {
        return numberPhoneRecipients;
    }
    public void setNumberPhoneRecipients(List<String> numberPhoneRecipients) {
        this.numberPhoneRecipients = numberPhoneRecipients;
    }

    @Override
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "id=" + id +
                ", time=" + time +
                ", numberPhoneRecipients=" + numberPhoneRecipients +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerificationSms that = (VerificationSms) o;
        return Objects.equals(time, that.time) &&
                Objects.equals(numberPhoneRecipients, that.numberPhoneRecipients) &&
                Objects.equals(message, that.message) &&
                Objects.equals(processUuid, that.processUuid) &&
                verificationStatus == that.verificationStatus &&
                Objects.equals(verificationCode, that.verificationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, numberPhoneRecipients, message, processUuid, verificationStatus, verificationCode);
    }
}
