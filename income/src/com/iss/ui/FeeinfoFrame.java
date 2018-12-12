package com.iss.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.iss.dao.FeeinfoDao;
import com.iss.dao.StuinfoDao;
import com.iss.dao.core.ConnectionManager;
import com.iss.dao.core.DaoFactory;
import com.iss.pojo.Stuinfo;
import com.iss.utils.PageDiv;

public class FeeinfoFrame extends JPanel
{
	
	private ArraysTableModel feelist=null;
	private JTable  jtable=new JTable();
	private JScrollPane jsp=new JScrollPane(jtable);
	
	private JPanel  top=new JPanel();
	private JLabel lno=new JLabel("学生学号：");
	private JTextField no=new JTextField(15);
	private JLabel show=new JLabel("回车搜索");
	
	private JButton all=new JButton("显示所有");
	
	
	   private JPanel bottom=new JPanel();
	   private JButton first=new JButton("第一页");
	   private JButton priv=new JButton("上一页");
	   private JButton next=new JButton("下一页");
	   private JButton last=new JButton("最后一页");
	   private JLabel  curpage=new JLabel("          2/");
	   private JLabel  totalpage=new JLabel("15");
	   private JLabel  totalrecod=new JLabel("      总共123条");
	
	 private MainFrame main;
	 
	 private int pageNo=1;
	 private int pageSize=25;
	 private PageDiv pd;
     private int querytype=1;//1查所有的，2表示只查指定学生的
	 private String []title= new String[]{"序号","收据号","金额","日期","记帐人","收款人","收款方式"};
	 private Stuinfo stu;
	 public void initJtable()
	 {
		 FeeinfoDao fd=(FeeinfoDao)DaoFactory.getDao(FeeinfoDao.class);
		 
		 try
		{
			if(querytype==1)
			 {
			   pd=fd.getAll(pageNo, pageSize);
			   feelist=new ArraysTableModel(pd.getList(),title);
				 
			 }else
			 {
				pd=fd.searchStu(pageNo, pageSize, stu.getId());
				feelist=new ArraysTableModel(pd.getList(),title);
			 }
			jtable.setModel(feelist);
			jtable.updateUI();
			
			
			 curpage.setText("          "+pd.getPageNo()+"/");
			   totalpage.setText(String.valueOf(pd.getTotalPage()));
			   totalrecod.setText("      总共"+pd.getTotalCount()+"条");   
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			ConnectionManager.getInstance().closeConnection();
		}
	 }
	public FeeinfoFrame(MainFrame main)
   {
	   super();
	   initJtable();
	   this.main=main;
	   this.setLayout(new BorderLayout());
	   
	   top.add(lno);
	   top.add(no);
	   show.setForeground(Color.red);
	   top.add(show);
	   top.add(all);
	   
	   
	   bottom.add(first);
	   bottom.add(priv);
	   bottom.add(next);
	   bottom.add(last);
	   
	   bottom.add(curpage);
	   bottom.add(totalpage);
	   bottom.add(totalrecod);
	   
	   this.add(top,BorderLayout.NORTH);
	   this.add(jsp,BorderLayout.CENTER);
	   this.add(bottom,BorderLayout.SOUTH);
	   initListener();
	   this.setVisible(true);
   }
	public void initListener()
	{
		first.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				pageNo=1;
				 initJtable();
			}
		});
		priv.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				 if(pageNo-1>0)
					 {pageNo=pageNo-1;
				 initJtable();}
			}
		});
		next.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
			     if(pageNo+1<=pd.getTotalPage())
			     {
			    	 pageNo=pageNo+1;
				 initJtable();
			     }
			}
		});
		last.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				pageNo=pd.getTotalPage();
				 initJtable();
			}
		});
		
		no.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				querytype=2;
				pageNo=1;
			    String sno=no.getText();
			    if(!"".equals(sno))
			    {
			    	StuinfoDao sd=(StuinfoDao)DaoFactory.getDao(StuinfoDao.class);
			    	FeeinfoDao fd=(FeeinfoDao)DaoFactory.getDao(FeeinfoDao.class);
			    	try
					{
						Stuinfo stu=sd.getBystuno(sno.trim());
						if(null!=stu)
						{
							FeeinfoFrame.this.stu=stu;
							initJtable();
						}
					} catch (SQLException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally
					{
						ConnectionManager.getInstance().closeConnection();
					}
			    }
				
			}
		});
		all.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				querytype=1;
				pageNo=1;
				initJtable();
				
			}
		});
		
	}
}
