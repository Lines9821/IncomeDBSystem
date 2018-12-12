package com.iss.dao;

import java.sql.SQLException;
import java.util.List;

import com.iss.pojo.Classinfo;

public interface ClassinfoDao
{
   public void add(Classinfo info)throws SQLException;
   public void update(Classinfo info)throws SQLException;
   public void delete(int id)throws SQLException;
   public Classinfo get(int id)throws SQLException;
   public List<Classinfo> getAll()throws SQLException;
   /**
    * 计算今年新班的号码
    * @param year
    * @return
    * @throws SQLException
    */
   public int newClassno(String year)throws SQLException;
   
}
