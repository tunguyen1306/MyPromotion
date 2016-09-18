package com.mypromotion.mypromotion.model;

/**
 * Created by TuNguyen on 09/16/2016.
 */
public class BrandDto {
    public static int idBrandPromotiom,idCategory;
    public static String NameBrandPromotiom;
    public int id_brand_promotiom ;
    public String name_brand_promotiom ;
    public BrandDto(int id_brand_promotiom,String name_brand_promotiom,String img_brand_promotiom,int percent_brand_promotiom) {
        this.id_brand_promotiom = id_brand_promotiom;
        this.name_brand_promotiom = name_brand_promotiom;
        this.img_brand_promotiom = img_brand_promotiom;
        this.percent_brand_promotiom = percent_brand_promotiom;
    }
    public int getPercent_brand_promotiom() {
        return percent_brand_promotiom;
    }

    public void setPercent_brand_promotiom(int percent_brand_promotiom) {
        this.percent_brand_promotiom = percent_brand_promotiom;
    }

    public int getId_brand_promotiom() {
        return id_brand_promotiom;
    }

    public void setId_brand_promotiom(int id_brand_promotiom) {
        this.id_brand_promotiom = id_brand_promotiom;
    }

    public String getName_brand_promotiom() {
        return name_brand_promotiom;
    }

    public void setName_brand_promotiom(String name_brand_promotiom) {
        this.name_brand_promotiom = name_brand_promotiom;
    }

    public String getAddress_brand_promotiom() {
        return address_brand_promotiom;
    }

    public void setAddress_brand_promotiom(String address_brand_promotiom) {
        this.address_brand_promotiom = address_brand_promotiom;
    }

    public int getStatus_brand_promotiom() {
        return status_brand_promotiom;
    }

    public void setStatus_brand_promotiom(int status_brand_promotiom) {
        this.status_brand_promotiom = status_brand_promotiom;
    }

    public int getType_brand_promotiom() {
        return type_brand_promotiom;
    }

    public void setType_brand_promotiom(int type_brand_promotiom) {
        this.type_brand_promotiom = type_brand_promotiom;
    }

    public String getPhone_brand_promotiom() {
        return phone_brand_promotiom;
    }

    public void setPhone_brand_promotiom(String phone_brand_promotiom) {
        this.phone_brand_promotiom = phone_brand_promotiom;
    }

    public String getWorking_brand_promotiom() {
        return working_brand_promotiom;
    }

    public void setWorking_brand_promotiom(String working_brand_promotiom) {
        this.working_brand_promotiom = working_brand_promotiom;
    }

    public int getAdvert_id_brand_promotiom() {
        return advert_id_brand_promotiom;
    }

    public void setAdvert_id_brand_promotiom(int advert_id_brand_promotiom) {
        this.advert_id_brand_promotiom = advert_id_brand_promotiom;
    }

    public String getImg_brand_promotiom() {
        return img_brand_promotiom;
    }

    public void setImg_brand_promotiom(String img_brand_promotiom) {
        this.img_brand_promotiom = img_brand_promotiom;
    }

    public String getShort_des_brand_promotion() {
        return short_des_brand_promotion;
    }

    public void setShort_des_brand_promotion(String short_des_brand_promotion) {
        this.short_des_brand_promotion = short_des_brand_promotion;
    }

    public String getStart_date_brand_promotion() {
        return start_date_brand_promotion;
    }

    public void setStart_date_brand_promotion(String start_date_brand_promotion) {
        this.start_date_brand_promotion = start_date_brand_promotion;
    }

    public String getEnd_date_brand_promotion() {
        return end_date_brand_promotion;
    }

    public void setEnd_date_brand_promotion(String end_date_brand_promotion) {
        this.end_date_brand_promotion = end_date_brand_promotion;
    }

    public String getCreated_date_brand_promotion() {
        return created_date_brand_promotion;
    }

    public void setCreated_date_brand_promotion(String created_date_brand_promotion) {
        this.created_date_brand_promotion = created_date_brand_promotion;
    }

    public String getModifi_date_brand_promotion() {
        return modifi_date_brand_promotion;
    }

    public void setModifi_date_brand_promotion(String modifi_date_brand_promotion) {
        this.modifi_date_brand_promotion = modifi_date_brand_promotion;
    }

    public String getCreate_user_brand_promotion() {
        return create_user_brand_promotion;
    }

    public void setCreate_user_brand_promotion(String create_user_brand_promotion) {
        this.create_user_brand_promotion = create_user_brand_promotion;
    }

    public String getModifi_user_brand_promotion() {
        return modifi_user_brand_promotion;
    }

    public void setModifi_user_brand_promotion(String modifi_user_brand_promotion) {
        this.modifi_user_brand_promotion = modifi_user_brand_promotion;
    }

    public int percent_brand_promotiom ;
    public int status_brand_promotiom ;
    public int type_brand_promotiom ;
    public String phone_brand_promotiom ;
    public String working_brand_promotiom ;
    public int advert_id_brand_promotiom ;

    public int getCategory_id_brand_promotion() {
        return category_id_brand_promotion;
    }

    public void setCategory_id_brand_promotion(int category_id_brand_promotion) {
        this.category_id_brand_promotion = category_id_brand_promotion;
    }

    public int category_id_brand_promotion ;
    public String img_brand_promotiom ;
    public String short_des_brand_promotion ;
    public String start_date_brand_promotion ;
    public String end_date_brand_promotion ;
    public String created_date_brand_promotion ;
    public String modifi_date_brand_promotion ;
    public String create_user_brand_promotion ;
    public String modifi_user_brand_promotion ;

    public static String getNameBrandPromotiom() {
        return NameBrandPromotiom;
    }

    public static void setNameBrandPromotiom(String nameBrandPromotiom) {
        NameBrandPromotiom = nameBrandPromotiom;
    }

    public String address_brand_promotiom ;
}
