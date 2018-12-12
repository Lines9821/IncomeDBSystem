package com.iss.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import com.iss.dao.ClassinfoDao;
import com.iss.dao.core.ConnectionManager;
import com.iss.dao.core.DaoFactory;
import com.iss.pojo.Classinfo;
import com.iss.utils.Classutils;

public class ClassinfoFrame extends JPanel
{
   private MainFrame main;
   private JPanel top=new JPanel();//顶层面板
   private JLabel ldesc=new JLabel("描述");
   private JTextField desc=new JTextField(12);
   private JLabel ltea=new JLabel("老师");
   private JTextField tea=new JTextField(12);
   private JLabel ltor=new JLabel("导员");
   private JTextField tor=new JTextField(12);
   private JButton add=new JButton("增加");
   
   private ArraysTableModel classlist=null;
   private JTable jtable =new JTable();
   private JScrollPane jsp=new JScrollPane(jtable);
   
   /**
    * 初始化表格模型对象
    */
   public void initJTable()
   {
	   QueryRunner run=new QueryRunner();
	  // String sql="select * from classinfo";
	   String sql="select c.*,(select count(classid) from stuinfo s where s.classid=c.id) as total from classinfo c";
	   try
	{
		List<Object[]> list=run.query(ConnectionManager.getInstance().getConnection(),sql,new ArrayListHandler());
	    String title[]=new String[]{"序号","编号","描述","老师","导员","人数"};
	    classlist=new ArraysTableModel(list, title);
	} catch (SQLException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	{
		ConnectionManager.getInstance().closeConnection();
	}
   }
   
   public ClassinfoFrame(MainFrame main)
   {
	   super();
	   this.main=main;
	   this.setLayout(new BorderLayout());
	   initJTable();
	   jtable.getTableHeader().setReorderingAllowed(false);
	   jtable.setModel(classlist);
	   top.add(ldesc);
	   top.add(desc);
	   top.add(ltea);
	   top.add(tea);
	   top.add(ltor);
	   top.add(tor);
	   top.add(add);

	   this.add(top,BorderLayout.NORTH);
	   this.add(jsp,BorderLayout.CENTER);
	   initListener();
	   
   }
   /**
    * 增加
    */
   public void addClassinfo()
   {
	   String cno=Classutils.createClassNo();
	   String cdesc=desc.getText();
	   String ctea=tea.getText();
	   String ctor=tor.getText();
	   if("".equals(cdesc)||"".equals(ctea)||"".equals(ctor))
	   {
		   JOptionPane.showMessageDialog(null,"内容不能为空");
	   }else
	   {
		   Classinfo info=new Classinfo();
		   info.setCno(cno);
		   info.setCdesc(cdesc);
		   info.setCteacher(ctea);
		   info.setCtutor(ctor);
		   
		   ClassinfoDao cd=(ClassinfoDao)DaoFactory.getDao(ClassinfoDao.class);
		   try
		{
			cd.add(info);
			JOptionPane.showMessageDialog(null,"增加班级成功!");
			desc.setText("");
			tea.setText("");
			tor.setText("");
			initJTable();
			jtable.setModel(classlist);
			jtable.updateUI();
		} catch (SQLException e)
		{
			JOptionPane.showConfirmDialog(null,"增加班级信息失败");
			e.printStackTrace();
		}finally
		{
			ConnectionManager.getInstance().closeConnection();
		}
	   }
	   
	   
   }
   /**
    * 修改
    */
   public void updateClick()
   {
	      int selectrow=jtable.getSelectedRow();
	      if(selectrow>-1)
	      {
	    	  Long rowid=(Long)jtable.getValueAt(selectrow, 0);
	    	  ClassinfoDao cd=(ClassinfoDao)DaoFactory.getDao(ClassinfoDao.class);
	    	  try
			{
				   Classinfo info=cd.get(rowid.intValue());
				  new ClassinfoUPdateDialog(ClassinfoFrame.this,info);
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally
			{
				ConnectionManager.getInstance().closeConnection();
			}
	      }else
	      {
	    	  JOptionPane.showMessageDialog(null,"双击要修改的行");
	      }
	   
		 
   }
   /**
    * 事处处理
    */
   public void initListener()
   {
	   add.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			 addClassinfo();
			
		}
	});  
	   tor.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			 addClassinfo();
			
		}
	});
	   
	   jtable.addMouseListener(new MouseAdapter()
	{

		@Override
		public void mouseClicked(MouseEvent e)
		{
		   if(e.getClickCount()==2)
		   {
			   updateClick();
		   }
		}
		   
	});
   }
   //-----------------------------------------------

public MainFrame getMain()
{
	return main;
}

public void setMain(MainFrame main)
{
	this.main = main;
}

public JTable getJtable()
{
	return jtable;
}

public void setJtable(JTable jtable)
{
	this.jtable = jtable;
}

public ArraysTableModel getClasslist()
{
	return classlist;
}

public void setClasslist(ArraysTableModel classlist)
{
	this.classlist = classlist;
}
   
   
}
