package com.iss.dao;

import java.sql.SQLException;

import com.iss.pojo.Stuinfo;
import com.iss.utils.PageDiv;

public interface StuinfoDao
{
	/**
	 * 
	 * @param stu
	 * @return ���������ݵ�idֵ
	 * @throws SQLException
	 */
	public int add(Stuinfo stu)throws SQLException;
	public void update(Stuinfo stu)throws SQLException;
	public void delete(int id)throws SQLException;
	public Stuinfo get(int id)throws SQLException;
	/**
	 * ������һ��ѧ���Ǽ�
	 * @param classid
	 * @return
	 * @throws SQLException
	 */
	public int nextNo(int classid)throws SQLException;
	/**
	 * ��ҳ��ʾѧ����Ϣ
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public PageDiv getByPage(int pageNo,int pageSize)throws SQLException;
	/**
	 * ������༶�µ�����ѧ��
	 * @param pageNo
	 * @param pageSize
	 * @param classId  �༶id
	 * @return
	 * @throws SQLException
	 */
	public PageDiv getByClass(int pageNo,int pageSize,int classId)throws SQLException;
	/**
	 * ����ѧ�����
	 * @param pageNo
	 * @param pageSize
	 * @param stuno
	 * @return
	 * @throws SQLException
	 */
	public PageDiv getByStuNo(int pageNo,int pageSize,String stuno)throws SQLException;
	/**
	 * ����ѧ��������
	 * @param pageNo
	 * @param pageSize
	 * @param stuname
	 * @return
	 * @throws SQLException
	 */
	public PageDiv getByStuName(int pageNo,int pageSize,String stuname)throws SQLException;
	/**
	 * ����ѧ��ȡѧ����Ϣ
	 * @param stuno
	 * @return
	 * @throws SQLException
	 */
	public Stuinfo getBystuno(String stuno)throws SQLException;
}
