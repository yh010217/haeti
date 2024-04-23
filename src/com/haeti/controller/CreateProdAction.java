package com.haeti.controller;

import com.haeti.comm.Forward;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateProdAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Forward forward = new Forward();
        forward.setForward(true);
        forward.setUrl("/WEB-INF/prod/create_prod.jsp");
        return forward;
    }
}
