package ru.diasoft.message.api.dto.mail;

import ru.diasoft.message.api.model.mail.ISimpleMail;

import java.util.Objects;

public class SimpleMailDto implements ISimpleMail {

    private String from;
    private String to;
    private String subject;
    private String content;

    public SimpleMailDto() { }

    public SimpleMailDto(String from, String to, String subject, String content) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    @Override
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleMailDto that = (SimpleMailDto) o;
        return Objects.equals(from, that.from) &&
                Objects.equals(to, that.to) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, subject, content);
    }
}
