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

@WebServlet(name = "PurchaseListResultAction", value = "/purchase_list_result")
public class PurchaseListResultAction extends HttpServlet {
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

        int user_no=4;
        String period=request.getParameter("period");
        int period_select=7;

        if("month".equals(period))
            period_select=31;
        else if("3month".equals(period))
            period_select=31*3;

        JSONArray arr=new JSONArray();

        ProdService prodService=ProdService.getInstance();
        List<ProdDTO> purchase_list=prodService.purchaseList(period_select,user_no);

        for(ProdDTO dto:purchase_list){
            JSONObject o1=new JSONObject();

            o1.put("title",dto.getTitle());
            o1.put("cost",dto.getCost());

            arr.add(o1);
        }

        PrintWriter out=response.getWriter();
        out.print(arr);
    }

}