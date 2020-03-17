package ru.diasoft.service.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.diasoft.service.api.exeption.ErrorModel;
import ru.diasoft.service.api.exeption.FeignBadRequestException;
import ru.diasoft.service.api.exeption.FeignErrorDecoder;

import java.util.ArrayList;
import java.util.Map;

public interface SenderObject<T> {

    Logger logger = LogManager.getLogger(SenderObject.class);

    default T send(T objectSend,
           DataFunction<T> dataFunction,
           String nameParamObject,
           MessageSource messageSource,
           BindingResult bindingResult
    ) {
        try {
            return dataFunction.sendObject(objectSend);
        } catch (HystrixBadRequestException ex) {
            if (ex instanceof FeignBadRequestException) {
                try {
                    String body = ((FeignBadRequestException) ex).getBody();
                    if (body.equals(FeignErrorDecoder.DEFAULT_NAME_400_STATUS)) {
                        throw new Exception("Body not found...");
                    }

                    ErrorModel errorModel = new ObjectMapper().readValue(body, ErrorModel.class);
                    Map<String, ArrayList<Object>> map = new ObjectMapper().readValue(errorModel.getDetails(), Map.class);
                    map.entrySet().stream().forEach( entery -> {
                        String field = entery.getKey();
                        ArrayList<Object> arrayDefaultMessage = entery.getValue();
                        arrayDefaultMessage.forEach(defaultMessage -> {
                            String message = messageSource.getMessage(defaultMessage.toString(), null, LocaleContextHolder.getLocale());
                            bindingResult.addError(new FieldError(nameParamObject, field, message));
                        });
                    });

                } catch (Exception ex1) {
                    logger.error(ex1);
                }
            } else {
                logger.error(ex);
            }
            return objectSend;
        }
    }

}
