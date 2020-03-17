package ru.diasoft.message.api.dto.sms;

import ru.diasoft.message.api.model.sms.ISms;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class SmsDto implements ISms {

    private Long id;

    private LocalDateTime time;

    private List<String> numberPhoneRecipients;

    private String message;

    public SmsDto() { }

    @Override
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public List<String> getNumberPhoneRecipients() {
        return numberPhoneRecipients;
    }
    public void setNumberPhoneRecipients(List<String> numberPhoneRecipients) {
        this.numberPhoneRecipients = numberPhoneRecipients;
    }

    @Override
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "id=" + id +
                ", time=" + time +
                ", numberPhoneRecipients=" + numberPhoneRecipients +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmsDto smsDto = (SmsDto) o;
        return Objects.equals(time, smsDto.time) &&
                Objects.equals(numberPhoneRecipients, smsDto.numberPhoneRecipients) &&
                Objects.equals(message, smsDto.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, numberPhoneRecipients, message);
    }
}
