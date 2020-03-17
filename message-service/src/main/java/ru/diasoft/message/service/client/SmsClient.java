package ru.diasoft.message.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.diasoft.message.api.model.TimeClient;
import ru.diasoft.message.api.model.sms.ISms;
import ru.diasoft.message.api.props.SmsUrlRequestProps;
import ru.diasoft.message.service.props.SmsCUrlRequestParams;
import ru.diasoft.message.service.utils.SenderSmsBuilder;
import ru.diasoft.message.service.utils.SmsCResponseDecoder;
import ru.diasoft.message.service.utils.SmsUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class SmsClient implements TimeClient<ISms> {

    private static final Logger logger = LogManager.getLogger(SmsClient.class);

    @Value("${sms.charset}")
    private String smsCharset;

    @Autowired private SmsUtil smsUtil;
    @Autowired private SenderSmsBuilder senderSmsBuilder;
    @Autowired private SmsCResponseDecoder smsCResponseDecoder;

    @Override
    public void sendMessage(ISms message) {
        sendSms(message);
    }

    public void sendSms(ISms sms) {
        Map<String, Object> defaultUrlProps = smsUtil.getDefaultSendUrlProps();
        Map<String, Object> urlParams = smsUtil.getUrlParamsForPostRequest(senderSmsBuilder.getDefault(), sms);
        smsCResponseDecoder.getDefualtSendSmsResponseEncoder(sendPostRequest(defaultUrlProps, urlParams));
    }

    public BigDecimal getCostSms(ISms sms) {
        Map<String, Object> defaultUrlProps = smsUtil.getDefaultSendUrlProps();
        Map<String, Object> urlParams = smsUtil.getUrlParamsForPostRequest(senderSmsBuilder.getDefault(), sms);
        urlParams.replace(SmsCUrlRequestParams.COST, 1);
        return smsCResponseDecoder.getDefualtCostSmsResponseEncoder(sendPostRequest(defaultUrlProps, urlParams));
    }

    public BigDecimal getBalance() {
        Map<String, Object> defaultUrlProps = smsUtil.getDefaultBalanceUrlProps();
        Map<String, Object> urlParams = smsUtil.getUrlParamsForPostRequest(senderSmsBuilder.getDefault(), null);
        urlParams.put(SmsCUrlRequestParams.CURRENCY, 1);
        return smsCResponseDecoder.getDefualtBalanceResponseEncoder(sendPostRequest(defaultUrlProps, urlParams));
    }

    private Map<String, Object> sendPostRequest(Map<String, Object> defaultUrlProps, Map<String, Object> urlParams) {
        String url = defaultUrlProps.get(SmsUrlRequestProps.PROTOCOL) + "://" + defaultUrlProps.get(SmsUrlRequestProps.ADDRESS);

        HttpPost post = new HttpPost(url);

        List<NameValuePair> urlParameters = new ArrayList<>();

        urlParams.entrySet().stream().forEach( entery -> {
            urlParameters.add(new BasicNameValuePair(entery.getKey(), entery.getValue().toString()));
        });

        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters, smsCharset));
            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                 CloseableHttpResponse response = httpClient.execute(post)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String retSrc = EntityUtils.toString(entity);
                    ObjectMapper objectMapper = new ObjectMapper();
                    Map<String, Object> map = objectMapper.readValue(retSrc, Map.class);
                    return map;
                } else {
                    logger.error("HttpEntity is null...", new NullPointerException());
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

}
