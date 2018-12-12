package com.iss.dao.core;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Connection extends ConnectionManager
{
   public static ComboPooledDataSource ds=new ComboPooledDataSource();
	@Override
	public Connection getRealConnection()
	{
		Connection con=null;
		try
		{
			con=ds.getConnection();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}

}
