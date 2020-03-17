package ru.diasoft.service.api.dto;

import ru.diasoft.service.api.model.IAddress;

import java.util.Objects;

public class AddressDto implements IAddress {

    private Long id;
    private Long countryId;
    private Long regionId;
    private Long cityId;
    private String street;
    private String house;

    public AddressDto() { }

    @Override
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getCountryId() {
        return countryId;
    }
    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Override
    public Long getRegionId() {
        return regionId;
    }
    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    @Override
    public Long getCityId() {
        return cityId;
    }
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    @Override
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String getHouse() {
        return house;
    }
    public void setHouse(String house) {
        this.house = house;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDto that = (AddressDto) o;
        return Objects.equals(countryId, that.countryId) &&
                Objects.equals(regionId, that.regionId) &&
                Objects.equals(cityId, that.cityId) &&
                Objects.equals(street, that.street) &&
                Objects.equals(house, that.house);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryId, regionId, cityId, street, house);
    }
}
