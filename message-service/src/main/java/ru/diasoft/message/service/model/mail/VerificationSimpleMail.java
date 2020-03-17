package ru.diasoft.message.service.model.mail;

import ru.diasoft.message.api.enums.VerificationStatus;
import ru.diasoft.message.api.model.IVerificationMessage;
import ru.diasoft.message.api.model.mail.ISimpleMail;

import java.util.Objects;
import java.util.UUID;

public class VerificationSimpleMail implements ISimpleMail, IVerificationMessage {

    private String from;
    private String to;
    private String subject;
    private String content;

    private UUID processUuid;

    private VerificationStatus verificationStatus;
    private Integer verificationCode;

    public VerificationSimpleMail() { }

    public VerificationSimpleMail(String from, String to, String subject, String content, VerificationStatus verificationStatus, Integer verificationCode) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.verificationStatus = verificationStatus;
        this.verificationCode = verificationCode;
    }

    public VerificationSimpleMail(ISimpleMail simpleMail) {
        this.from = simpleMail.getFrom();
        this.to = simpleMail.getTo();
        this.subject = simpleMail.getSubject();
        this.content = simpleMail.getContent();
    }

    @Override
    public UUID getProcessUuid() {
        return processUuid;
    }
    public void setProcessUuid(UUID processUuid) {
        this.processUuid = processUuid;
    }

    @Override
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
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
        VerificationSimpleMail that = (VerificationSimpleMail) o;
        return Objects.equals(from, that.from) &&
                Objects.equals(to, that.to) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(content, that.content) &&
                verificationStatus == that.verificationStatus &&
                Objects.equals(verificationCode, that.verificationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, subject, content, verificationStatus, verificationCode);
    }
}
