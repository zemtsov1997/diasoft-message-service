package ru.diasoft.service.api.enums;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public enum FinancialType {
    COMMERCIAL, NOT_COMMERCIAL;

    public static FinancialType getEnum(String value) {
        AtomicReference<FinancialType> returnedObject = new AtomicReference<>();
        returnedObject.set(null);
        if (value == null) return null;
        Arrays.stream(FinancialType.values()).forEach( type -> {
            if (type.name().equals(value.toLowerCase()) || type.name().equals(value.toUpperCase())) returnedObject.set(type);
        });
        return returnedObject.get();
    }

}
