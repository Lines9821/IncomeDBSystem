package com.iss.utils;

import java.util.List;

public class PageDiv
{
   private int pageSize;//ÿҳ������
   private int pageNo;//��ǰ�ڼ�ҳ
   private int totalPage;//�ܹ�����ҳ
   public int totalCount;//�ܹ�������
   public List<Object[]> list;//��һҳ������
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
