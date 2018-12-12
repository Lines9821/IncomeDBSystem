package com.iss.utils;

import java.sql.SQLException;
import java.util.Calendar;

import com.iss.dao.ClassinfoDao;
import com.iss.dao.core.DaoFactory;

public class Classutils
{
	/**
	 * 自动产生班级编号
	 * @return
	 */
	public static String createClassNo()
	{
		StringBuilder re=new StringBuilder();
		Calendar  cal=Calendar.getInstance();
		int  year=cal.get(Calendar.YEAR);
		re.append(year);
		
		
		ClassinfoDao cd=(ClassinfoDao)DaoFactory.getDao(ClassinfoDao.class);
		  try
		{
			int newcno=cd.newClassno(String.valueOf(year));
			if(newcno<10)
			{
				re.append(0);
				re.append(newcno);
			}else
			{
				re.append(newcno);
			}
			
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return re.toString();
	}
   public static void main(String[] args)
{
	System.out.println(createClassNo());
}
}
