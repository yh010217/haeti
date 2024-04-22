package com.haeti.dto;

import java.time.LocalDate;

public class UserDTO {
    private int user_no;
    private String user_id;
    private String pwd;
    private String name;
    private String nick_name;
    private String tel;

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getJoin_date() {
        return join_date;
    }

    public void setJoin_date(LocalDate join_date) {
        this.join_date = join_date;
    }

    public String getTeacher_school() {
        return teacher_school;
    }

    public void setTeacher_school(String teacher_school) {
        this.teacher_school = teacher_school;
    }

    public String getAddr_detail() {
        return addr_detail;
    }

    public void setAddr_detail(String addr_detail) {
        this.addr_detail = addr_detail;
    }

    public String getAddr_dong() {
        return addr_dong;
    }

    public void setAddr_dong(String addr_dong) {
        this.addr_dong = addr_dong;
    }

    public String getFav_region() {
        return fav_region;
    }

    public void setFav_region(String fav_region) {
        this.fav_region = fav_region;
    }

    private String email;
    private LocalDate join_date;
    private String teacher_school;
    private String addr_detail;
    private String addr_dong;
    private String fav_region;
}
