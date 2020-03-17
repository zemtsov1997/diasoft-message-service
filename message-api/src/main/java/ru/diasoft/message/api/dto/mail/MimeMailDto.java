package ru.diasoft.message.api.dto.mail;

import ru.diasoft.message.api.enums.HtmlTemplate;
import ru.diasoft.message.api.model.mail.IMimeMail;

import java.util.Map;
import java.util.Objects;

public class MimeMailDto implements IMimeMail {

    private String from;
    private String to;
    private String subject;
    private Map<String, Object> props;
    private HtmlTemplate htmlTemplate;

    public MimeMailDto() { }

    public MimeMailDto(String from, String to, String subject, Map<String, Object> props, HtmlTemplate htmlTemplate) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.props = props;
        this.htmlTemplate = htmlTemplate;
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
    public Map<String, Object> getProps() {
        return props;
    }
    public void setProps(Map<String, Object> props) {
        this.props = props;
    }

    @Override
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
        MimeMailDto mimeMailDto = (MimeMailDto) o;
        return Objects.equals(from, mimeMailDto.from) &&
                Objects.equals(to, mimeMailDto.to) &&
                Objects.equals(subject, mimeMailDto.subject) &&
                Objects.equals(props, mimeMailDto.props) &&
                htmlTemplate == mimeMailDto.htmlTemplate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, subject, props, htmlTemplate);
    }
}
