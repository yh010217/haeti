package com.haeti.controller.prod;

import com.haeti.comm.Forward;
import com.haeti.controller.Action;
import com.haeti.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ReviewWriteResultAction implements Action {

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("user_id");
        String prod_no = request.getParameter("prod_no");
        String repcontent = request.getParameter("repcontent");

        ProdService service = ProdService.getInstance();
        service.repWrite(user_id, prod_no, repcontent);



        Forward forward = new Forward();
        forward.setForward(false);
        forward.setUrl("prod_detail.do?prod_no="+prod_no);
        return forward;
    }
}
