package com.iss.pojo;

import java.math.BigDecimal;
import java.sql.Date;

public class Stuinfo
{
	
	private int  id;
	private int classid;
	private String stuno;
	private String  sname;
	private String  ssex;
	private Date sbirthday;
	private String  school;
	 private Date  sgraduate;
	 private String  sgrade;
	 private String stel;
	 private String  smail;
	 private String sqq;
	 private int sstatus;
	 private String  smajor;
	 //----------------------------------------------
	 private String clazzno; //°à¼¶Ãû
	 private BigDecimal orgprice;
	 private BigDecimal newprice;
	 private BigDecimal lastfee;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getClassid()
	{
		return classid;
	}
	public void setClassid(int classid)
	{
		this.classid = classid;
	}
	public String getStuno()
	{
		return stuno;
	}
	public void setStuno(String stuno)
	{
		this.stuno = stuno;
	}
	public String getSname()
	{
		return sname;
	}
	public void setSname(String sname)
	{
		this.sname = sname;
	}
	public String getSsex()
	{
		return ssex;
	}
	public void setSsex(String ssex)
	{
		this.ssex = ssex;
	}
	public Date getSbirthday()
	{
		return sbirthday;
	}
	public void setSbirthday(Date sbirthday)
	{
		this.sbirthday = sbirthday;
	}
	public String getSchool()
	{
		return school;
	}
	public void setSchool(String school)
	{
		this.school = school;
	}
	public Date getSgraduate()
	{
		return sgraduate;
	}
	public void setSgraduate(Date sgraduate)
	{
		this.sgraduate = sgraduate;
	}
	public String getSgrade()
	{
		return sgrade;
	}
	public void setSgrade(String sgrade)
	{
		this.sgrade = sgrade;
	}
	public String getStel()
	{
		return stel;
	}
	public void setStel(String stel)
	{
		this.stel = stel;
	}
	public String getSmail()
	{
		return smail;
	}
	public void setSmail(String smail)
	{
		this.smail = smail;
	}
	public String getSqq()
	{
		return sqq;
	}
	public void setSqq(String sqq)
	{
		this.sqq = sqq;
	}
	public int getSstatus()
	{
		return sstatus;
	}
	public void setSstatus(int sstatus)
	{
		this.sstatus = sstatus;
	}
	public String getSmajor()
	{
		return smajor;
	}
	public void setSmajor(String smajor)
	{
		this.smajor = smajor;
	}
	public String getClazzno()
	{
		return clazzno;
	}
	public void setClazzno(String clazzno)
	{
		this.clazzno = clazzno;
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
	public BigDecimal getLastfee()
	{
		return lastfee;
	}
	public void setLastfee(BigDecimal lastfee)
	{
		this.lastfee = lastfee;
	}

	 

}
