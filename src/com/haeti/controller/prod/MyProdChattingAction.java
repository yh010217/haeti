package com.haeti.controller.prod;

import com.haeti.comm.Forward;
import com.haeti.controller.Action;
import com.haeti.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MyProdChattingAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int prod_no=Integer.parseInt(request.getParameter("prod_no"));
        ProdService prodService=ProdService.getInstance();
        //chat테이블은 prod_no와 buyer_no 로 식별할 수 있음.
        //그걸 prodService에서 groupby 같은 거로 묶어서 하나씩 가져와서 2개짜리 String 배열에다가
        //넣어서 List<String[]> 같은 걸 가져와서 request로 묶어서 보낼거임
        //그러면 my_prod_chatting.jsp에서는 그걸 받아서 채팅방 a 태그를 만들어주면 됨


        List<String[]> prod_buyer = prodService.getProdBuyer(prod_no);
        request.setAttribute("prod_buyer", prod_buyer);

        Forward forward=new Forward();
        forward.setForward(true);
        forward.setUrl("template.jsp?page=WEB-INF/prod/my_prod_chatting.jsp");
        return forward;

    }
}
