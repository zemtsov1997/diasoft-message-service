package ru.diasoft.service.api.dto;

import ru.diasoft.service.api.model.INumberPhone;

import java.util.Objects;

public class NumberPhoneDto implements INumberPhone {

    private Long id;
    private String code;
    private String number;

    public NumberPhoneDto() {}

    public NumberPhoneDto(Long id, String code, String number) {
        this.id = id;
        this.code = code;
        this.number = number;
    }

    public NumberPhoneDto(INumberPhone numberPhone) {
        this.id = numberPhone.getId();
        this.code = numberPhone.getCode();
        this.number = numberPhone.getNumber();
    }

    @Override
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    @Override
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return code+number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberPhoneDto that = (NumberPhoneDto) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, number);
    }

}
