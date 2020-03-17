package ru.diasoft.message.api.model.mail;

import ru.diasoft.message.api.enums.HtmlTemplate;

import java.util.Map;

public interface IMimeMail extends IMail {

    Map<String, Object> getProps();

    HtmlTemplate getHtmlTemplate();

}
