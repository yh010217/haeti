package com.haeti.dao;

import com.haeti.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private static UserDAO dao=new UserDAO();
    public static UserDAO getDAO() {
        return dao;
    }


    public UserDAO(){};

    public void insertData(Connection conn, String user_id, String pwd, String name, String nick_name, String tel, String email, String addr_dong, String addr_detail, String fav_region) throws SQLException {
        StringBuilder sql=new StringBuilder();
        sql.append(" insert into user(             ");
        sql.append("                    user_id    ");
        sql.append("                  , pwd       ");
        sql.append("                  , name       ");
        sql.append("                  , nick_name  ");
        sql.append("                  , tel        ");
        sql.append("                  , email      ");
        sql.append("                  , addr_dong  ");
        sql.append("                  , addr_detail  ");
        sql.append("                  , fav_region ) ");
        sql.append("     values(  ?,  ?,   ?,  ?, ? , ? , ?, ?, ? )           ");
        try (PreparedStatement pstmt=conn.prepareStatement(sql.toString());
        ){
            pstmt.setString(1,user_id);
            pstmt.setString(2,pwd);
            pstmt.setString(3,name);
            pstmt.setString(4,nick_name);
            pstmt.setString(5,tel);
            pstmt.setString(6,email);
            pstmt.setString(7,addr_dong);
            pstmt.setString(8,addr_detail);
            pstmt.setString(9,fav_region);
            pstmt.executeUpdate();
        }

    }
    public int login(Connection conn,String user_id, String pwd) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select     pwd   ");
        sql.append(" from     user      ");
        sql.append(" where    user_id = ?  ");
        int result=0;
        ResultSet rs=null;
        try (PreparedStatement pstmt=conn.prepareStatement(sql.toString());
        ){

            pstmt.setString(1, user_id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                if(rs.getString("pwd").equals(pwd)
                ) {
                    result=1;
                } else{
                   result=0;
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (conn!=null) try {conn.close();}catch (Exception e){}
            if (rs!=null) try {rs.close();}catch (Exception e){}

        }
        return result;
    }


    public String loginemail(Connection conn, String user_id) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select     pwd   ");
        sql.append("           , email ");
        sql.append(" from     user      ");
        sql.append(" where    user_id = ?  ");
        String result="";
        ResultSet rs=null;
        try (PreparedStatement pstmt=conn.prepareStatement(sql.toString());
        ){

            pstmt.setString(1, user_id);
            rs = pstmt.executeQuery();
            if (rs.next()){
                result=rs.getString("email");
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (conn!=null) try {conn.close();}catch (Exception e){}
            if (rs!=null) try {rs.close();}catch (Exception e){}

        }
        return result;

    }


    public UserDTO loginlist(Connection conn, String user_id) throws SQLException{
        StringBuilder sql=new StringBuilder();
        sql.append(" select     pwd          ");
        sql.append("           , name         ");
        sql.append("           ,nick_name      ");
        sql.append("           ,tel            ");
        sql.append("           , email          ");
        sql.append("           , addr_dong      ");
        sql.append("           , addr_detail     ");
        sql.append("           , fav_region     ");
        sql.append("       from  user             ");
        sql.append("      where  user_id = ?      ");
        ResultSet rs=null;
        UserDTO dto=new UserDTO();
        PreparedStatement pstmt=null;
        try {
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, user_id);
            rs=pstmt.executeQuery();
            if (rs.next()){
                dto.setPwd(rs.getString("pwd"));
                dto.setName(rs.getString("name"));
                dto.setNick_name(rs.getString("nick_name"));
                dto.setTel(rs.getString("tel"));
                dto.setEmail(rs.getString("email"));
                dto.setAddr_dong(rs.getString("addr_dong"));
                dto.setAddr_detail(rs.getString("addr_detail"));
                dto.setFav_region(rs.getString("fav_region"));
            }

        }finally {
            if (pstmt!=null) try {pstmt.close();}catch (Exception e){}
        }
        return dto;
    }
}

