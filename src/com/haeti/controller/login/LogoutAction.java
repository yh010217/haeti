package com.haeti.controller.login;

import com.haeti.comm.Forward;
import com.haeti.controller.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String user_id=(String)session.getAttribute("user_id");

        if (user_id!=null){
            session.invalidate();
        }

        Forward forward=new Forward();
        forward.setForward(false);
        forward.setUrl("login.do");

        return forward;
    }
}
