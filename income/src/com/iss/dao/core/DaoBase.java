package com.iss.dao.core;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;

public class DaoBase
{
  protected QueryRunner run=new QueryRunner();
  
  protected Connection getConnection()
  {
	  return ConnectionManager.getInstance().getConnection();
  }
  protected void closeConnection()
  {
	  ConnectionManager.getInstance().closeConnection();
  }
}
