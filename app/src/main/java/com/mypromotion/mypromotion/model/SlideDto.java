package com.mypromotion.mypromotion.model;

/**
 * Created by TuNguyen on 09/13/2016.
 */
public class SlideDto {
    public SlideDto(String id_slide,String img_slide,String title_slide,String des_slide) {
        this.id_slide=id_slide;
        this.img_slide=img_slide;
        this.title_slide=title_slide;
        this.des_slide=des_slide;

    }

    public String getId_slide() {
        return id_slide;
    }

    public void setId_slide(String id_slide) {
        this.id_slide = id_slide;
    }

    public String getImg_slide() {
        return img_slide;
    }

    public void setImg_slide(String img_slide) {
        this.img_slide = img_slide;
    }

    public String getTitle_slide() {
        return title_slide;
    }

    public void setTitle_slide(String title_slide) {
        this.title_slide = title_slide;
    }

    public String getDes_slide() {
        return des_slide;
    }

    public void setDes_slide(String des_slide) {
        this.des_slide = des_slide;
    }

    public int getStatus_slide() {
        return status_slide;
    }

    public void setStatus_slide(int status_slide) {
        this.status_slide = status_slide;
    }

    public String id_slide ;
    public String img_slide ;
    public String title_slide ;
    public String des_slide ;
    public int status_slide ;
}
