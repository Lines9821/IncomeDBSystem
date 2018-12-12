package com.iss.ui;

import java.lang.reflect.Field;
import java.util.List;

import javax.swing.table.DefaultTableModel;
/**
 * 用List<T>初始化TableModel
 * @author Administrator
 * @param <T>
 */
public class ListTableModel<T> extends DefaultTableModel
{
	private static final long serialVersionUID = -2716551071855643621L;
	private List<T> list;//数据
    private String [] title;//表格的标题
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
	 * 初始化单元格的数据
	 */
	@Override
	public Object getValueAt(int row, int column)
	{
		Object obj=null;
	    T t=list.get(row);
	    //得到T的字节码对应对象
	    Class clazz=t.getClass();
	    //取出T的所有属性
	    Field [] all=clazz.getDeclaredFields();
	    //取出对应的一个属性
	    Field f=all[column];
	    try
		{
	    	f.setAccessible(true);
	    	//取t对象的f属性值返回给obj
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
