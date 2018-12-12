package com.iss.pojo;

import java.io.Serializable;

public class Admin implements Serializable
{
	
	private int id;
	private String uname;
	private String upwd;
	private int ustatus;
	private String uposition;
	private String upur="10000000";

	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getUname()
	{
		return uname;
	}
	public void setUname(String uname)
	{
		this.uname = uname;
	}
	public String getUpwd()
	{
		return upwd;
	}
	public void setUpwd(String upwd)
	{
		this.upwd = upwd;
	}
	public int getUstatus()
	{
		return ustatus;
	}
	public void setUstatus(int ustatus)
	{
		this.ustatus = ustatus;
	}
	public String getUposition()
	{
		return uposition;
	}
	public void setUposition(String uposition)
	{
		this.uposition = uposition;
	}
	public String getUpur()
	{
		return upur;
	}
	public void setUpur(String upur)
	{
		this.upur = upur;
	}
	 
	 
}
