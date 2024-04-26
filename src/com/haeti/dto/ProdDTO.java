package com.haeti.dto;

import java.time.LocalDate;
import java.util.List;

public class ProdDTO {
    private int prod_no;
    private String title;
    private String content;
    private LocalDate write_date;
    private int cost;
    private String keyword;
    private int category_id;
    private int seller_user_no;
    private List<String> img_paths;
    private String category;
    private LocalDate sell_date;
    private String buyer_id;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getImg_paths() {
        return img_paths;
    }

    public void setImg_paths(List<String> img_paths) {
        this.img_paths = img_paths;
    }



    public int getProd_no() {
        return prod_no;
    }

    public void setProd_no(int prod_no) {
        this.prod_no = prod_no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getWrite_date() {
        return write_date;
    }

    public void setWrite_date(LocalDate write_date) {
        this.write_date = write_date;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getSeller_user_no() {
        return seller_user_no;
    }

    public void setSeller_user_no(int seller_user_no) {
        this.seller_user_no = seller_user_no;
    }

    public LocalDate getSell_date() {
        return sell_date;
    }

    public void setSell_date(LocalDate sell_date) {
        this.sell_date = sell_date;
    }


    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }
}

