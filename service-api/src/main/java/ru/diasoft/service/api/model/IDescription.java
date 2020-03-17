package ru.diasoft.service.api.model;

import ru.diasoft.service.api.utils.DescriptionUtils;

public interface IDescription {

    default String validSettedText(String value) {
        Boolean bool = DescriptionUtils.isValidDescText(value);
        if (bool) {
            return value;
        } else {
            return null;
        }
    }

    Long getId();

}
