package com.iss.dao.imp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.iss.dao.StuinfoDao;
import com.iss.dao.core.DaoBase;
import com.iss.pojo.Stuinfo;
import com.iss.utils.PageDiv;

public class StuinfoDaoImp extends DaoBase implements StuinfoDao
{

	@Override
	public int add(Stuinfo stu) throws SQLException
	{
		int re=0;
		String sql="insert into stuinfo(classid,stuno,sname,ssex,sbirthday,school,sgraduate,sgrade,stel,smail,sqq,sstatus,smajor) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    run.update(getConnection(),sql,stu.getClassid(),stu.getStuno(),stu.getSname(),stu.getSsex(),stu.getSbirthday(),stu.getSchool(),stu.getSgraduate(),stu.getSgrade(),stu.getStel(),stu.getSmail(),stu.getSqq(),stu.getSstatus(),stu.getSmajor());
	  
	  Object[] results=run.query(getConnection(),"select last_insert_id() from dual",new ArrayHandler());
	  if(null!=results&&results.length>0)
	  {
		  re=((Long)results[0]).intValue();
	  }
	    return re;
	}

	@Override
	public void update(Stuinfo stu) throws SQLException
	{
		String sql="update stuinfo set classid=?,stuno=?,sname=?,ssex=?,sbirthday=?,school=?,sgraduate=?,sgrade=?,stel=?,smail=?,sqq=?,sstatus=?,smajor=? where id=?";
	    run.update(getConnection(),sql,stu.getClassid(),stu.getStuno(),stu.getSname(),stu.getSsex(),stu.getSbirthday(),stu.getSchool(),stu.getSgraduate(),stu.getSgrade(),stu.getStel(),stu.getSmail(),stu.getSqq(),stu.getSstatus(),stu.getSmajor(),stu.getId());

	}

	@Override
	public void delete(int id) throws SQLException
	{
	    run.update(getConnection(),"delete from stuinfo where id=?",id);

	}

	@Override
	public Stuinfo get(int id) throws SQLException
	{
		// TODO Auto-generated method stub
		return run.query(getConnection(),"select * from stuinfo where id=?",new BeanHandler<Stuinfo>(Stuinfo.class),id);
	}

	@Override
	public PageDiv getByPage(int pageNo, int pageSize) throws SQLException
	{
        //String sql="select s.id,(select c.cno from classinfo c where s.classid=c.id) as clazzno,s.stuno,s.sname,s.ssex,s.sbirthday,s.school,s.sgraduate,s.sgrade,s.stel,s.smail,s.sqq,s. sstatus,s.smajor,p.orgprice,p.newprice,lastfee(s.id) as lastfee from stuinfo s,priceinfo p where s.id=p.stuid  and lastfee(s.id)>0 limit ?,?";
        String sql="select s.id,(select c.cno from classinfo c where s.classid=c.id) as clazzno,s.stuno,s.sname,s.ssex,s.school,s.stel,s.smail,s.sqq,s.sstatus,p.orgprice,p.newprice,lastfee(s.id) as lastfee from stuinfo s,priceinfo p where p.stuid=s.id  and lastfee(s.id)>0 order by lastfee(s.id) desc limit ?,?";
		String sqlcount="select count(s.id) from stuinfo s where lastfee(s.id)>0";
		
		int totalCount=((Long)run.query(getConnection(), sqlcount,new ArrayHandler())[0]).intValue();
		List<Object[]> list=run.query(getConnection(), sql,new ArrayListHandler(),(pageNo-1)*pageSize,pageSize);
		PageDiv pd=new PageDiv(pageNo,pageSize,totalCount,list);
        return pd;
	}

	@Override
	public PageDiv getByClass(int pageNo, int pageSize, int classId)
			throws SQLException
	{
		 
        String sql="select s.id,(select c.cno from classinfo c where s.classid=c.id) as clazzno,s.stuno,s.sname,s.ssex,s.school,s.stel,s.smail,s.sqq,s.sstatus,p.orgprice,p.newprice,lastfee(s.id) as lastfee from stuinfo s,priceinfo p where p.stuid=s.id  and s.classid=? limit ?,?";
		String sqlcount="select count(s.id) from stuinfo s where s.classid=?";
		
		int totalCount=((Long)run.query(getConnection(), sqlcount,new ArrayHandler(),classId)[0]).intValue();
		List<Object[]> list=run.query(getConnection(), sql,new ArrayListHandler(),classId,(pageNo-1)*pageSize,pageSize);
		PageDiv pd=new PageDiv(pageNo,pageSize,totalCount,list);
        return pd;
	}

	@Override
	public PageDiv getByStuNo(int pageNo, int pageSize, String stuno)
			throws SQLException
	{
	      String sql="select s.id,(select c.cno from classinfo c where s.classid=c.id) as clazzno,s.stuno,s.sname,s.ssex,s.school,s.stel,s.smail,s.sqq,s.sstatus,p.orgprice,p.newprice,lastfee(s.id) as lastfee from stuinfo s,priceinfo p where p.stuid=s.id  and s.stuno=? limit ?,?";
			String sqlcount="select count(s.id) from stuinfo s where s.stuno=?";
			
			int totalCount=((Long)run.query(getConnection(), sqlcount,new ArrayHandler(),stuno)[0]).intValue();
			List<Object[]> list=run.query(getConnection(), sql,new ArrayListHandler(),stuno,(pageNo-1)*pageSize,pageSize);
			PageDiv pd=new PageDiv(pageNo,pageSize,totalCount,list);
	        return pd;
	}

	@Override
	public PageDiv getByStuName(int pageNo, int pageSize, String stuname)
			throws SQLException
	{
		String sql="select s.id,(select c.cno from classinfo c where s.classid=c.id) as clazzno,s.stuno,s.sname,s.ssex,s.school,s.stel,s.smail,s.sqq,s.sstatus,p.orgprice,p.newprice,lastfee(s.id) as lastfee from stuinfo s,priceinfo p where p.stuid=s.id  and s.sname like ? limit ?,?";
		String sqlcount="select count(s.id) from stuinfo s where s.sname like ?";
		
		int totalCount=((Long)run.query(getConnection(), sqlcount,new ArrayHandler(),stuname)[0]).intValue();
		List<Object[]> list=run.query(getConnection(), sql,new ArrayListHandler(),stuname,(pageNo-1)*pageSize,pageSize);
		PageDiv pd=new PageDiv(pageNo,pageSize,totalCount,list);
        return pd;
	}
	@Override
	public int nextNo(int classid) throws SQLException
	{
		String sql="select stuno from stuinfo where classid=? order by stuno limit 1";
		int re=0;
		Object [] all=run.query(getConnection(),sql,new ArrayHandler(),classid);
		if(null!=all&&all.length>0)
		{
			String slastsno=(String)all[0];
			//20150108
			re=Integer.parseInt(slastsno.substring(6));
		}
		return re+1;
	}

	@Override
	public Stuinfo getBystuno(String stuno) throws SQLException
	{
		
		return run.query(getConnection(),"select * from stuinfo where stuno=? limit 1",new BeanHandler<Stuinfo>(Stuinfo.class),stuno);
	}
}
