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
}
