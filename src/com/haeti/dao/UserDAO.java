package com.haeti.dao;

import com.haeti.dto.UserDTO;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static UserDAO userDAO=new UserDAO();
    public static UserDAO getUserDAO(){
        return userDAO;
    }
    private UserDAO(){}


    public UserDTO getUserInfo(Connection conn, String user_id) throws SQLException {
        StringBuilder sql=new StringBuilder();
        sql.append("  select  user_no            ");
        sql.append("         , user_id           ");
        sql.append("         , pwd               ");
        sql.append("         , name              ");
        sql.append("         , nick_name         ");
        sql.append("         , tel               ");
        sql.append("         , email             ");
        sql.append("         , join_date         ");
        sql.append("         , teacher_school    ");
        sql.append("         , addr_dong         ");
        sql.append("         , addr_detail       ");
        sql.append("         , fav_region        ");
        sql.append("  from user                  ");
        sql.append("  where user_id = ?          ");

        ResultSet rs=null;
        UserDTO userDTO=new UserDTO();
        try(PreparedStatement pstmt= conn.prepareStatement(sql.toString())){
            pstmt.setString(1, user_id);
            rs= pstmt.executeQuery();
            if(rs.next()){
                userDTO.setUser_no(rs.getInt("user_no"));
                userDTO.setUser_id(rs.getString("user_id"));
                userDTO.setPwd(rs.getString("pwd"));
                userDTO.setName(rs.getString("name"));
                userDTO.setNick_name(rs.getString("nick_name"));
                userDTO.setTel(rs.getString("tel"));
                userDTO.setEmail(rs.getString("email"));
                userDTO.setJoin_date(rs.getDate("join_date").toLocalDate());
                userDTO.setTeacher_school(rs.getString("teacher_school"));
                userDTO.setAddr_dong(rs.getString("addr_dong"));
                userDTO.setAddr_detail(rs.getString("addr_detail"));
                userDTO.setFav_region(rs.getString("fav_region"));
            }
        }
        return userDTO;
    }

    public int modifyUser(Connection conn, UserDTO dto) throws SQLException{
        StringBuilder sql=new StringBuilder();
        sql.append("  update   user            ");
        sql.append("  set  pwd = ?             ");
        sql.append("      , nick_name = ?      ");
        sql.append("      , addr_dong = ?      ");
        sql.append("      , addr_detail = ?    ");
        sql.append("      , email = ?          ");
        sql.append("  where user_id = ?        ");

        int modify_result=0;
        try(PreparedStatement pstmt= conn.prepareStatement(sql.toString())){
            pstmt.setString(1, dto.getPwd());
            pstmt.setString(2, dto.getNick_name());
            pstmt.setString(3, dto.getAddr_dong());
            pstmt.setString(4, dto.getAddr_detail());
            pstmt.setString(5, dto.getEmail());
            pstmt.setString(6, dto.getUser_id());

            pstmt.executeUpdate();
            modify_result=1;
        }
        return modify_result;
    }

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

