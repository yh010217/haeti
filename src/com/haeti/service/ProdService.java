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

    public List<ProdDTO> getList() {
        DBConnection db = DBConnection.getInstance();
        Connection conn = null;
        List<ProdDTO> list = new ArrayList<>();
        try{
            conn=db.getConnection();
            ProdDAO dao = ProdDAO.getProdDAO();
            list = dao.getList(conn);

        }catch (SQLException | NamingException e){
            System.out.println("ProdService getList exception");
        }finally {
            db.disconn(conn);
        }
        return list;
    }
}
