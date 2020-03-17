package ru.diasoft.service.api.dto;

import ru.diasoft.service.api.model.ISquareImage;
import ru.diasoft.service.api.utils.ImageUtils;

import java.util.Arrays;

public class SquareImageDto implements ISquareImage {

    private Long id;
    private byte[] image;
    private byte[] image50x50;
    private byte[] image100x100;
    private byte[] image200x200;
    private byte[] image300x300;
    private byte[] image400x400;
    private byte[] image500x500;

    public SquareImageDto() { }

    public SquareImageDto(Long id, byte[] image) {
        this.id = id;
        this.setImage(image);
    }

    public SquareImageDto(ISquareImage squareImage) {
        this.id = squareImage.getId();
        this.setImage(squareImage.getImage());
    }

    @Override
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
        this.image50x50 = ImageUtils.scaleToSize(image, 50, 50);
        this.image100x100 = ImageUtils.scaleToSize(image, 100, 100);
        this.image200x200 = ImageUtils.scaleToSize(image, 200, 200);
        this.image300x300 = ImageUtils.scaleToSize(image, 300, 300);
        this.image400x400 = ImageUtils.scaleToSize(image, 400, 400);
        this.image500x500 = ImageUtils.scaleToSize(image, 500, 500);
    }

    @Override
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public byte[] getImage50x50() {
        return image50x50 != null? image50x50 : this.getImage();
    }
    public void setImage50x50(byte[] image50x50) {
        this.image50x50 = image50x50;
    }
    @Override
    public byte[] getImage100x100() {
        return image100x100 != null? image100x100 : this.getImage50x50();
    }
    public void setImage100x100(byte[] image100x100) {
        this.image100x100 = image100x100;
    }
    @Override
    public byte[] getImage200x200() {
        return image200x200 != null? image200x200 : this.getImage100x100();
    }
    public void setImage200x200(byte[] image200x200) {
        this.image200x200 = image200x200;
    }
    @Override
    public byte[] getImage300x300() {
        return image300x300 != null ? image300x300 : this.getImage200x200();
    }
    public void setImage300x300(byte[] image300x300) {
        this.image300x300 = image300x300;
    }
    @Override
    public byte[] getImage400x400() {
        return image400x400 != null? image400x400 : this.getImage300x300();
    }
    public void setImage400x400(byte[] image400x400) {
        this.image400x400 = image400x400;
    }
    @Override
    public byte[] getImage500x500() {
        return image500x500 != null? image500x500 : this.getImage400x400();
    }
    public void setImage500x500(byte[] image500x500) {
        this.image500x500 = image500x500;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SquareImageDto logo = (SquareImageDto) o;
        return Arrays.equals(image, logo.image);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(image);
    }

}
