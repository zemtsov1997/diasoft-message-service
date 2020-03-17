package ru.diasoft.message.service.entity;

import ru.diasoft.message.api.model.telegram.ITelegramTextMessage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class TelegramHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime = LocalDateTime.now();

    private String recipient;

    private String message;

    public TelegramHistory() { }

    public TelegramHistory(ITelegramTextMessage telegramTextMessage) {
        this.dateTime = LocalDateTime.now();
        this.recipient = telegramTextMessage.getChatId();
        this.message = telegramTextMessage.getMessage();
    }

    public TelegramHistory(LocalDateTime dateTime, String recipient, String message) {
        this.dateTime = dateTime;
        this.recipient = recipient;
        this.message = message;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getRecipient() {
        return recipient;
    }
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

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
        TelegramHistory that = (TelegramHistory) o;
        return Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(recipient, that.recipient) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, recipient, message);
    }
}
