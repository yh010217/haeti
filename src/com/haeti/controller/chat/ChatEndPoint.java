package com.haeti.controller.chat;

import com.haeti.service.ProdService;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint("/ChattingRoom")
public class ChatEndPoint {
    private static String nowRoom = "";
    private static String nowUser = "";

    /* 채팅방 이름, 채팅방 유저를 담은 DTO */
    private static Map<String, ChatRoomDTO> userMap = Collections.synchronizedMap(new HashMap<String, ChatRoomDTO>());

    public static void putUserMap(String prod_no, String buyer) {
        if (!userMap.containsKey(prod_no + "#" + buyer)) {
            ChatRoomDTO dto = new ChatRoomDTO(prod_no + "#" + buyer);
            ProdService service = ProdService.getInstance();
            String seller = service.getSellerId(prod_no);
            dto.setUser1(seller);
            dto.setUser2(buyer);
            userMap.put(prod_no + "#" + buyer, dto);
        }
    }

    //prod_no#buyer#현재유저 + "▤" + inputMessage.value

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {

        String[] splitMessage = message.split("#");
        String prod_no = splitMessage[0];
        String buyer = splitMessage[1];
        String room = prod_no+"#"+buyer;
        String user = splitMessage[2];
        String content = splitMessage[3];
/*
        String sender = "";
        String receiver = "";
        String room = splitMessage[0];
        System.out.println(room);
        String content = splitMessage[1];
        System.out.println(content);*/
        if (userMap.containsKey(room)) {
            synchronized (userMap) {
                ChatRoomDTO dto = userMap.get(room);
                //흠 어차피 같은 세션을 가진 사람이 지금은 없겠다만,
                //나중에는 id 로만 구분해야할텐데, 그때는 어떡하지,
                //DTO에서 id로 하는 게 아니라 세션도 계속 구분해야하나
                //채팅방 접근 관리(onOpen)은 id로 message 관리는 세션으로?
                String user1 = dto.getUser1();
                String user2 = dto.getUser2();
                Session toSend = null;
                if(!user1.equals(user) && user2.equals(user) ) toSend = dto.getUser1Session();
                if(!user2.equals(user) && user1.equals(user) ) toSend = dto.getUser2Session();

                // 일단은 세션에 다 보냄. 근데 받는 친구는 그 prod_no안에서의 receiver만 받아야함
                // 근데, 사실 보안적으로 좋지는 않은듯, 모든 신호가 다 감청되니깐
                // 그리고, 모든 웹에다가 다 뿌려지고 그걸 각각 걸러들어야된다는 얘기니깐, 그니까 모두에게 다
                // 뿌려줬다는 얘기니깐, session에 대한 조작도 위에 있어야될듯
                toSend.getBasicRemote().sendText(user + "|" + content);
            }
        }
    }

    @OnOpen
    public void onOpen(Session session) throws IOException {
        ChatRoomDTO dto = null;
        if (userMap.containsKey(nowRoom)) {
            dto = userMap.get(nowRoom);
            if (dto.getUser1().equals(nowUser)) {
                dto.setUser1Session(session);
            } else if (dto.getUser2().equals(nowUser)) {
                dto.setUser2Session(session);
            }
        }
        nowRoom = "";
        nowUser = "";
    }

    @OnClose
    public void onClose(Session session) {

    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    public static void setNowRoom(String room) {
        nowRoom = room;
    }
    public static void setNowUser(String user){
        nowUser = user;
    }

}
