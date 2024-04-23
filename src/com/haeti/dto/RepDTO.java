package com.haeti.dto;

import java.time.LocalDate;

public class RepDTO {
    private int rep_no;
    private String repcontent;
    private int user_no;
    private LocalDate repdate;
    private int prod_no;

    public int getRep_no() {
        return rep_no;
    }

    public void setRep_no(int rep_no) {
        this.rep_no = rep_no;
    }

    public String getRepcontent() {
        return repcontent;
    }

    public void setRepcontent(String repcontent) {
        this.repcontent = repcontent;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public LocalDate getRepdate() {
        return repdate;
    }

    public void setRepdate(LocalDate repdate) {
        this.repdate = repdate;
    }

    public int getProd_no() {
        return prod_no;
    }

    public void setProd_no(int prod_no) {
        this.prod_no = prod_no;
    }
}
