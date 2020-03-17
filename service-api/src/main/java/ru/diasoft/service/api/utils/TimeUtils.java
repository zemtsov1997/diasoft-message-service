package ru.diasoft.service.api.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

public class TimeUtils {

    public static Integer calAge(LocalDate birthDate, LocalDate currentDate) {
        if (birthDate == null) return null;
        if (currentDate == null) currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    public static Integer calAge(java.util.Date birthDate, java.util.Date currentDate) {
        if (currentDate == null) currentDate = new java.util.Date();
        return calAge(TimeUtils.convertToLocalDate(birthDate), TimeUtils.convertToLocalDate(currentDate));
    }

    public static LocalDate convertToLocalDate(java.util.Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
