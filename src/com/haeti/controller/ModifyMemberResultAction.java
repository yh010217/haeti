package com.haeti.controller;

import com.haeti.comm.Forward;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ModifyMemberResultAction implements Action{
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Forward forward=new Forward();
        forward.setForward(false);
        forward.setUrl("mypage.do");
        return forward;
    }
}
