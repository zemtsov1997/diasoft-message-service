package ru.diasoft.message.api.dto;

import ru.diasoft.message.api.model.ISocialUser;

import java.util.Objects;

public class SocialUserDto implements ISocialUser {

    private Long id;

    private String email;

    private String numberPhone;

    private String notificationToken;

    private String telegramChatId;

    private Integer vkUserId;

    private String discordUserId;

    public SocialUserDto() { }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    @Override
    public String getNotificationToken() {
        return notificationToken;
    }

    public void setNotificationToken(String notificationToken) {
        this.notificationToken = notificationToken;
    }

    @Override
    public String getTelegramChatId() {
        return telegramChatId;
    }

    public void setTelegramChatId(String telegramChatId) {
        this.telegramChatId = telegramChatId;
    }

    @Override
    public Integer getVkUserId() {
        return vkUserId;
    }

    public void setVkUserId(Integer vkUserId) {
        this.vkUserId = vkUserId;
    }

    @Override
    public String getDiscordUserId() {
        return discordUserId;
    }

    public void setDiscordUserId(String discordUserId) {
        this.discordUserId = discordUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocialUserDto that = (SocialUserDto) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(numberPhone, that.numberPhone) &&
                Objects.equals(notificationToken, that.notificationToken) &&
                Objects.equals(telegramChatId, that.telegramChatId) &&
                Objects.equals(vkUserId, that.vkUserId) &&
                Objects.equals(discordUserId, that.discordUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, numberPhone, notificationToken, telegramChatId, vkUserId, discordUserId);
    }
}
