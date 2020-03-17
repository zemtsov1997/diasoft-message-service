package ru.diasoft.service.api.model;

import ru.diasoft.service.api.utils.ImageUtils;

public interface IRectangleImage extends IAppImage {

    default void resize() {
        this.setImage200x800(ImageUtils.scaleToSize(this.getImage(), 200, 800));
        this.setImage400x1600(ImageUtils.scaleToSize(this.getImage(), 400, 1600));
        this.setImage600x2400(ImageUtils.scaleToSize(this.getImage(), 600, 2400));
        this.setImage800x3200(ImageUtils.scaleToSize(this.getImage(), 800, 3200));
        this.setImage1000x4000(ImageUtils.scaleToSize(this.getImage(), 1000, 4000));
    }

    void setImage200x800(byte[] image200x800);
    void setImage400x1600(byte[] image400x1600);
    void setImage600x2400(byte[] image600x2400);
    void setImage800x3200(byte[] image800x3200);
    void setImage1000x4000(byte[] image1000x4000);

    byte[] getImage200x800();
    byte[] getImage400x1600();
    byte[] getImage600x2400();
    byte[] getImage800x3200();
    byte[] getImage1000x4000();

}
