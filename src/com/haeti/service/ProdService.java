package com.haeti.service;


public class ProdService {
    private static ProdService prodService=new ProdService();
    public static ProdService getProdService(){
        return prodService;
    }
    private ProdService(){

    }
}
