package com.haeti.controller.prod;

import com.haeti.comm.Forward;
import com.haeti.controller.Action;
import com.haeti.dao.ProdDAO;
import com.haeti.dto.ProdDTO;
import com.haeti.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProdListAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProdService service = ProdService.getInstance();
        List<ProdDTO> list = service.getList();

        request.setAttribute("list", list);

        Forward forward = new Forward();
        forward.setForward(true);
        forward.setUrl("template.jsp?page=WEB-INF/prod/index.jsp");

        return forward;
    }
}
