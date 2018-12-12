package com.iss.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 用于初始化数据
 * @author 刘慧涛
 *
 */
public class ExecuteSQL
{
	List<String> sqllist=new ArrayList<String>();

	private String host;
	private String port;
	private String user;
	private String pwd;
	private String newdb;
	public ExecuteSQL(String host,String user,String pwd,String port,String newdb)
	{
		this.host=host;
		this.port=port;
		this.user=user;
		this.pwd=pwd;
		this.newdb=newdb;
	}
	
	static
	{
		    try
			{
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  

	}
	/**
	 * 读sql脚本文件，放入list
	 * @param index
	 * @throws Exception
	 */
	public void paraseSql(int index)throws Exception
	{
		InputStream is=null;
		if(index==0)
		{
		is=this.getClass().getResourceAsStream("/empty.sql");
		}else
		{
			is=this.getClass().getResourceAsStream("/datas.sql");
		}
		BufferedReader br=new BufferedReader(new InputStreamReader(is,"utf-8"));
		String str=null;
		StringBuilder sb=new StringBuilder();
		while(null!=(str=br.readLine()))
		{
			if("".equals(str.trim()))continue;
		   if(str.endsWith(";"))
		   {
			   sb.append(str.subSequence(0, str.length()));
			   sqllist.add(sb.toString());
			   sb.delete(0,sb.length());
		   }else
		   {
			   sb.append(str);
		   }
		}
	}
	/**
	 * 创建函数
	 * @return
	 * @throws Exception
	 */
	public boolean  executeFunction()throws Exception
	{
		boolean result=false;
		   
		
		    String sql="CREATE  FUNCTION `lastfee`(sid int) RETURNS decimal(10,2)"+
 "begin "+
 "DECLARE newp dec(10,2) default 0.0;"+
 "DECLARE allfee dec(10,2) default 0.0;"+
 "select newprice into newp from priceinfo where stuid=sid limit 1;"+
 "select sum(feermb) into allfee from feeinfo where stuid=sid limit 1;"+
 "if allfee is null then set allfee=0.0; end if;"+
" return newp-allfee;"+
"end";
		    Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+newdb, user,pwd);    
    try
	{
    	
		PreparedStatement ps=con.prepareStatement(sql);
		int re=ps.executeUpdate();
		 result=true;
	} catch (Exception e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	{
		con.close();
	}
    
    return result;
	}
	/**
	 * 初始化表和表结构
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public boolean executeBatch(int index)throws Exception
	{
		boolean res=false;
		paraseSql(index);//把文件sql语句放入list
		  Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/mysql", user,pwd); 
		    try
			{
				Statement stat=con.createStatement();
				stat.addBatch("CREATE DATABASE IF NOT EXISTS "+newdb);
				stat.addBatch("use "+newdb);
				for(String sql:sqllist)
				{
								 stat.addBatch(sql);
				}
				   int re[]=stat.executeBatch();
				   if(re.length==sqllist.size()+2)
				   {
								   res=true;
				   }
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally
			{
				con.close();
			}
		   return res;
		  
	}
   public static void main(String[] args)throws Exception
	{
	         //ExecuteSQL ex=new ExecuteSQL();
	        // ex.paraseSql();
	        // ex.executeBatch();
	        // ex.executeFunction();
	}
}
