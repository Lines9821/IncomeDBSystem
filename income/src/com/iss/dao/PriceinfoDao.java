package com.iss.dao;

import java.sql.SQLException;

import com.iss.pojo.Priceinfo;

public interface PriceinfoDao
{
   public void add(Priceinfo price)throws SQLException;
   public void update(Priceinfo price)throws SQLException;
   public void delete(int id)throws SQLException;
   public Priceinfo get(int id)throws SQLException;
   
   public Priceinfo getByStuid(int id)throws SQLException;
   
}
