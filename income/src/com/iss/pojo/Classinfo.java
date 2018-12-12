package com.iss.pojo;

import java.io.Serializable;

public class Classinfo implements Serializable
{
	private int id;
	private String  cno;
	private String cdesc;
	private String cteacher;
	private String ctutor;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getCno()
	{
		return cno;
	}
	public void setCno(String cno)
	{
		this.cno = cno;
	}
	public String getCdesc()
	{
		return cdesc;
	}
	public void setCdesc(String cdesc)
	{
		this.cdesc = cdesc;
	}
	public String getCteacher()
	{
		return cteacher;
	}
	public void setCteacher(String cteacher)
	{
		this.cteacher = cteacher;
	}
	public String getCtutor()
	{
		return ctutor;
	}
	public void setCtutor(String ctutor)
	{
		this.ctutor = ctutor;
	}
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return cno;
	}
	
	
}
