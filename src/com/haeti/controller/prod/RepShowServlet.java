package com.haeti.controller.prod;

import com.haeti.dto.RepDTO;
import com.haeti.service.RepService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/repshow")
public class RepShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doReq(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doReq(req, resp);
    }
    private void doReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int prod_no = Integer.parseInt(request.getParameter("prod_no"));

        RepService service = RepService.getInstance();
        List<RepDTO> repList = service.repList(prod_no);

        JSONArray arr = new JSONArray();
        for(RepDTO dto : repList) {
            JSONObject o = new JSONObject();
            o.put("rep_no", dto.getRep_no());
            o.put("repcontent", dto.getRepcontent());
            //o.put("repdate", dto.getRepdate());
            o.put("prod_no", dto.getProd_no());
            arr.add(o);
        }
        response.setContentType("text/json; charset=utf-8");
        PrintWriter out=response.getWriter();
        out.print(arr);
    }
}
