package com.iss.dao.core;

import java.lang.reflect.Field;

import com.iss.pojo.Admin;


public class Main
{ 
	public static void main(String[] args) throws Exception
	{

	   new DD();
	}
	
}
class TT
{
   public TT()
   {
	   System.out.println("sssss");
   }
}
class DD
{
  TT t=new TT();
  public DD()
  {
	  System.out.println("ddddd");
  }
}
