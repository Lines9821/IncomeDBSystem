package com.iss.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.iss.dao.ClassinfoDao;
import com.iss.dao.PriceinfoDao;
import com.iss.dao.StuinfoDao;
import com.iss.dao.core.ConnectionManager;
import com.iss.dao.core.DaoFactory;
import com.iss.pojo.Classinfo;
import com.iss.pojo.Priceinfo;
import com.iss.pojo.Stuinfo;
import com.iss.utils.PageDiv;

public class StuinfoFrame extends JPanel
{
	String []title=new String[]{"���", "�༶","���","����","�Ա�","ѧУ","�绰","����","QQ","״̬","ԭ��","�ּ�","Ƿ��"};
   private MainFrame main;
   private JPanel top=new JPanel();
   private DefaultComboBoxModel comModel=new DefaultComboBoxModel();
	private JComboBox<Classinfo> clazz=new JComboBox<Classinfo>(comModel);
   private JLabel lno=new JLabel("���");
   private JTextField no=new JTextField(6);
   
   private JLabel lname=new JLabel("����");
   private JTextField name=new JTextField(8);
   
   private JButton search=new JButton("Ƿ��ѧ��");
   private JLabel space=new JLabel("   ");
   private JButton add=new JButton("����");
   private JButton update=new JButton("�޸�");
   private JButton delete=new JButton("ɾ��");
   
   private JPanel bottom=new JPanel();
   private JButton first=new JButton("��һҳ");
   private JButton priv=new JButton("��һҳ");
   private JButton next=new JButton("��һҳ");
   private JButton last=new JButton("���һҳ");
   private JLabel  curpage=new JLabel("          2/");
   private JLabel  totalpage=new JLabel("15");
   private JLabel  totalrecod=new JLabel("      �ܹ�123��");
   private JLabel  titps=new JLabel("    ��ţ������س������� ˫��ѧ����Ϣ�ɷ�!");
   
   
   private ArraysTableModel stulist=null;
   private JTable jtable=new JTable();
   private JScrollPane jsp=new JScrollPane(jtable);
   private PageDiv pd=null;
   private  int pageNo=1;
   private int pageSize=25;
   private int queryType=0;//��ʾ����Ƿ�ѵ�ѧ��   1��ʾ����ָ���༶�ŵ�ѧ��    2  ָ��ѧ�ŵ�ѧ��   3  ָ��������
   /**
    * ��ָ���༶ѧ����Ϣ
    */
   public void queryByClass()
   {
	   Classinfo tem=(Classinfo)clazz.getSelectedItem();
       
		StuinfoDao sd=(StuinfoDao)DaoFactory.getDao(StuinfoDao.class);
		try
		{
			pd=sd.getByClass(pageNo, pageSize, tem.getId());
		
			intiPageLabels();
		    stulist=new ArraysTableModel(pd.getList(),title);
			jtable.setModel(stulist);
			jtable.updateUI();
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally
		{
			ConnectionManager.getInstance().closeConnection();
		}
   }
   public void queryByStuno()
   {
	   String stuno=no.getText();
	   if(null!=stuno&&!"".equals(stuno)&&stuno.matches("\\d+"))
	   {
			StuinfoDao sd=(StuinfoDao)DaoFactory.getDao(StuinfoDao.class);
			try
			{
				pd=sd.getByStuNo(pageNo, pageSize,stuno);
			
				intiPageLabels();
			    stulist=new ArraysTableModel(pd.getList(),title);
				jtable.setModel(stulist);
				jtable.updateUI();
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
   //����������ѧ����Ϣ
   public void queryByName()
   {
	   String sname=name.getText();
	   if(null!=sname&&!"".equals(sname))
	   {
			StuinfoDao sd=(StuinfoDao)DaoFactory.getDao(StuinfoDao.class);
			try
			{
				pd=sd.getByStuName(pageNo, pageSize,sname);
			
				intiPageLabels();
			    stulist=new ArraysTableModel(pd.getList(),title);
				jtable.setModel(stulist);
				jtable.updateUI();
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
   
   //��ʼ�������Ϣ,����Ƿ�ѵ�ѧ����Ϣ
   public void initJTable(int pageNo)
   {
	   StuinfoDao sd=(StuinfoDao)DaoFactory.getDao(StuinfoDao.class);
	   try
	{
		pd=sd.getByPage(pageNo,pageSize);
		//String []title=new String[]{"���", "�༶","���","����","�Ա�","����","ѧУ","��ҵʱ��","ѧ��","�绰","����","QQ","״̬","רҵ","ԭ��","�ּ�","Ƿ��"};
		
		stulist=new ArraysTableModel(pd.getList(),title);
	} catch (SQLException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	{
		ConnectionManager.getInstance().closeConnection();
	}
   }
   
   
   //��ʼ���༶��Ϣ
  	public void initClassinfo()
  	{
  		ClassinfoDao cd=(ClassinfoDao)DaoFactory.getDao(ClassinfoDao.class);
  		
  		try
  		{
  			List<Classinfo> list=cd.getAll();
  			for(Classinfo clazz:list)
  			{
  				comModel.addElement(clazz);
  			}
  		} catch (SQLException e)
  		{
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	}
   public StuinfoFrame(MainFrame main)
   {
	   super();
	   this.main=main;
	   initClassinfo();
	   this.setLayout(new BorderLayout());
	   initJTable(1);
	  jtable.setModel(stulist);
	  jtable.getColumn("���").setMaxWidth(30);
	  jtable.getTableHeader().setReorderingAllowed(false);
	  //jsp.setSize(700, 540);
	  jtable.setSize(700,540);
	   top.add(clazz);
	   top.add(lno);
	   top.add(no);
	   top.add(lname);
	   top.add(name);
	   top.add(search);
	   top.add(space);
	   //��ͨ�û�û����ɾ�ĵĹ���
	   if(!getMain().getAdmin().getUpur().startsWith("001"))
	   {
	   top.add(add);
	   top.add(update);
	   top.add(delete);
	   }
	   bottom.add(first);
	   bottom.add(priv);
	   bottom.add(next);
	   bottom.add(last);
	   bottom.add(curpage);
	   bottom.add(totalpage);
	   bottom.add(totalrecod);
	   
	   titps.setForeground(Color.red);
	   bottom.add(titps);
	   
	   intiPageLabels();
	 
	   this.add(top,BorderLayout.NORTH);
	   this.add(jsp,BorderLayout.CENTER);
	   this.add(bottom,BorderLayout.SOUTH);
	   initListener();
	   
   }
   public void intiPageLabels()
   {
	   curpage.setText("          "+pd.getPageNo()+"/");
	   totalpage.setText(String.valueOf(pd.getTotalPage()));
	   totalrecod.setText("      �ܹ�"+pd.getTotalCount()+"��");   
   }
   //������ҳ������ҵ��
   public void clickByPage()
   {
	   switch (queryType) {
		case 0:
			queryType=0;
			initJTable(pageNo);
			intiPageLabels();
			jtable.setModel(stulist);
			jtable.updateUI();				
		break;
		case 1:
				queryByClass();			
		break;
		case 2:
			   queryByStuno();
			break;
		case 3:
			  queryByName();
			break;

		default:
			break;
		}
   }
	//��ҳ��Ŧ���¼� 
	public void pageListener()
	{
		first.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				pageNo=1;
				 clickByPage();
				
			}
		});
		priv.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				  if(pageNo-1>0)pageNo=pageNo-1;
				  clickByPage();
				
			}
		});
		next.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(pd.getTotalPage()>=pageNo+1)
				{
					pageNo=pageNo+1;
					 clickByPage();
				}
				
			}
		});
		last.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				pageNo=pd.getTotalPage();
				 clickByPage();
			}
		});
	}

	
   //�¼�����
   public void initListener()
   {
		pageListener();

	   clazz.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			queryType=1;
	       queryByClass();
			
		}
	});
	   no.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			queryType=2;
			queryByStuno();
			
		}
	});
	   name.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			queryType=3;
			queryByName();
			
		}
	});
	   search.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			queryType=0;
			initJTable(1);
			intiPageLabels();
			jtable.setModel(stulist);
			jtable.updateUI();
			
		}
	});
	   add.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			 new StuaddDialog(StuinfoFrame.this);
		}
	});
	  update.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			int id=jtable.getSelectedRow();
			if(id>-1)
			{
			
				new StuupdateDialog(StuinfoFrame.this,((Long)jtable.getValueAt(id, 0)).intValue());
			}else
			{
				JOptionPane.showMessageDialog(null,"����ѡ��Ҫ�޸ĵ���!");
			}
			
		}
	});
	  delete.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			int id=jtable.getSelectedRow();
			if(id>-1)
			{
			     int stuid=((Long)jtable.getValueAt(id, 0)).intValue();
			        StuinfoDao sd=(StuinfoDao)DaoFactory.getDao(StuinfoDao.class);
					PriceinfoDao pd=(PriceinfoDao)DaoFactory.getDao(PriceinfoDao.class);
					try
					{
			            ConnectionManager.getInstance().startTransaction();
			            //�¼�
			            //step1:����ѧ����Ϣ
			            //step2:ȡ�¼ӵ�ѧ����Ϣ��id 
			            //step3:����priceinfo��Ҫ�β�ѧ����Ϣid 
						Priceinfo price=pd.getByStuid(stuid);
						pd.delete(price.getId());
						sd.delete(stuid);
						
						ConnectionManager.getInstance().commit();
						initJTable(1);
						intiPageLabels();
						jtable.setModel(stulist);
						jtable.updateUI();
						StuinfoFrame.this.repaint();
					} catch (SQLException ex)
					{
						ConnectionManager.getInstance().rollback();
						ex.printStackTrace();
					}finally
					{
						ConnectionManager.getInstance().closeConnection();
					}
			     
			     
			}else
			{
				JOptionPane.showMessageDialog(null,"����ѡ��Ҫɾ������!");
			}
		}
	});

	  //��ͨ�û����ܲ鿴������Ϣ
	  if(!StuinfoFrame.this.main.getAdmin().getUpur().startsWith("001"))
	  {
			  //˫������շ�
			  jtable.addMouseListener(new MouseAdapter()
			{
		
				@Override
				public void mouseClicked(MouseEvent e)
				{
					 if(e.getClickCount()==2)
					 {
						// main.tops.setSelectedIndex(1);
						 
						 int stuid=((Long)jtable.getValueAt(jtable.getSelectedRow(), 0)).intValue();
						 
						 Stuinfo stu=new Stuinfo();
						 stu.setId(stuid);
						 stu.setStuno((String)jtable.getValueAt(jtable.getSelectedRow(), 2));
						 stu.setSname((String)jtable.getValueAt(jtable.getSelectedRow(), 3));
						 stu.setClazzno((String)jtable.getValueAt(jtable.getSelectedRow(), 1));
						 
						 
						 
						 new FeeaddDialog(StuinfoFrame.this, StuinfoFrame.this.main.getAdmin(), stu);
					 }
				}
				  
			});
  }
   }
   //__________--------------------------------------------------------------
public MainFrame getMain()
{
	return main;
}
public void setMain(MainFrame main)
{
	this.main = main;
}
public ArraysTableModel getStulist()
{
	return stulist;
}
public void setStulist(ArraysTableModel stulist)
{
	this.stulist = stulist;
}
public JTable getJtable()
{
	return jtable;
}
public void setJtable(JTable jtable)
{
	this.jtable = jtable;
}
public PageDiv getPd()
{
	return pd;
}
public void setPd(PageDiv pd)
{
	this.pd = pd;
}
public int getPageNo()
{
	return pageNo;
}
public void setPageNo(int pageNo)
{
	this.pageNo = pageNo;
}
public int getQueryType()
{
	return queryType;
}
public void setQueryType(int queryType)
{
	this.queryType = queryType;
}
   
   
   
}
