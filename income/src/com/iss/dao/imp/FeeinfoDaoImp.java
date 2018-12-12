package com.iss.dao.imp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.iss.dao.FeeinfoDao;
import com.iss.dao.core.DaoBase;
import com.iss.pojo.Feeinfo;
import com.iss.utils.PageDiv;

public class FeeinfoDaoImp extends DaoBase implements FeeinfoDao
{

	@Override
	public void add(Feeinfo fee) throws SQLException
	{
		String sql="insert into feeinfo(feeno,stuid,feenote,feermb,feedate,adminid,feetype,feeby) values(?,?,?,?,?,?,?,?)";
        run.update(getConnection(),sql,fee.getFeeno(),fee.getStuid(),fee.getFeenote(),fee.getFeermb(),fee.getFeedate(),fee.getAdminid(),fee.getFeetype(),fee.getFeeby());
	}

	@Override
	public void update(Feeinfo fee) throws SQLException
	{
		String sql="update feeinfo set feeno=?,stuid=?,feenote=?,feermb=?,feedate=?,adminid=?,feetype=?,feeby=? where id=?";
        run.update(getConnection(),sql,fee.getFeeno(),fee.getStuid(),fee.getFeenote(),fee.getFeermb(),fee.getFeedate(),fee.getAdminid(),fee.getFeetype(),fee.getFeeby(),fee.getId());

	}

	@Override
	public void delete(int id) throws SQLException
	{
		run.update(getConnection(),"delete from feeinfo where id=?",id);

	}

	@Override
	public Feeinfo get(int id) throws SQLException
	{
		
		return run.query(getConnection(), "select * from feeinfo where id=?",new BeanHandler<Feeinfo>(Feeinfo.class),id);
	}

	@Override
	public List<Object[]> getByStu(int stuId) throws SQLException
	{
		String sql="select f.feeno,f.feermb,f.feedate,a.uname,f.feeby,CASE f.feetype WHEN 0 THEN '现金' WHEN 1 THEN '刷卡' WHEN 2 THEN '转账' END as feetype from feeinfo f,admin a where f.adminid=a.id and f.stuid=?";
		return run.query(getConnection(),sql, new ArrayListHandler(),stuId);
	}

	@Override
	public PageDiv getAll(int pageNo,int pageSize) throws SQLException
	{
		List<Object[]> list=null;
		String sql="select f.id,f.feeno,f.feermb,f.feedate,a.uname,f.feeby,CASE f.feetype WHEN 0 THEN '现金' WHEN 1 THEN '刷卡' WHEN 2 THEN '转账' END as feetype from feeinfo f,admin a where f.adminid=a.id order by f.id desc limit ?,?";
		list=run.query(getConnection(),sql, new ArrayListHandler(),(pageNo-1)*pageSize,pageSize);
		
		int totalCount=((Long)run.query(getConnection(), "select count(id) from feeinfo",new ArrayHandler())[0]).intValue();
		
		return new PageDiv(pageNo, pageSize, totalCount, list);
	}

	@Override
	public PageDiv searchStu(int pageNo,int pageSize,int stuid) throws SQLException
	{
		List<Object[]> list=null;
		String sql="select f.id,f.feeno,f.feermb,f.feedate,a.uname,f.feeby,CASE f.feetype WHEN 0 THEN '现金' WHEN 1 THEN '刷卡' WHEN 2 THEN '转账' END as feetype from feeinfo f,admin a where f.adminid=a.id and f.stuid=? order by f.id desc limit ?,?";
		list=run.query(getConnection(),sql, new ArrayListHandler(),stuid,(pageNo-1)*pageSize,pageSize);
		
		int totalCount=((Long)run.query(getConnection(), "select count(id) from feeinfo where stuid=?",new ArrayHandler(),stuid)[0]).intValue();
		
		return new PageDiv(pageNo, pageSize, totalCount, list);
	}

}
