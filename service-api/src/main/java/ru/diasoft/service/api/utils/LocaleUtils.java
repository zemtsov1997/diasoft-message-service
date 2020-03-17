package ru.diasoft.service.api.utils;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleUtils {

    public static final Locale LOCALE_RU = new Locale("ru", "RU");
    public static final Locale LOCALE_EN = Locale.ENGLISH;

    public static Locale getDefault(){
        return LocaleUtils.LOCALE_RU;
    }

    public static Locale[] getAll() {
        return new Locale[]{ LocaleUtils.LOCALE_RU, LocaleUtils.LOCALE_EN };
    }

}
