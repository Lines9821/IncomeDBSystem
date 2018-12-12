package com.iss.dao;

import java.sql.SQLException;
import java.util.List;

import com.iss.pojo.Admin;

public interface AdminDao
{
   /**
    * ����һ������Ա
    * @param admin
    */
   public void add(Admin admin)throws SQLException;
   /**
    * ɾ������Ա
    * @param id  Ҫɾ����id
    */
   public void delete(int id)throws SQLException;
   /**
    * �޸Ĺ���Ա
    * @param admin
    */
   public void update(Admin admin)throws SQLException;
   /**
    * ��ѯ���й���Ա
    * @return
    */
   public List<Admin> getAll()throws SQLException;
   /**
    * �õ�һ������Ա����
    * @param id
    * @return
    * @throws SQLException
    */
   public Admin get(int id)throws SQLException;
   /**
    * ��֤�û��������Ƿ���ȷ
    * @param uname
    * @param upwd
    * @return null�û��������벻��ȷ��
    */
   public Admin checkLogin(String uname,String upwd)throws SQLException;
}
