package ru.diasoft.message.service.model.notification;

import ru.diasoft.message.api.enums.VerificationStatus;
import ru.diasoft.message.api.model.IVerificationMessage;
import ru.diasoft.message.api.model.notification.IPushMessage;

import java.util.Objects;
import java.util.UUID;

public class VerificationPushMessage implements IPushMessage, IVerificationMessage {

    private Long id;
    private String clientToken;
    private String title;
    private String body;
    private String urlIcon;
    private String urlImage;
    private String urlAction;
    private String ttlInSeconds;

    private UUID processUuid;

    private VerificationStatus verificationStatus;
    private Integer verificationCode;

    public VerificationPushMessage() { }

    public VerificationPushMessage(Long id, String clientToken, String title, String body, String urlIcon, String urlImage, String urlAction, String ttlInSeconds) {
        this.id = id;
        this.clientToken = clientToken;
        this.title = title;
        this.body = body;
        this.urlIcon = urlIcon;
        this.urlImage = urlImage;
        this.urlAction = urlAction;
        this.ttlInSeconds = ttlInSeconds;
    }

    public VerificationPushMessage(IPushMessage message) {
        this.id = message.getId();
        this.clientToken = message.getClientToken();
        this.title = message.getTitle();
        this.body = message.getBody();
        this.urlIcon = message.getUrlIcon();
        this.urlImage = message.getUrlImage();
        this.urlAction = message.getUrlAction();
        this.ttlInSeconds = message.getTtlInSeconds();
    }

    @Override
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getClientToken() {
        return clientToken;
    }
    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    @Override
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String getUrlIcon() {
        return urlIcon;
    }
    public void setUrlIcon(String urlIcon) {
        this.urlIcon = urlIcon;
    }

    @Override
    public String getUrlImage() {
        return urlImage;
    }
    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public String getUrlAction() {
        return urlAction;
    }
    public void setUrlAction(String urlAction) {
        this.urlAction = urlAction;
    }

    @Override
    public String getTtlInSeconds() {
        return ttlInSeconds;
    }
    public void setTtlInSeconds(String ttlInSeconds) {
        this.ttlInSeconds = ttlInSeconds;
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
        VerificationPushMessage that = (VerificationPushMessage) o;
        return Objects.equals(clientToken, that.clientToken) &&
                Objects.equals(title, that.title) &&
                Objects.equals(body, that.body) &&
                Objects.equals(urlIcon, that.urlIcon) &&
                Objects.equals(urlImage, that.urlImage) &&
                Objects.equals(urlAction, that.urlAction) &&
                Objects.equals(ttlInSeconds, that.ttlInSeconds) &&
                Objects.equals(processUuid, that.processUuid) &&
                verificationStatus == that.verificationStatus &&
                Objects.equals(verificationCode, that.verificationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientToken, title, body, urlIcon, urlImage, urlAction, ttlInSeconds, processUuid, verificationStatus, verificationCode);
    }
}
