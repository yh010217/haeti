package com.haeti.controller.mypage;

import com.haeti.comm.Forward;
import com.haeti.controller.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PurchaseListAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Forward forward=new Forward();
        forward.setForward(true);
        forward.setUrl("template.jsp?page=WEB-INF/mypage/purchase_list.jsp");
        return forward;
    }
}
