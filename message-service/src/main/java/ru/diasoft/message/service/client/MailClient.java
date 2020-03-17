package ru.diasoft.message.service.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import ru.diasoft.message.api.model.mail.IMailClient;
import ru.diasoft.message.api.model.mail.IMimeMail;
import ru.diasoft.message.api.model.mail.ISimpleMail;
import ru.diasoft.message.service.utils.MailContentBuilder;

@Component
public class MailClient implements IMailClient {

    private static final Logger logger = LogManager.getLogger(MailClient.class);

    @Autowired private JavaMailSender emailSender;
    @Autowired private MailContentBuilder mailContentBuilder;

    @Override
    public void sendSimpleMessage(ISimpleMail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        message.setFrom(mail.getFrom());
        message.setTo(mail.getTo());
        try {
            emailSender.send(message);
        } catch (MailException ex) {
            logger.error("Error for sent email", ex);
        }
    }

    @Override
    public void sendMimeMessage(IMimeMail mail) {

        MimeMessagePreparator messagePreparator = (mimeMessage) -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setSubject(mail.getSubject());
            mimeMessageHelper.setFrom(mail.getFrom());
            mimeMessageHelper.setTo(mail.getTo());

            String content = mailContentBuilder.build(mail.getProps(), mail.getHtmlTemplate());
            mimeMessageHelper.setText(content, true);
        };
        try {
            emailSender.send(messagePreparator);
        } catch (MailException ex) {
            logger.error("Error for sent email", ex);
        }

    }

}
