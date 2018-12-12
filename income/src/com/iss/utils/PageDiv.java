package com.iss.utils;

import java.util.List;

public class PageDiv
{
   private int pageSize;//每页多少条
   private int pageNo;//当前第几页
   private int totalPage;//总共多少页
   public int totalCount;//总共多少条
   public List<Object[]> list;//第一页的数据
   public PageDiv(){}
   public PageDiv(int pageNo,int pageSize,int totalCount,List<Object[]> list)
   {
	   this.pageNo=pageNo;
	   this.pageSize=pageSize;
	   this.totalCount=totalCount;
	   this.list=list;
	   totalPage=(totalCount+pageSize-1)/pageSize;
	   
   }
public int getPageSize()
{
	return pageSize;
}
public void setPageSize(int pageSize)
{
	this.pageSize = pageSize;
}
public int getPageNo()
{
	return pageNo;
}
public void setPageNo(int pageNo)
{
	this.pageNo = pageNo;
}
public int getTotalPage()
{
	return totalPage;
}
public void setTotalPage(int totalPage)
{
	this.totalPage = totalPage;
}
public int getTotalCount()
{
	return totalCount;
}
public void setTotalCount(int totalCount)
{
	this.totalCount = totalCount;
}
public List<Object[]> getList()
{
	return list;
}
public void setList(List<Object[]> list)
{
	this.list = list;
}
   
}
