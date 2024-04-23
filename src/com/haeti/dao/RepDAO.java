package com.haeti.dao;

import com.haeti.dto.RepDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepDAO {
    private static RepDAO instance = new RepDAO();
    private RepDAO() {}
    public static RepDAO getInstance() {
        return instance;
    }

    public List<RepDTO> repList(Connection conn, int prod_no) throws SQLException {
        List<RepDTO> repList = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT rep_no         ");
        sql.append("       ,repcontent    ");
        sql.append("       ,repdate       ");
        sql.append("    FROM rep          ");
        sql.append("   WHERE prod_no = ?; ");

        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        pstmt.setInt(1, prod_no);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            RepDTO dto = new RepDTO();
            dto.setRep_no(rs.getInt("rep_no"));
            dto.setRepcontent(rs.getString("repcontent"));
            dto.setRepdate(rs.getDate("repdate").toLocalDate());
            dto.setProd_no(prod_no);
            repList.add(dto);
        }
        if(rs != null) rs.close();
        if(pstmt != null) pstmt.close();
        return repList;
    }

    public void repDelete(Connection conn, int rep_no) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM rep ");
        sql.append(" WHERE rep_no = ?;");

        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        pstmt.setInt(1, rep_no);
        pstmt.executeUpdate();
        if(pstmt != null) pstmt.close();
    }
}
