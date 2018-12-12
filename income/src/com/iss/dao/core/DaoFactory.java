package com.iss.dao.core;

import java.util.ResourceBundle;

public class DaoFactory
{
	/**
	 * ͨ���ӿڵ�class���ͣ��õ��ӿڵ�ʵ����
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
