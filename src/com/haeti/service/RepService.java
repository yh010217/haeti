package com.haeti.service;

import com.haeti.comm.DBConnection;
import com.haeti.dao.RepDAO;
import com.haeti.dto.RepDTO;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RepService {
    private static RepService instance = new RepService();
    private RepService() {}
    public static RepService getInstance() {
        return instance;
    }

    public List<RepDTO> repList(int prod_no) {
        List<RepDTO> repList = null;
        RepDAO dao = RepDAO.getInstance();
        Connection conn = null;
        DBConnection db = DBConnection.getInstance();
        try{
            conn = db.getConnection();
            repList = dao.repList(conn, prod_no);

        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally {
            if(conn != null) try { conn.close(); } catch (SQLException e) {}
        }
        return repList;
    }


    public void repDelete(int rep_no) {
        RepDAO dao = RepDAO.getInstance();
        Connection conn = null;
        DBConnection db = DBConnection.getInstance();
        try{
            conn = db.getConnection();
            dao.repDelete(conn, rep_no);
            conn.commit();
        }catch (SQLException | NamingException e){
            System.out.println(e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            if (conn != null) try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }
}
