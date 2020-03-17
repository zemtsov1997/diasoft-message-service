package ru.diasoft.service.api;

public class UrlSeparator {

    private int index = 0;

    public UrlSeparator() {}

    public String get() {
        if (index == 0) {
            index++;
            return "?";
        } else {
            return "&";
        }
    }

}
