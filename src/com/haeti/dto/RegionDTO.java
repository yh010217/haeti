package com.haeti.dto;

public class RegionDTO {
    private String no;
    private String si_do;
    private String si_gun_gu;
    private String eup_myeun_dong;
    private float lat;
    private float lng;
    private int prod_no;

    public int getProd_no() {
        return prod_no;
    }

    public void setProd_no(int prod_no) {
        this.prod_no = prod_no;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSi_do() {
        return si_do;
    }

    public void setSi_do(String si_do) {
        this.si_do = si_do;
    }

    public String getSi_gun_gu() {
        return si_gun_gu;
    }

    public void setSi_gun_gu(String si_gun_gu) {
        this.si_gun_gu = si_gun_gu;
    }

    public String getEup_myeun_dong() {
        return eup_myeun_dong;
    }

    public void setEup_myeun_dong(String eup_myeun_dong) {
        this.eup_myeun_dong = eup_myeun_dong;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }
}
