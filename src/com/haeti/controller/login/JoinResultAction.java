package com.haeti.controller.login;

import com.haeti.comm.Forward;
import com.haeti.controller.Action;
import com.haeti.dto.UserDTO;
import com.haeti.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class JoinResultAction implements Action {

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_id=request.getParameter("user_id");
        String pwd=request.getParameter("pwd");
        String name=request.getParameter("name");
        String nick_name=request.getParameter("nick_name");
        String teacher_school
              =request.getParameter("teacher_school");
//                =request.getParameter("ele_school")
//                +','
//                +request.getParameter("middlehigh");

        String addr_dong
                =request.getParameter("postcode")
                +","
                +request.getParameter("addr");
        String addr_detail
                =request.getParameter("addr_detail")
                +","
                +request.getParameter("addr_extra");
        String fav_region
                =request.getParameter("fav_region");

        String tel=request.getParameter("tel");
        String email=request.getParameter("email");

        UserDTO dto=new UserDTO();
        dto.setUser_id(user_id);
        dto.setPwd(pwd);
        dto.setName(name);
        dto.setNick_name(nick_name);
        dto.setTeacher_school(teacher_school);
        dto.setAddr_dong(addr_dong);
        dto.setAddr_detail(addr_detail);
        dto.setFav_region(fav_region);
        dto.setTel(tel);
        dto.setEmail(email);


        UserService service=UserService.getUserService();
        int join_result=service.JoinUser(dto);
        request.setAttribute("join_result",join_result);

        Forward forward=new Forward();
        forward.setForward(true);
        forward.setUrl("/WEB-INF/login/joinresult.jsp");

        return forward;
    }
}
