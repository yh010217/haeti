package com.haeti.controller;

import com.haeti.dto.ProdDTO;
import com.haeti.service.ProdService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SalesListResultAction", value = "/sales_list_result")
public class SalesListResultAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doReq(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doReq(request, response);
    }

    protected void doReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");

        int user_no=1;
        String status=request.getParameter("status");


        if("sale".equals(status) || status==null)
            status="판매중";
        else if("sale_comp".equals(status))
            status="판매완료";

        JSONArray arr=new JSONArray();

        ProdService prodService=ProdService.getInstance();
        List<ProdDTO> sales_list=prodService.salesList(status, user_no);

        for(ProdDTO dto:sales_list){
            JSONObject o1=new JSONObject();

            o1.put("title",dto.getTitle());
            o1.put("cost",dto.getCost());

            arr.add(o1);
        }

        PrintWriter out=response.getWriter();
        out.print(arr);
    }

}