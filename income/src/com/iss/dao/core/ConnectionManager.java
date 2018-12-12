package com.iss.dao.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * 用于得到Connection对象的，管理Connection对象的一类
 * @author Administrator
 */
public abstract class ConnectionManager
{
      public static ThreadLocal<Connection> thread=new ThreadLocal<Connection>();
      public static ConnectionManager instance=null;
      
      private Connection con=null;
      /**
       * 用于得到一个Connection对象，
       * 先从ThreadLocal取，取不到话才真正建立一个链接
       * @return
       */
      public Connection getConnection()
      {
    	 con=thread.get();
    	  try
		{
			if(null==con||con.isClosed())
			  {
				  con=getRealConnection();
				  thread.set(con);
			  }
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  return con;
      }
      /**
       * 关闭Connection
       */
      public void closeConnection()
      {
    	  try
		{
			Connection con=getConnection();
			  if(null!=con&&!con.isClosed())
			  {
				  con.close();
			  }
		} catch (SQLException e)
		{
			System.out.println("ERROR_01_关闭Connection");
			e.printStackTrace();
		}
      }
      /**
       * 开启一个事务
       */
      public void startTransaction()
      {
    	  try
		{
			Connection con=getConnection();
			  con.setAutoCommit(false);
		} catch (SQLException e)
		{
			System.out.println("ERROR_02_开启事务失败");
			e.printStackTrace();
		}
      }
      /**
       * 提交事务
       */
      public void commit()
      {
    	  try
  		{
  			Connection con=getConnection();
  			  con.commit();
  		} catch (SQLException e)
  		{
  			System.out.println("ERROR_03_提交事务失败");
  			e.printStackTrace();
  		}  
      }
      /**
       * 撤销
       */
      public void rollback()
      {
    	  try
  		{
  			Connection con=getConnection();
  			  con.rollback();
  		} catch (SQLException e)
  		{
  			System.out.println("ERROR_03_提交事务失败");
  			e.printStackTrace();
  		}  
      }
      /**
       * 抽象的，留给子类实现，用于真正得到数据连接
       * @return
       */
      public abstract Connection getRealConnection();
      
      /**
       * 自已调用自已 一个方法就返回了自已的一个对，自已是抽像的
       * 一定是得到抽像类子类的对象
       * @return
       */
      public static ConnectionManager getInstance()
      {
    	  
    	     if(null==instance)
    	     {
		           //读jdbc.properties
		               //DSIMPS
		               ResourceBundle rs=ResourceBundle.getBundle("jdbc");//访问jdbc.properties
		               String key=rs.getString("DSIMPS");
		               try
					{
						Object obj=Class.forName(key).newInstance();
						instance=(ConnectionManager)obj;
					} catch (InstantiationException | IllegalAccessException
							| ClassNotFoundException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
               
    	     } 
               return instance;
    	}
      
}
