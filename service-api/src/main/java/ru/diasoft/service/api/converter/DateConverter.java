package ru.diasoft.service.api.converter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {

    private final static Logger logger = LogManager.getLogger(DateConverter.class);

    @Override
    public Date convert(String value) {
        if (!value.isEmpty()) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd").parse(value);
            } catch (ParseException e) {
                logger.error("Error parse java.util.Date.", e);
                return null;
            }
        } else {
            return null;
        }
    }

}
