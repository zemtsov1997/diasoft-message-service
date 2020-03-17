package ru.diasoft.message.api.dto.vk;


import ru.diasoft.message.api.model.vk.IVkTextMessage;

import java.util.Objects;

public class VkTextMessageDto implements IVkTextMessage {

    private Integer userId;
    private String message;

    public VkTextMessageDto() { }

    public VkTextMessageDto(Integer userId, String message) {
        this.userId = userId;
        this.message = message;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VkTextMessageDto that = (VkTextMessageDto) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, message);
    }
}
