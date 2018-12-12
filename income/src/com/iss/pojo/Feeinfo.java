package com.iss.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Feeinfo implements Serializable
{
	private int  id;
	private String feeno;
	private int stuid;
	private String feenote;
	private BigDecimal feermb;
	private Date feedate;
	 private int   adminid;
	private int feetype;
	private String feeby;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getFeeno()
	{
		return feeno;
	}
	public void setFeeno(String feeno)
	{
		this.feeno = feeno;
	}
	public int getStuid()
	{
		return stuid;
	}
	public void setStuid(int stuid)
	{
		this.stuid = stuid;
	}
	public String getFeenote()
	{
		return feenote;
	}
	public void setFeenote(String feenote)
	{
		this.feenote = feenote;
	}
	public BigDecimal getFeermb()
	{
		return feermb;
	}
	public void setFeermb(BigDecimal feermb)
	{
		this.feermb = feermb;
	}
	public Date getFeedate()
	{
		return feedate;
	}
	public void setFeedate(Date feedate)
	{
		this.feedate = feedate;
	}
	public int getAdminid()
	{
		return adminid;
	}
	public void setAdminid(int adminid)
	{
		this.adminid = adminid;
	}
	public int getFeetype()
	{
		return feetype;
	}
	public void setFeetype(int feetype)
	{
		this.feetype = feetype;
	}
	public String getFeeby()
	{
		return feeby;
	}
	public void setFeeby(String feeby)
	{
		this.feeby = feeby;
	}
	
}
