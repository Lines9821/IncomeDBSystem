package com.iss.dao;

import java.sql.SQLException;
import java.util.List;

import com.iss.pojo.Feeinfo;
import com.iss.utils.PageDiv;

public interface FeeinfoDao
{
   public void add(Feeinfo fee)throws SQLException;
   public void update(Feeinfo fee)throws SQLException;
   public void delete(int id)throws SQLException;
   public Feeinfo get(int id)throws SQLException;
   /**
    *找一个学生的交费记录
    * @param stuId
    * @return
    * @throws SQLException
    */
   public List<Object[]> getByStu(int stuId)throws SQLException;
   
   public PageDiv getAll(int pageNo,int pageSize)throws SQLException;
   public PageDiv searchStu(int pageNo,int pageSize,int stuid)throws SQLException;
   
}
