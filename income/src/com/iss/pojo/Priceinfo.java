package com.iss.pojo;

import java.math.BigDecimal;

public class Priceinfo
{
	private int id;
	private int stuid;
	private BigDecimal orgprice;
	private BigDecimal newprice;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getStuid()
	{
		return stuid;
	}
	public void setStuid(int stuid)
	{
		this.stuid = stuid;
	}
	public BigDecimal getOrgprice()
	{
		return orgprice;
	}
	public void setOrgprice(BigDecimal orgprice)
	{
		this.orgprice = orgprice;
	}
	public BigDecimal getNewprice()
	{
		return newprice;
	}
	public void setNewprice(BigDecimal newprice)
	{
		this.newprice = newprice;
	}
	
	

}
