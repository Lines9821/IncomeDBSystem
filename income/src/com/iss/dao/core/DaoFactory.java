package com.iss.dao.core;

import java.util.ResourceBundle;

public class DaoFactory
{
	/**
	 * 通过接口的class类型，得到接口的实现类
	 * @param clazz
	 * @return
	 */
	public static Object getDao(Class clazz)
	{
		Object re=null;
		ResourceBundle res=ResourceBundle.getBundle("dao");
		String value=res.getString(clazz.getName());
		try
		{
			re=Class.forName(value).newInstance();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return re;
	}

}
