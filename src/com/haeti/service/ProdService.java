package com.haeti.service;


import com.haeti.comm.DBConnection;
import com.haeti.dao.ProdDAO;
import com.haeti.dto.ProdDTO;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdService {
    private static ProdService prodService=new ProdService();
    public static ProdService getProdService(){
        return prodService;
    }
    private ProdService(){

    }

    public List<ProdDTO> purchaseList(int period){
        DBConnection db=DBConnection.getInstance();
        Connection conn=null;
        ProdDAO dao=ProdDAO.getProdDAO();
        List<ProdDTO> purchase_list=new ArrayList<>();

        try{
            conn= db.getConnection();
            purchase_list=dao.purchaseList(conn, period);


        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally {
            db.disconn(conn);
        }
        return purchase_list;
    }
}
