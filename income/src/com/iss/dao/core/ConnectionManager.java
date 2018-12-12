package com.iss.dao.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * ���ڵõ�Connection����ģ�����Connection�����һ��
 * @author Administrator
 */
public abstract class ConnectionManager
{
      public static ThreadLocal<Connection> thread=new ThreadLocal<Connection>();
      public static ConnectionManager instance=null;
      
      private Connection con=null;
      /**
       * ���ڵõ�һ��Connection����
       * �ȴ�ThreadLocalȡ��ȡ����������������һ������
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
       * �ر�Connection
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
			System.out.println("ERROR_01_�ر�Connection");
			e.printStackTrace();
		}
      }
      /**
       * ����һ������
       */
      public void startTransaction()
      {
    	  try
		{
			Connection con=getConnection();
			  con.setAutoCommit(false);
		} catch (SQLException e)
		{
			System.out.println("ERROR_02_��������ʧ��");
			e.printStackTrace();
		}
      }
      /**
       * �ύ����
       */
      public void commit()
      {
    	  try
  		{
  			Connection con=getConnection();
  			  con.commit();
  		} catch (SQLException e)
  		{
  			System.out.println("ERROR_03_�ύ����ʧ��");
  			e.printStackTrace();
  		}  
      }
      /**
       * ����
       */
      public void rollback()
      {
    	  try
  		{
  			Connection con=getConnection();
  			  con.rollback();
  		} catch (SQLException e)
  		{
  			System.out.println("ERROR_03_�ύ����ʧ��");
  			e.printStackTrace();
  		}  
      }
      /**
       * ����ģ���������ʵ�֣����������õ���������
       * @return
       */
      public abstract Connection getRealConnection();
      
      /**
       * ���ѵ������� һ�������ͷ��������ѵ�һ���ԣ������ǳ����
       * һ���ǵõ�����������Ķ���
       * @return
       */
      public static ConnectionManager getInstance()
      {
    	  
    	     if(null==instance)
    	     {
		           //��jdbc.properties
		               //DSIMPS
		               ResourceBundle rs=ResourceBundle.getBundle("jdbc");//����jdbc.properties
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
