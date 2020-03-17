package ru.diasoft.message.api.dto.telegram;


import ru.diasoft.message.api.model.telegram.ITelegramTextMessage;

import java.util.Objects;

public class TelegramTextMessageDto implements ITelegramTextMessage {

    private String chatId;
    private String message;

    public TelegramTextMessageDto() { }

    public TelegramTextMessageDto(String chatId, String message) {
        this.chatId = chatId;
        this.message = message;
    }

    @Override
    public String getChatId() {
        return chatId;
    }
    public void setChatId(String chatId) {
        this.chatId = chatId;
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
        TelegramTextMessageDto that = (TelegramTextMessageDto) o;
        return Objects.equals(chatId, that.chatId) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, message);
    }
}
