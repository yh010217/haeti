package com.haeti.controller;

import com.haeti.comm.Forward;
import com.haeti.dto.ProdDTO;
import com.haeti.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PurchaseListAction implements Action{
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String period=request.getParameter("period");

        Forward forward=new Forward();
        forward.setForward(true);
        forward.setUrl("/WEB-INF/mypage/purchase_list.jsp");
        return forward;
    }
}
