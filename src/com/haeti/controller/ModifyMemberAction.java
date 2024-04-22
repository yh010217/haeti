package com.haeti.controller;

import com.haeti.comm.Forward;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ModifyMemberAction implements Action{
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Forward forward=new Forward();
        forward.setForward(true);
        forward.setUrl("/WEB-INF/mypage/modify_member.jsp");
        return forward;
    }
}
