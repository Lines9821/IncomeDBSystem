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
   private JPanel top=new JPanel();//�������
   private JLabel ldesc=new JLabel("����");
   private JTextField desc=new JTextField(12);
   private JLabel ltea=new JLabel("��ʦ");
   private JTextField tea=new JTextField(12);
   private JLabel ltor=new JLabel("��Ա");
   private JTextField tor=new JTextField(12);
   private JButton add=new JButton("����");
   
   private ArraysTableModel classlist=null;
   private JTable jtable =new JTable();
   private JScrollPane jsp=new JScrollPane(jtable);
   
   /**
    * ��ʼ�����ģ�Ͷ���
    */
   public void initJTable()
   {
	   QueryRunner run=new QueryRunner();
	  // String sql="select * from classinfo";
	   String sql="select c.*,(select count(classid) from stuinfo s where s.classid=c.id) as total from classinfo c";
	   try
	{
		List<Object[]> list=run.query(ConnectionManager.getInstance().getConnection(),sql,new ArrayListHandler());
	    String title[]=new String[]{"���","���","����","��ʦ","��Ա","����"};
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
    * ����
    */
   public void addClassinfo()
   {
	   String cno=Classutils.createClassNo();
	   String cdesc=desc.getText();
	   String ctea=tea.getText();
	   String ctor=tor.getText();
	   if("".equals(cdesc)||"".equals(ctea)||"".equals(ctor))
	   {
		   JOptionPane.showMessageDialog(null,"���ݲ���Ϊ��");
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
			JOptionPane.showMessageDialog(null,"���Ӱ༶�ɹ�!");
			desc.setText("");
			tea.setText("");
			tor.setText("");
			initJTable();
			jtable.setModel(classlist);
			jtable.updateUI();
		} catch (SQLException e)
		{
			JOptionPane.showConfirmDialog(null,"���Ӱ༶��Ϣʧ��");
			e.printStackTrace();
		}finally
		{
			ConnectionManager.getInstance().closeConnection();
		}
	   }
	   
	   
   }
   /**
    * �޸�
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
	    	  JOptionPane.showMessageDialog(null,"˫��Ҫ�޸ĵ���");
	      }
	   
		 
   }
   /**
    * �´�����
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
