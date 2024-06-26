package com.haeti.controller.mypage;

import com.haeti.comm.Forward;
import com.haeti.controller.Action;
import com.haeti.dto.UserDTO;
import com.haeti.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModifyUserResultAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_id=request.getParameter("id");
        String pwd=request.getParameter("pwd");
        String nick_name=request.getParameter("nick_name");
        String addr_dong
                =request.getParameter("postcode")
                +","
                +request.getParameter("addr");
        String addr_detail
                =request.getParameter("addr_detail")
                +","
                +request.getParameter("addr_extra");
        String fav_region=request.getParameter("fav_region");
        String email=request.getParameter("email");

        UserDTO dto=new UserDTO();
        dto.setUser_id(user_id);
        dto.setPwd(pwd);
        dto.setNick_name(nick_name);
        dto.setAddr_dong(addr_dong);
        dto.setAddr_detail(addr_detail);
        dto.setFav_region(fav_region);
        dto.setEmail(email);

        UserService userService=UserService.getUserService();
        int modify_result=userService.modifyUser(dto);
        request.setAttribute("modify_result", modify_result);

        Forward forward=new Forward();

        // 관리자 계정에서 수정했을 경우에는 관리자 페이지로 돌아가도록 함
        HttpSession session = request.getSession();
        String user_check = (String) session.getAttribute("user_id");

        if("admin".equals(user_check)){
            forward.setForward(false);
            forward.setUrl("admin.do");
        } else {
            forward.setForward(true);
            forward.setUrl("/WEB-INF/mypage/modify_user_result.jsp");
        }

        return forward;
    }
}
