package com.haeti.controller.prod;

import com.haeti.comm.Forward;
import com.haeti.controller.Action;
import com.haeti.service.ProdService;
import com.haeti.service.RepService;
import com.sun.net.httpserver.HttpsServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RepDeleteAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int prod_no = Integer.parseInt(request.getParameter("prod_no"));

        int rep_no = Integer.parseInt(request.getParameter("rep_no"));
        RepService repService = RepService.getInstance();
        repService.repDelete(rep_no);



        Forward forward = new Forward();
        forward.setForward(false);
        forward.setUrl("prod_detail.do?prod_no="+prod_no);
        return forward;
    }
}
