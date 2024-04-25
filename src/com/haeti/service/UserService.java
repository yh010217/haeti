package com.haeti.service;

import com.haeti.comm.DBConnection;
import com.haeti.dao.UserDAO;
import com.haeti.dto.ProdDTO;
import com.haeti.dto.UserDTO;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static UserService userService=new UserService();
    public static UserService getUserService(){
        return userService;
    }
    private UserService(){}

    public UserDTO getUserInfo(String user_id){
        Connection conn=null;
        DBConnection db=DBConnection.getInstance();
        UserDAO userDAO=UserDAO.getUserDAO();
        UserDTO userDTO=new UserDTO();

        try{
            conn= db.getConnection();
            userDTO=userDAO.getUserInfo(conn, user_id);

        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally {
            db.disconn(conn);
        }
        return userDTO;
    }

    public int modifyUser(UserDTO dto) {
        Connection conn=null;
        DBConnection db=DBConnection.getInstance();
        UserDAO userDAO=UserDAO.getUserDAO();

        int modify_result=0;
        try{
            conn=db.getConnection();
            modify_result=userDAO.modifyUser(conn, dto);

        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally {
            db.disconn(conn);
        }
        return modify_result;
    }

    public void insertService(String user_id, String pwd, String name, String nick_name, String tel, String email, String addr_dong, String addr_detail, String fav_region) {
        DBConnection db=DBConnection.getInstance();
        UserDAO dao=UserDAO.getUserDAO();
        Connection conn=null;
        try {
            conn=db.getConnection();
            dao.insertData(conn,user_id,pwd, name,nick_name, tel, email, addr_dong, addr_detail, fav_region);
        }catch (SQLException| NamingException e){
            System.out.println(e);
        }finally {
            if (conn!=null) try {conn.close();}catch (Exception e){System.out.println(e);}
        }
    }


    public int login(String user_id, String pwd) {
        DBConnection db=DBConnection.getInstance();
        UserDAO dao=UserDAO.getUserDAO();
        Connection conn=null;
        int login_result=0;
        try {
            conn=db.getConnection();
            login_result=dao.login(conn,user_id,pwd);
        }catch (SQLException| NamingException e){
            System.out.println(e);
        }finally {
            if (conn!=null) try {conn.close();}catch (Exception e){System.out.println(e);}
        }
        return login_result;
    }

    public int getCount(String search, String search_txt) {
        DBConnection db=DBConnection.getInstance();
        Connection conn=null;
        int result = 0;
        try{
            conn=db.getConnection();
            UserDAO dao = UserDAO.getUserDAO();
            result = dao.getCount(conn, search, search_txt);
        } catch (SQLException | NamingException e){
            System.out.println("userService getCount Exception");
        } finally {
            db.disconn(conn);
        }
        return result;
    }

    public List<UserDTO> getList(int startrow, int pagesize, String search, String search_txt) {
        DBConnection db=DBConnection.getInstance();
        Connection conn=null;
        List<UserDTO> list = new ArrayList<>();
        try{
            conn=db.getConnection();
            UserDAO dao = UserDAO.getUserDAO();
            list = dao.getList(conn, startrow, pagesize, search, search_txt);
        }  catch (SQLException | NamingException e){
            System.out.println("userService getList Exception");
        } finally {
            db.disconn(conn);
        }
        return list;
    }

    public UserDTO loginlist(String user_id) {
        DBConnection db=DBConnection.getInstance();
        Connection conn=null;
        UserDAO dao=UserDAO.getUserDAO();
        UserDTO dto=new UserDTO();
        try{
            conn=db.getConnection();
            dto=dao.loginlist(conn,user_id);
        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally {
            if (conn!=null)try {
                conn.close();
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return dto;
    }

    public boolean nickCheck(String nick_name, String user_id) {
        Connection conn=null;
        DBConnection db=DBConnection.getInstance();
        UserDAO dao=UserDAO.getUserDAO();
        boolean result=true;

        try{
            conn= db.getConnection();
            result=dao.nickCheck(conn, nick_name, user_id);

        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally {
            db.disconn(conn);
        }
        return result;
    }

    public boolean emailCheck(String email, String user_id) {
        Connection conn=null;
        DBConnection db=DBConnection.getInstance();
        UserDAO dao=UserDAO.getUserDAO();
        boolean result=true;

        try{
            conn= db.getConnection();
            result=dao.emailCheck(conn, email, user_id);

        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally {
            db.disconn(conn);
        }
        return result;
    }
    public int confirmId(String user_id) {
        Connection conn=null;
        DBConnection db=DBConnection.getInstance();
        UserDAO dao=UserDAO.getUserDAO();
        int confirmId_result=-1;// 있으면 1, 없으면 -1 초기값은 -1로 설정
        try {
            conn=db.getConnection();
            confirmId_result=dao.confirmID(user_id);
        }catch (Exception e){
            System.out.println(e);
        }finally {
            db.disconn(conn);
        }
        return confirmId_result;
    }

//    public boolean joinIdCheck(String user_id) {
//        Connection conn=null;
//        DBConnection db=DBConnection.getInstance();
//        UserDAO dao=UserDAO.getUserDAO();
//        boolean join_result=false;
//        try {
//            conn=db.getConnection();
//            join_result=dao.joinIdCheck(conn,user_id);
//
//        }catch (SQLException | NamingException e){
//            System.out.println(e);
//        }finally {
//            db.disconn(conn);
//        }
//        return join_result;
//    }

}
