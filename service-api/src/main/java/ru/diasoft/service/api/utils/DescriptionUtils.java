package ru.diasoft.service.api.utils;

import java.util.regex.Pattern;

public class DescriptionUtils {

    public static Boolean isValidDescText(String text) {
        Pattern pattern = Pattern.compile("^(.*)(<style[^>]*>)(.*)(</style[^>]*>)(.*)$", Pattern.DOTALL);
        return text != null && !text.isEmpty() && !pattern.matcher(text).matches();
    }

}
