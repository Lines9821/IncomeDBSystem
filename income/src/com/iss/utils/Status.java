package com.iss.utils;

public class Status
{
	private int value;
	private String name;
	
	public Status(String name,int value)
	{
		this.name=name;
		this.value=value;
	}
	public Status(){}
	public int getValue()
	{
		return value;
	}
	public void setValue(int value)
	{
		this.value = value;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return name;
	}
	

}
