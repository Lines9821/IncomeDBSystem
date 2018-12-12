package com.iss.dao;

import java.sql.SQLException;

import com.iss.pojo.Stuinfo;
import com.iss.utils.PageDiv;

public interface StuinfoDao
{
	/**
	 * 
	 * @param stu
	 * @return 返回新数据的id值
	 * @throws SQLException
	 */
	public int add(Stuinfo stu)throws SQLException;
	public void update(Stuinfo stu)throws SQLException;
	public void delete(int id)throws SQLException;
	public Stuinfo get(int id)throws SQLException;
	/**
	 * 计算下一个学号是几
	 * @param classid
	 * @return
	 * @throws SQLException
	 */
	public int nextNo(int classid)throws SQLException;
	/**
	 * 分页显示学生信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public PageDiv getByPage(int pageNo,int pageSize)throws SQLException;
	/**
	 * 找这个班级下的所有学生
	 * @param pageNo
	 * @param pageSize
	 * @param classId  班级id
	 * @return
	 * @throws SQLException
	 */
	public PageDiv getByClass(int pageNo,int pageSize,int classId)throws SQLException;
	/**
	 * 根据学生编号
	 * @param pageNo
	 * @param pageSize
	 * @param stuno
	 * @return
	 * @throws SQLException
	 */
	public PageDiv getByStuNo(int pageNo,int pageSize,String stuno)throws SQLException;
	/**
	 * 根据学生姓名找
	 * @param pageNo
	 * @param pageSize
	 * @param stuname
	 * @return
	 * @throws SQLException
	 */
	public PageDiv getByStuName(int pageNo,int pageSize,String stuname)throws SQLException;
	/**
	 * 根据学号取学生信息
	 * @param stuno
	 * @return
	 * @throws SQLException
	 */
	public Stuinfo getBystuno(String stuno)throws SQLException;
}
