package com.haeti.dao;

import com.haeti.dto.ProdDTO;

import java.util.List;

public class ProdDAO {
    private static ProdDAO prodDAO=new ProdDAO();
    public static ProdDAO getProdDAO(){
        return prodDAO;
    }
    private ProdDAO(){}


}
