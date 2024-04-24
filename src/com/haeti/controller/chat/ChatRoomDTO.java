package com.haeti.controller.chat;

import javax.websocket.Session;

public class ChatRoomDTO {
    private String room="";
    private String user1="";
    private String user2="";

    public Session getUser1Session() {
        return user1Session;
    }

    private Session user1Session;
    private Session user2Session;

    public ChatRoomDTO(String room) {
        this.room = room;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public void setUser1Session(Session user1Session) {
        this.user1Session = user1Session;
    }

    public Session getUser2Session() {
        return user2Session;
    }

    public void setUser2Session(Session user2Session) {
        this.user2Session = user2Session;
    }
}