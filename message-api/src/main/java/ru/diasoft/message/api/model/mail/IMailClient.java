package ru.diasoft.message.api.model.mail;

public interface IMailClient {

    void sendSimpleMessage(ISimpleMail mail);

    void sendMimeMessage(IMimeMail mail);

}
