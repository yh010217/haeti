package com.haeti.controller;

import com.haeti.comm.Forward;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CategoryListAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Forward forward = new Forward();
        forward.setForward(true);
        forward.setUrl("template.jsp?page=WEB-INF/prod/categorylist.jsp");

        return forward;
    }
}
