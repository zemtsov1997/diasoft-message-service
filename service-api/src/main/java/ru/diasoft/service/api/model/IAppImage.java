package ru.diasoft.service.api.model;

public interface IAppImage {

    Long getId();

    void setImage(byte[] image);
    byte[] getImage();

}
