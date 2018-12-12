package com.iss.dao.imp;

import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.iss.dao.PriceinfoDao;
import com.iss.dao.core.DaoBase;
import com.iss.pojo.Priceinfo;

public class PriceinfoDaoIm extends DaoBase implements PriceinfoDao
{

	@Override
	public void add(Priceinfo price) throws SQLException
	{
		String sql="insert into priceinfo(stuid,orgprice,newprice) values(?,?,?)";
		run.update(getConnection(),sql,price.getStuid(),price.getOrgprice(),price.getNewprice());

	}

	@Override
	public void update(Priceinfo price) throws SQLException
	{
		String sql="update priceinfo set stuid=?,orgprice=?,newprice=? where id=?";
		run.update(getConnection(),sql,price.getStuid(),price.getOrgprice(),price.getNewprice(),price.getId());

	}

	@Override
	public void delete(int id) throws SQLException
	{
		run.update(getConnection(),"delete from priceinfo where id=?",id);

	}

	@Override
	public Priceinfo get(int id) throws SQLException
	{
		
		return run.query(getConnection(),"select * from priceinfo where id=?",new BeanHandler<Priceinfo>(Priceinfo.class),id);
	}

	@Override
	public Priceinfo getByStuid(int id) throws SQLException
	{
		return run.query(getConnection(),"select * from priceinfo where stuid=? limit 1",new BeanHandler<Priceinfo>(Priceinfo.class),id);
	}

}
