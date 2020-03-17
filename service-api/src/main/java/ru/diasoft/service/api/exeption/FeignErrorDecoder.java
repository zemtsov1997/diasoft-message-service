package ru.diasoft.service.api.exeption;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;

import java.io.IOException;

public class FeignErrorDecoder implements ErrorDecoder {

    private static final Logger logger = LogManager.getLogger(FeignErrorDecoder.class);

    public static final String DEFAULT_NAME_400_STATUS = "Bad request";

    @Override
    public Exception decode(String s, Response response) {
        Integer status = response.status();
        if (status == 400) {
            String body = DEFAULT_NAME_400_STATUS;
            try {
                body = IOUtils.toString(response.body().asReader());

                HttpHeaders httpHeaders = new HttpHeaders();
                response.headers().forEach((k, v) -> httpHeaders.add("feign-" + k, StringUtils.join(v,",")));

                return new FeignBadRequestException(status, httpHeaders, body);
            } catch (IOException e) {
                logger.error(e);
            }
        }
        return new RuntimeException("Response Code "+status);
    }

}
