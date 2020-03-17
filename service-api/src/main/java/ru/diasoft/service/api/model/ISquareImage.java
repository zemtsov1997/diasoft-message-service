package ru.diasoft.service.api.model;

import ru.diasoft.service.api.utils.ImageUtils;

public interface ISquareImage extends IAppImage {

    default void resize() {
        this.setImage50x50(ImageUtils.scaleToSize(this.getImage(), 50, 50));
        this.setImage100x100(ImageUtils.scaleToSize(this.getImage(), 100, 100));
        this.setImage200x200(ImageUtils.scaleToSize(this.getImage(), 200, 200));
        this.setImage300x300(ImageUtils.scaleToSize(this.getImage(), 300, 300));
        this.setImage400x400(ImageUtils.scaleToSize(this.getImage(), 400, 400));
        this.setImage500x500(ImageUtils.scaleToSize(this.getImage(), 500, 500));
    }

    void setImage50x50(byte[] image50x50);
    void setImage100x100(byte[] image100x100);
    void setImage200x200(byte[] image200x200);
    void setImage300x300(byte[] image300x300);
    void setImage400x400(byte[] image400x400);
    void setImage500x500(byte[] image500x500);

    byte[] getImage50x50();
    byte[] getImage100x100();
    byte[] getImage200x200();
    byte[] getImage300x300();
    byte[] getImage400x400();
    byte[] getImage500x500();

}
