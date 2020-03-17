package ru.diasoft.message.service.entity;

import ru.diasoft.message.api.model.notification.IPushMessage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class NotificationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime = LocalDateTime.now();

    private String title;

    private String body;

    public NotificationHistory() { }

    public NotificationHistory(IPushMessage pushMessage) {
        this.dateTime = LocalDateTime.now();
        this.title = pushMessage.getTitle();
        this.body = pushMessage.getBody();
    }

    public NotificationHistory(LocalDateTime dateTime, String title, String body) {
        this.dateTime = dateTime;
        this.title = title;
        this.body = body;
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

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationHistory that = (NotificationHistory) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, body);
    }
}
