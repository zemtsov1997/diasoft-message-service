package ru.diasoft.message.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.diasoft.service.api.converter.DateConverter;
import ru.diasoft.service.api.converter.LocalDateConverter;
import ru.diasoft.service.api.converter.LocalDateTimeConverter;

@Configuration
public class ConverterConfig {

    @Bean
    public DateConverter dateConverter() {
        return new DateConverter();
    }

    @Bean
    public LocalDateConverter localDateConverter() {
        return new LocalDateConverter();
    }

    @Bean
    public LocalDateTimeConverter localDateTimeConverter() {
        return new LocalDateTimeConverter();
    }

}
