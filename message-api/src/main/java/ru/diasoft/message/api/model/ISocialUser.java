package ru.diasoft.message.api.model;

public interface ISocialUser {

    Long getId();

    String getEmail();

    String getNumberPhone();

    String getNotificationToken();

    String getTelegramChatId();

    Integer getVkUserId();

    String getDiscordUserId();

}
