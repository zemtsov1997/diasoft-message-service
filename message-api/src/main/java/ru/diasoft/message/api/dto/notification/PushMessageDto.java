package ru.diasoft.message.api.dto.notification;

import ru.diasoft.message.api.model.notification.IPushMessage;

import java.util.Objects;

public class PushMessageDto implements IPushMessage {

    private Long id;
    private String clientToken;
    private String title;
    private String body;
    private String urlIcon;
    private String urlImage;
    private String urlAction;
    private String ttlInSeconds;

    public PushMessageDto() { }

    public PushMessageDto(String clientToken, String title, String body, String urlIcon, String urlImage, String urlAction, String ttlInSeconds) {
        this.clientToken = clientToken;
        this.title = title;
        this.body = body;
        this.urlIcon = urlIcon;
        this.urlImage = urlImage;
        this.urlAction = urlAction;
        this.ttlInSeconds = ttlInSeconds;
    }

    @Override
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    @Override
    public String toString() {
        return "PushMessage{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", urlIcon='" + urlIcon + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", urlAction='" + urlAction + '\'' +
                ", ttlInSeconds='" + ttlInSeconds + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PushMessageDto that = (PushMessageDto) o;
        return Objects.equals(clientToken, that.clientToken) &&
                Objects.equals(title, that.title) &&
                Objects.equals(body, that.body) &&
                Objects.equals(urlIcon, that.urlIcon) &&
                Objects.equals(urlImage, that.urlImage) &&
                Objects.equals(urlAction, that.urlAction) &&
                Objects.equals(ttlInSeconds, that.ttlInSeconds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientToken, title, body, urlIcon, urlImage, urlAction, ttlInSeconds);
    }
}
