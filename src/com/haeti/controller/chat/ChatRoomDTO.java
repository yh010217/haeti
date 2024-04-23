package com.haeti.controller.chat;

import javax.websocket.Session;

public class ChatRoomDTO {
    private String room="";
    private String user1="";
    private String user2="";

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

    public boolean isRoomFull(){
        if(user1 != "" && user2 != "") return true;
        else return false;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

}