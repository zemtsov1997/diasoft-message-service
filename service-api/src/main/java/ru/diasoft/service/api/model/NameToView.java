package ru.diasoft.service.api.model;

import org.springframework.context.i18n.LocaleContextHolder;
import ru.diasoft.service.api.utils.LocaleUtils;

import java.util.Locale;
import java.util.Map;

public interface NameToView {

    default String getNameToView() {
        String nameView = getNamesToView().get(LocaleContextHolder.getLocale());
        if (nameView == null) nameView = getNamesToView().get(LocaleUtils.getDefault());
        return nameView;
    }

    default String getNameToView(Locale locale) {
        String nameView = getNamesToView().get(locale);
        if (nameView == null) nameView = getNamesToView().get(LocaleUtils.getDefault());
        return nameView;
    }

    Map<Locale, String> getNamesToView();

}
