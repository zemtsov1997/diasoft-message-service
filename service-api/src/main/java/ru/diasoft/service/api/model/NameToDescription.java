package ru.diasoft.service.api.model;

import org.springframework.context.i18n.LocaleContextHolder;
import ru.diasoft.service.api.utils.LocaleUtils;

import java.util.Locale;
import java.util.Map;

public interface NameToDescription {

    default String getNameToDescription() {
        String nameView = getNamesToDescription().get(LocaleContextHolder.getLocale());
        if (nameView == null) nameView = getNamesToDescription().get(LocaleUtils.getDefault());
        return nameView;
    }

    default String getNameToDescription(Locale locale) {
        String nameView = getNamesToDescription().get(locale);
        if (nameView == null) nameView = getNamesToDescription().get(LocaleUtils.getDefault());
        return nameView;
    }

    Map<Locale, String> getNamesToDescription();

}
