package com.iss.dao.imp;

import java.sql.SQLException;

import org.junit.Test;

import com.iss.dao.StuinfoDao;
import com.iss.dao.core.DaoFactory;

public class TestStuinfoDaoImp
{
  @Test
  public void testNewid()
  {
	  StuinfoDao sd=(StuinfoDao)DaoFactory.getDao(StuinfoDao.class);
	  try
	{
		int id=sd.nextNo(4);
		
		System.out.println(id);
	} catch (SQLException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}
