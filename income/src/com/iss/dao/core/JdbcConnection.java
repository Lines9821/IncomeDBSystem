package com.iss.dao.core;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnection extends ConnectionManager
{
	 public static String driver=null;
	 public static String url=null;
	 public static String user=null;
	 public static String pwd=null;

	static{
		Properties p=new Properties();
		try
		{
			p.load(JdbcConnection.class.getResourceAsStream("/jdbc.properties"));
			
		      driver=p.getProperty("driver");
		      url=p.getProperty("url");
		      user=p.getProperty("user");
		      pwd=p.getProperty("pwd");
		      
		      Class.forName(driver);
		} catch (Exception e)
		{
			System.out.println("加载驱动出错");
			e.printStackTrace();
		}
	}
	@Override
	public Connection getRealConnection()
	{
		Connection con=null;
		try
		{
			con=DriverManager.getConnection(url,user,pwd);
		} catch (SQLException e)
		{
		  System.out.println("建立链接失败.....");
			e.printStackTrace();
		}
		return con;
	}
}
