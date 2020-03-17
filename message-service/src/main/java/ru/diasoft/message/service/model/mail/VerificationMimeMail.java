package ru.diasoft.message.service.model.mail;

import ru.diasoft.message.api.enums.HtmlTemplate;
import ru.diasoft.message.api.enums.VerificationStatus;
import ru.diasoft.message.api.model.IVerificationMessage;
import ru.diasoft.message.api.model.mail.IMimeMail;
import ru.diasoft.message.api.model.mail.ISimpleMail;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class VerificationMimeMail implements IMimeMail, IVerificationMessage {

    private String from;
    private String to;
    private String subject;
    private Map<String, Object> props;
    private HtmlTemplate htmlTemplate;

    private UUID processUuid;

    private VerificationStatus verificationStatus;
    private Integer verificationCode;

    public VerificationMimeMail() { }

    public VerificationMimeMail(String from, String to, String subject, Map<String, Object> props, HtmlTemplate htmlTemplate) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.props = props;
        this.htmlTemplate = htmlTemplate;
    }

    public VerificationMimeMail(IMimeMail mimeMail) {
        this.from = mimeMail.getFrom();
        this.to = mimeMail.getTo();
        this.subject = mimeMail.getSubject();
        this.props = mimeMail.getProps();
        this.htmlTemplate = mimeMail.getHtmlTemplate();
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
    public Map<String, Object> getProps() {
        return props;
    }

    public void setProps(Map<String, Object> props) {
        this.props = props;
    }

    @Override
    public HtmlTemplate getHtmlTemplate() {
        return htmlTemplate;
    }

    public void setHtmlTemplate(HtmlTemplate htmlTemplate) {
        this.htmlTemplate = htmlTemplate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerificationMimeMail that = (VerificationMimeMail) o;
        return Objects.equals(from, that.from) &&
                Objects.equals(to, that.to) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(props, that.props) &&
                htmlTemplate == that.htmlTemplate &&
                Objects.equals(processUuid, that.processUuid) &&
                verificationStatus == that.verificationStatus &&
                Objects.equals(verificationCode, that.verificationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, subject, props, htmlTemplate, processUuid, verificationStatus, verificationCode);
    }
}
