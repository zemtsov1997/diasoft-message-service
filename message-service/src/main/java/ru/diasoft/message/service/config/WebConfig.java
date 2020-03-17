package ru.diasoft.message.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.diasoft.service.api.converter.DateConverter;
import ru.diasoft.service.api.converter.LocalDateConverter;
import ru.diasoft.service.api.converter.LocalDateTimeConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired private DateConverter converterDate;
    @Autowired private LocalDateConverter converterLocalDate;
    @Autowired private LocalDateTimeConverter localDateTimeConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(converterDate);
        registry.addConverter(converterLocalDate);
        registry.addConverter(localDateTimeConverter);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
