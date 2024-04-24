package com.haeti.controller;

import com.haeti.comm.Forward;
import com.haeti.dto.UserDTO;
import com.haeti.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModifyUserResultAction implements Action{
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_id=request.getParameter("id");
        String pwd=request.getParameter("pwd");
        String nick_name=request.getParameter("nick_name");
        String addr_detail=request.getParameter("addr_detail"); //수정 필요
        String addr_dong=request.getParameter("addr_dong"); //수정 필요
        String fav_region=request.getParameter("fav_region1"); //수정 필요
        String email=request.getParameter("email");

        UserDTO dto=new UserDTO();
        dto.setUser_id(user_id);
        dto.setPwd(pwd);
        dto.setNick_name(nick_name);
        dto.setAddr_detail(addr_detail);
        dto.setAddr_dong(addr_dong);
        dto.setFav_region(fav_region);
        dto.setEmail(email);


        UserService userService=UserService.getUserService();

        List<UserDTO> userList=new ArrayList<>();
        userList=userService.userListCheck(user_id);

        Forward forward=new Forward();
//        forward.setForward(true);
//        forward.setUrl("/WEB-INF/mypage/modify_user_result.jsp");

        for(UserDTO userDTO:userList){
            if("".equals(nick_name)|| userDTO.getNick_name().equals(nick_name)){
                request.setAttribute("fail",0);
                forward.setForward(false);
                forward.setUrl("modify_user.do");
                break;
            }else if("".equals(email)|| userDTO.getEmail().equals(email)){
                request.setAttribute("fail",0);
                forward.setForward(false);
                forward.setUrl("modify_user.do");
                break;
            }else{
                int modify_result=userService.modifyUser(dto);
                request.setAttribute("modify_result", modify_result);
                forward.setForward(true);
                forward.setUrl("/WEB-INF/mypage/modify_user_result.jsp");
            }
        }




//        System.out.println(modify_result+".....");

/*        forward.setForward(true);
        forward.setUrl("/WEB-INF/mypage/modify_user_result.jsp");*/
        return forward;
    }
}
