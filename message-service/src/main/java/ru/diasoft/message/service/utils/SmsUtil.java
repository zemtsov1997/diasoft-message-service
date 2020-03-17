package ru.diasoft.message.service.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.diasoft.message.api.model.sms.ISenderSms;
import ru.diasoft.message.api.model.sms.ISms;
import ru.diasoft.message.api.props.SmsUrlRequestProps;
import ru.diasoft.message.service.props.SmsCUrlRequestParams;
import ru.diasoft.service.api.UrlSeparator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SmsUtil {

    private static final Logger logger = LogManager.getLogger(SmsUtil.class);

    @Autowired private MessageSource messageSource;

    @Value("${sms.charset}")
    private String smsCharset;

    public String getTimeWithApiFormat(LocalDateTime localDateTime) {
        if (localDateTime == null) return "0";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyHHmm");
        return localDateTime.format(formatter);
    }

    public Map<String, Object> getDefaultSendUrlProps() {
        Map<String, Object> urlProps = new HashMap<>();
        urlProps.put(SmsUrlRequestProps.PROTOCOL, "https");
        urlProps.put(SmsUrlRequestProps.ADDRESS, "smsc.ru/sys/send.php");
        return urlProps;
    }

    public Map<String, Object> getDefaultBalanceUrlProps() {
        Map<String, Object> urlProps = new HashMap<>();
        urlProps.put(SmsUrlRequestProps.PROTOCOL, "https");
        urlProps.put(SmsUrlRequestProps.ADDRESS, "smsc.ru/sys/balance.php");
        return urlProps;
    }

    public Map<String, Object> getUrlParamsForPostRequest(ISenderSms senderSms, ISms sms) {
        Integer ususalStrResp = 0;
        Integer strParamsResp = 1;
        Integer xmlResp = 2;
        Integer jsonResp = 3;

        Map<String, Object> urlParams = new HashMap<>();
        urlParams.put(SmsCUrlRequestParams.LOGIN, senderSms.getLogin());
        urlParams.put(SmsCUrlRequestParams.PASSWORD, senderSms.getPassword());
        if (sms != null) {
            urlParams.put(SmsCUrlRequestParams.PHONES, sms.getNumberPhoneRecipients());
            urlParams.put(SmsCUrlRequestParams.MESSAGE, sms.getMessage());
        }
        urlParams.put(SmsCUrlRequestParams.TYPE_RESPONSE, jsonResp);
        urlParams.put(SmsCUrlRequestParams.TRANSLIT, 0);
        urlParams.put(SmsCUrlRequestParams.CHARSET, smsCharset);
        urlParams.put(SmsCUrlRequestParams.COST, 0);

        return urlParams;
    }

    public String getUrlParamsForGetRequest(ISenderSms senderSms, ISms sms) {
        UrlSeparator sp = new UrlSeparator();
        StringBuffer s = new StringBuffer();
        Map<String, Object> urlParams = getUrlParamsForPostRequest(senderSms, sms);
        urlParams.entrySet().stream().forEach( entery -> {
            if (validParam(entery.getValue().toString())) {
                s.append(sp.get() + entery.getKey() + "=" + entery.getValue());
            }
        });
        return s.toString();
    }

    private String convertNumbersPhoneToString(List<String> numbers) {
        String numbersPhone = "";
        if (numbers.size() > 0) {
            for (int i = 0; i < numbers.size(); i++) {
                numbersPhone += numbers.get(i);
                if (i < (numbers.size() - 1)) numbersPhone += ",";
            }
        }
        return numbersPhone;
    }

    private Boolean validParam(String param) {
        return param != null && !param.isEmpty();
    }

}
