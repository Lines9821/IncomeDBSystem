package com.iss.dao.imp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.iss.dao.AdminDao;
import com.iss.dao.core.DaoBase;
import com.iss.pojo.Admin;
import com.iss.utils.MD5;

public class AdminDaoImp extends DaoBase implements AdminDao 
{

	@Override
	public void add(Admin admin)throws SQLException
	{
		String sql="insert into admin(uname,upwd,ustatus,uposition,upur) values(?,?,?,?,?)";
		run.update(getConnection(), sql,
				admin.getUname(),
				admin.getUpwd(),
				admin.getUstatus(),
				admin.getUposition(),
				admin.getUpur());
		
	//	closeConnection();

	}

	@Override
	public void delete(int id)throws SQLException
	{
		run.update(getConnection(),"delete from admin where id=?",id);
		//closeConnection();
	}

	@Override
	public void update(Admin admin)throws SQLException
	{
		String sql="update admin set uname=?,upwd=?,ustatus=?,uposition=?,upur=? where id=?";
		run.update(getConnection(), sql,
				admin.getUname(),
				admin.getUpwd(),
				admin.getUstatus(),
				admin.getUposition(),
				admin.getUpur(),
				admin.getId());
		//closeConnection();
	}

	@Override
	public List<Admin> getAll()throws SQLException
	{
		// TODO Auto-generated method stub
		 List<Admin> list=run.query(getConnection(), "select * from admin",new BeanListHandler<Admin>(Admin.class));
		//closeConnection();
		 return list;
	}

	@Override
	public Admin checkLogin(String uname, String upwd)throws SQLException
	{
		String sql="select * from admin where uname=? and upwd=?";
		Admin admin=run.query(getConnection(),sql,new BeanHandler<Admin>(Admin.class),uname,upwd);
		//closeConnection();
		return admin;
	}

	@Override
	public Admin get(int id) throws SQLException
	{
		String sql="select * from admin where id=?";
		Admin admin=run.query(getConnection(), sql,new BeanHandler<Admin>(Admin.class),id);
		return admin;
	}

}
