package ru.diasoft.service.api.dto;

import ru.diasoft.service.api.model.IWage;

import java.util.Objects;

public class WageDto implements IWage {

    private Long id;
    private Integer count;
    private Long currencyId;
    private Long periodId;

    public WageDto() { }

    public WageDto(Integer count, Long currencyId, Long periodId) {
        this.count = count;
        this.currencyId = currencyId;
        this.periodId = periodId;
    }

    @Override
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public Long getCurrencyId() {
        return currencyId;
    }
    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public Long getPeriodId() {
        return periodId;
    }
    public void setPeriodId(Long periodId) {
        this.periodId = periodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WageDto wageDto = (WageDto) o;
        return Objects.equals(count, wageDto.count) &&
                Objects.equals(currencyId, wageDto.currencyId) &&
                Objects.equals(periodId, wageDto.periodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, currencyId, periodId);
    }
}
