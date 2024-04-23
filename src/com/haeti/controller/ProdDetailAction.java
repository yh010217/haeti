package com.haeti.controller;

import com.haeti.comm.Forward;
import com.haeti.dto.ProdDTO;
import com.haeti.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProdDetailAction implements Action{
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ProdService service = ProdService.getInstance();
        int prod_no = Integer.parseInt(request.getParameter("prod_no"));
        ProdDTO dto = service.prodDetail(prod_no);


        request.setAttribute("dto", dto);

        String uploadPath = request.getServletContext().getRealPath("upload") + "\\" + prod_no;
        request.setAttribute("path",uploadPath);


        Forward forward = new Forward();
        forward.setForward(true);
        forward.setUrl("/WEB-INF/prod/prod_detail.jsp");
        return forward;
    }
}
