package com.iss.dao.imp;

import java.sql.SQLException;

import org.junit.Test;

import com.iss.dao.AdminDao;
import com.iss.pojo.Admin;
import com.iss.utils.MD5;

public class TestAdminDaoImp
{
	@Test
	  public void testAdd()
	  {
		  AdminDao ad=new AdminDaoImp();
		  Admin admin=new Admin();
		  admin.setUname("admin");
		  admin.setUpwd(MD5.tomd5("admin"));
		  admin.setUposition("CEO");
		  admin.setUpur("1000000000");
		  admin.setUstatus(0);
		  
		  try
		{
			ad.add(admin);
			System.out.println("YES");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	
	@Test
	public void testCheclLogin()
	{
		AdminDao ad=new AdminDaoImp();
		try
		{
			Admin admin=ad.checkLogin("admin",MD5.tomd5("123"));
			if(admin==null)
			{
				System.out.println("NO");
			}else
			{
				System.out.println("YES,,,Welcome,"+admin.getUname());
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
