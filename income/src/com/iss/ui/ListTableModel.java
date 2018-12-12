package com.iss.ui;

import java.lang.reflect.Field;
import java.util.List;

import javax.swing.table.DefaultTableModel;
/**
 * ��List<T>��ʼ��TableModel
 * @author Administrator
 * @param <T>
 */
public class ListTableModel<T> extends DefaultTableModel
{
	private static final long serialVersionUID = -2716551071855643621L;
	private List<T> list;//����
    private String [] title;//���ı���
    public ListTableModel(List<T> list,String[]title)
    {
    	this.list=list;
    	this.title=title;
    	this.setColumnIdentifiers(title);
    }
	@Override
	public int getRowCount()
	{
		// TODO Auto-generated method stub
		return null==list?0:list.size();
	}
	@Override
	public int getColumnCount()
	{
		// TODO Auto-generated method stub
		return title.length;
	}
	/**
	 * ��ʼ����Ԫ�������
	 */
	@Override
	public Object getValueAt(int row, int column)
	{
		Object obj=null;
	    T t=list.get(row);
	    //�õ�T���ֽ����Ӧ����
	    Class clazz=t.getClass();
	    //ȡ��T����������
	    Field [] all=clazz.getDeclaredFields();
	    //ȡ����Ӧ��һ������
	    Field f=all[column];
	    try
		{
	    	f.setAccessible(true);
	    	//ȡt�����f����ֵ���ظ�obj
	    	if(f.getName().equals("upwd"))
	    	{
	    		obj="***";
	    	}else
	    	{
			obj=f.get(t);
	    	}
		} catch (IllegalArgumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj;
	}
    
    
}
