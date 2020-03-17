package ru.diasoft.message.service.entity;

import ru.diasoft.message.api.enums.HtmlTemplate;
import ru.diasoft.message.api.model.mail.IMimeMail;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class MimeMailHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime = LocalDateTime.now();

    private String recipient;

    private HtmlTemplate htmlTemplate;

    public MimeMailHistory() { }

    public MimeMailHistory(IMimeMail mimeMail) {
        this.dateTime = LocalDateTime.now();
        this.recipient = mimeMail.getTo();
        this.htmlTemplate = mimeMail.getHtmlTemplate();
    }

    public MimeMailHistory(LocalDateTime dateTime, String recipient, HtmlTemplate htmlTemplate) {
        this.dateTime = dateTime;
        this.recipient = recipient;
        this.htmlTemplate = htmlTemplate;
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
        MimeMailHistory that = (MimeMailHistory) o;
        return Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(recipient, that.recipient) &&
                htmlTemplate == that.htmlTemplate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, recipient, htmlTemplate);
    }
}
