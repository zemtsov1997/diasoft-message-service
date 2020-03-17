package ru.diasoft.message.service.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import ru.diasoft.message.api.enums.HtmlTemplate;

import java.util.Map;

@Service
public class MailContentBuilder {

    private static final Logger logger = LogManager.getLogger(MailContentBuilder.class);

    @Autowired private TemplateEngine templateEngine;

    public String build(Map<String, Object> props, HtmlTemplate template) {
        Context context = new Context();
        for (Map.Entry<String, Object> entry : props.entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }
        String pathHtmlTemplate = null;
        if (template.equals(HtmlTemplate.REGISTRATION)) {
            pathHtmlTemplate = "mail/mail.registration";
        } else {
            logger.error("path for template is null...", new NullPointerException());
        }
        return templateEngine.process(pathHtmlTemplate, context);
    }

}
