package com.haeti.dto;

public class ChatDTO {
    private String prod_no;
    private String sender_id;
    private String buyer_no;
    private String chat_content;

    public String getChat_content() {
        return chat_content;
    }

    public void setChat_content(String chat_content) {
        this.chat_content = chat_content;
    }

    public String getProd_no() {
        return prod_no;
    }

    public void setProd_no(String prod_no) {
        this.prod_no = prod_no;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getBuyer_no() {
        return buyer_no;
    }

    public void setBuyer_no(String buyer_no) {
        this.buyer_no = buyer_no;
    }
}
