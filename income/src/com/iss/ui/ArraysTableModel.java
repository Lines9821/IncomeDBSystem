package com.iss.ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;
/**
 * ��List<Object[]>����ʼ�����
 * @author Administrator
 *
 */
public class ArraysTableModel extends DefaultTableModel
{
   private static final long serialVersionUID = 4201947975758080130L;
   private List<Object[]> list=null;
   private String []title=null;
   
   public ArraysTableModel(List<Object[]> list,String []title)
   {
	   super();
	   this.list=list;
	   this.title=title;
	   this.setColumnIdentifiers(title);
   }
	@Override
	public int getRowCount()
	{
		return null!=list?list.size():0;
	}
	@Override
	public int getColumnCount()
	{
		// TODO Auto-generated method stub
		return title.length;
	}
	
	//�ñ���ÿ����Ԫ�񲻿ɱ༭
	@Override
	public boolean isCellEditable(int row, int column)
	{
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Object getValueAt(int row, int column)
	{
		Object obj=null;
	    Object []rowdata=list.get(row);
	    obj=rowdata[column];
		return obj;
	}
   
}
