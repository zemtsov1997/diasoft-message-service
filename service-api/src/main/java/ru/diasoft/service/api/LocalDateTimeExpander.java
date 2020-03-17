package ru.diasoft.service.api;

import feign.Param;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeExpander implements Param.Expander {

    @Override
    public String expand(Object o) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").format((LocalDateTime) o);
    }
}
