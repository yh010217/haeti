package com.haeti.dao;

import com.haeti.comm.DBConnection;
import com.haeti.dto.ProdDTO;

import javax.naming.NamingException;
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


    /**  기간별 구매내역  */
    public List<ProdDTO> purchaseList(Connection conn, int period) throws SQLException{
        //쿼리문 수정 필요!!
        StringBuilder sql=new StringBuilder();
        sql.append("  select        prod_no                  ");
        sql.append("              , title                    ");
        sql.append("              , content                  ");
        sql.append("              , write_date               ");
        sql.append("              , cost                     ");
        sql.append("              , category_id              ");
        sql.append("  from  prod                             ");
        sql.append("  WHERE DATEDIFF(NOW(),write_date) <= ?   ");

        List<ProdDTO> purchase_list=new ArrayList<>();
        ResultSet rs=null;

        try(PreparedStatement pstmt=conn.prepareStatement(sql.toString());
        ){
            pstmt.setInt(1, period);
            rs=pstmt.executeQuery();
            while (rs.next()){
                ProdDTO dto=new ProdDTO();
                dto.setProd_no(rs.getInt("prod_no"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setWrite_date(rs.getDate("write_date").toLocalDate());
                dto.setCost(rs.getInt("cost"));
                dto.setCategory_id(rs.getInt("category_id"));
                purchase_list.add(dto);

            }
        }

        return purchase_list;
    }
}
