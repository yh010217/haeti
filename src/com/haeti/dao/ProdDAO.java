package com.haeti.dao;

import com.haeti.dto.ProdDTO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdDAO {
    private static ProdDAO prodDAO=new ProdDAO();
    public static ProdDAO getProdDAO(){
        return prodDAO;
    }
    private ProdDAO(){}


    public List<ProdDTO> getList(Connection conn) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT p.prod_no              ");
        sql.append("            , title             ");
        sql.append("            , write_date        ");
        sql.append("            , cost              ");
        sql.append("            , i1.img_url        ");
        sql.append("  FROM prod p Inner JOIN        ");
        sql.append("                       ( SELECT img_url      ");
        sql.append("                               , prod_no     ");
        sql.append("                               , ROW_NUMBER() OVER(PARTITION BY prod_no) AS rn   ");
        sql.append("                         FROM image ) i1     ");
        sql.append("               ON p.prod_no = i1.prod_no     ");
        sql.append("   WHERE rn=1                                ");
        sql.append("   ORDER BY p.prod_no DESC;                  ");

        List<ProdDTO> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());
             ResultSet rs = pstmt.executeQuery();
        ) {
            if(rs.next()){
                ProdDTO dto = new ProdDTO();
                dto.setProd_no(rs.getInt("p.prod_no"));
                dto.setTitle(rs.getString("title"));
                dto.setWrite_date(rs.getDate("write_date").toLocalDate());
                dto.setCost(rs.getInt("cost"));
                dto.set
            }
        }


        return list;
    }
}
