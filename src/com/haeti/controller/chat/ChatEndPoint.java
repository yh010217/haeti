package com.haeti.controller.chat;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint("/ChattingRoom")
public class ChatEndPoint {
    private static String nowRoom = "";

    /* 채팅방 이름, 채팅방 유저를 담은 DTO */
    private static Map<String,ChatRoomDTO> userMap = Collections.synchronizedMap(new HashMap<String,ChatRoomDTO>());
    static{
        userMap.put("1",new ChatRoomDTO("1"));
        userMap.put("2",new ChatRoomDTO("2"));
        userMap.put("3",new ChatRoomDTO("3"));
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {

        String[] splitMessage = message.split("#");

        String sender = "";
        String receiver = "";
        
        String room = splitMessage[0];
        System.out.println(room);
        String content = splitMessage[1];
        System.out.println(content);
        if(userMap.containsKey(room)){
            synchronized (userMap){
                ChatRoomDTO dto = userMap.get(room);
                //흠 어차피 같은 세션을 가진 사람이 지금은 없겠다만,
                //나중에는 id 로만 구분해야할텐데, 그때는 어떡하지,
                //DTO에서 id로 하는 게 아니라 세션도 계속 구분해야하나
                //채팅방 접근 관리(onOpen)은 id로 message 관리는 세션으로?
                String user1 = dto.getUser1();
                String user2 = dto.getUser2();
                
                // 일단은 세션에 다 보냄. 근데 받는 친구는 그 prod_no안에서의 receiver만 받아야함
                // 근데, 사실 보안적으로 좋지는 않은듯, 모든 신호가 다 감청되니깐
                // 그리고, 모든 웹에다가 다 뿌려지고 그걸 각각 걸러들어야된다는 얘기니깐, 그니까 모두에게 다
                // 뿌려줬다는 얘기니깐, session에 대한 조작도 위에 있어야될듯
                session.getBasicRemote().sendText(session+"|"+content);
            }
        }
    }

    @OnOpen
    public void onOpen(Session session) throws IOException {

        ChatRoomDTO dto = null;
        if(userMap.containsKey(nowRoom)){
            dto=userMap.get(nowRoom);

            if(dto.isRoomFull()) {
                session.getBasicRemote().sendText(session + "| 님은 들어오실 수 없습니다");
                //뭐 여기서 id 체크하고 한 채팅방에 여러 브라우저로 접근하지 말라고 경고 띄우면 되지 않을까
            }
            else{
                if(dto.getUser1()==null){
                    //dto.setUser1(session);
                    //if도 바꿔야되고, 아니 사실 근데 굳이 못들어오게 해야되나?
                }else if(dto.getUser2()==null){
                    //dto.setUser2(session);
                }
            }
        }
        nowRoom="";
    }
    @OnClose
    public void onClose(Session session) {

    }
    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    public static void setNowRoom(String room){
        nowRoom=room;
    }

}
