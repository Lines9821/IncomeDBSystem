package com.iss.dao;

import java.sql.SQLException;
import java.util.List;

import com.iss.pojo.Admin;

public interface AdminDao
{
   /**
    * 增加一个管理员
    * @param admin
    */
   public void add(Admin admin)throws SQLException;
   /**
    * 删除管理员
    * @param id  要删除的id
    */
   public void delete(int id)throws SQLException;
   /**
    * 修改管理员
    * @param admin
    */
   public void update(Admin admin)throws SQLException;
   /**
    * 查询所有管理员
    * @return
    */
   public List<Admin> getAll()throws SQLException;
   /**
    * 得到一个管理员对象
    * @param id
    * @return
    * @throws SQLException
    */
   public Admin get(int id)throws SQLException;
   /**
    * 验证用户名密码是否正确
    * @param uname
    * @param upwd
    * @return null用户名和密码不正确，
    */
   public Admin checkLogin(String uname,String upwd)throws SQLException;
}
