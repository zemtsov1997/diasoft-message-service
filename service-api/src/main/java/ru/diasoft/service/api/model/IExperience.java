package ru.diasoft.service.api.model;

import java.time.LocalDate;

public interface IExperience {

    default java.time.Period getPeriod() {
        LocalDate dateEnd = getDateEnd();
        LocalDate dateStart = getDateStart();
        if (dateStart != null) {
            if (dateEnd == null) dateEnd = LocalDate.now();
            java.time.Period period = java.time.Period.between(dateStart, dateEnd);
            if (period.getDays() > 0 || period.getMonths() > 0 || period.getYears() > 0) {
                return period;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    Long getId();
    LocalDate getDateStart();
    LocalDate getDateEnd();

}
