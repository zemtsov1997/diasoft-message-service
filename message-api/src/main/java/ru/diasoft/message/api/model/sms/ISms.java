package ru.diasoft.message.api.model.sms;

import ru.diasoft.message.api.model.IMessage;

import java.time.LocalDateTime;
import java.util.List;

public interface ISms extends IMessage {

    Long getId();

    LocalDateTime getTime();

    List<String> getNumberPhoneRecipients();

    String getMessage();

}
