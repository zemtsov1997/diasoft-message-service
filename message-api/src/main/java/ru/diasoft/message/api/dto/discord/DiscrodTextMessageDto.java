package ru.diasoft.message.api.dto.discord;


import ru.diasoft.message.api.model.discord.IDiscrodTextMessage;

import java.util.Objects;

public class DiscrodTextMessageDto implements IDiscrodTextMessage {

    private String userId;
    private String message;

    public DiscrodTextMessageDto() { }

    public DiscrodTextMessageDto(String userId, String message) {
        this.userId = userId;
        this.message = message;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscrodTextMessageDto that = (DiscrodTextMessageDto) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, message);
    }
}
