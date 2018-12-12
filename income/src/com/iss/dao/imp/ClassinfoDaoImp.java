package com.iss.dao.imp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.iss.dao.ClassinfoDao;
import com.iss.dao.core.DaoBase;
import com.iss.pojo.Classinfo;

public class ClassinfoDaoImp extends DaoBase implements ClassinfoDao
{
	@Override
	public void add(Classinfo info) throws SQLException
	{
		String sql="insert into classinfo(cno,cdesc,cteacher,ctutor) values(?,?,?,?)";
        run.update(getConnection(),sql,info.getCno(),info.getCdesc(),info.getCteacher(),info.getCtutor());
	}

	@Override
	public void update(Classinfo info) throws SQLException
	{
		String sql="update classinfo set cno=?,cdesc=?,cteacher=?,ctutor=? where id=?";
		run.update(getConnection(),sql,info.getCno(),info.getCdesc(),info.getCteacher(),info.getCtutor(),info.getId());

	}

	@Override
	public void delete(int id) throws SQLException
	{
		 run.update(getConnection(),"delete from classinfo where id=?",id);
	}

	@Override
	public Classinfo get(int id) throws SQLException
	{
		String sql="select * from classinfo where id=?";
		return run.query(getConnection(),sql,new BeanHandler<Classinfo>(Classinfo.class),id);
	}

	@Override
	public List<Classinfo> getAll() throws SQLException
	{
		return run.query(getConnection(),"select * from classinfo",new BeanListHandler<Classinfo>(Classinfo.class));
	}

	@Override
	public int newClassno(String year) throws SQLException
	{
            int result=1;
		String sql="select  substr(cno,5,2)+1 from classinfo where cno like ? order by cno desc limit 1";
		Object [] re=run.query(getConnection(),sql,new ArrayHandler(),year+"%");
		if(null!=re&&re.length>0)
		{
		Double num=(Double)re[0];
		result=num.intValue();
		}
		return result;
	}

}
