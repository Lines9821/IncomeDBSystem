package com.iss.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import com.iss.dao.AdminDao;
import com.iss.dao.core.ConnectionManager;
import com.iss.dao.core.DaoFactory;
import com.iss.pojo.Admin;
import com.iss.utils.MD5;
/**
 * ����Ա����
 * BorderLayout
 * ������һ��Jpannel  �����ӹ���Ա�Ľ���
 * �в���һ�����������ʾ���й���Ա��Ϣ
 * @author ������
 *
 */
public class AdminFrame extends JPanel
{
	private static final long serialVersionUID = 2243630199292599402L;
	private MainFrame main=null;//���췽�������������ڶ���
	private JPanel top=new JPanel();//��������
	private JLabel lname=new JLabel("�û���:");
	private JTextField name=new JTextField(12);
	private JLabel lpwd=new JLabel("����:");
	private JTextField pwd=new JTextField(12);
	private JComboBox<String> posi=new JComboBox<String>(new String[]{"�ϰ�","����","��ͨԱ��","����"});//ְλ
	private JComboBox<String> pur=new JComboBox<String>(new String[]{"����Ա","����","��ͨ"});//Ȩ��
	private JButton add=new JButton("����");
	
	private JPanel bottom=new JPanel();
	private JButton update=new JButton("�޸�");
	private JButton delete=new JButton("ɾ��");
	

	//��ʼ�����
	private ArraysTableModel adminlist=null;
	private JTable jtable=new JTable();
	private JScrollPane jsp=new JScrollPane(jtable);
	/**
	 * ��ʼ��TableMode�������ݿ�������ݣ�
	 * ��ʾ�����
	 */
	public void initJTable()
	{
		AdminDao ad=(AdminDao)DaoFactory.getDao(AdminDao.class);
		try
		{
			//List<Admin> list=ad.getAll();
			//adminlist=new ListTableModel<Admin>(list, new String[]{"���","����","����","״̬","ְλ","Ȩ��"});
			QueryRunner q=new QueryRunner();
			String sql="select id,uname,CASE ustatus WHEN 0 THEN '����' WHEN 1 THEN 'ע��' WHEN 2 THEN '����' END  as ustatus,uposition,upur from admin";
			List<Object[]> list=q.query(ConnectionManager.getInstance().getConnection(), sql,new ArrayListHandler());
			adminlist=new ArraysTableModel(list, new String[]{"���","����","״̬","ְλ","Ȩ��"});
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			ConnectionManager.getInstance().closeConnection();
		}
	}
  
   public AdminFrame(MainFrame main)
   {
	   super();
	   this.main=main;
	   this.setLayout(new BorderLayout());
	   initJTable();//��ʼ��������ݣ����������ݿ�
	   jtable.setModel(adminlist);
	   //�ñ����в��ɳƶ�
	   jtable.getTableHeader().setReorderingAllowed(false);
	   top.add(lname);
	   top.add(name);
	   top.add(lpwd);
	   top.add(pwd);
	   top.add(posi);
	   top.add(pur);
	   top.add(add);
	   
	   bottom.add(update);
	   bottom.add(delete);
	   
	   this.add(jsp,"Center");
	   
	   this.add(top,BorderLayout.NORTH);
	   this.add(bottom,BorderLayout.SOUTH);
	   //�Ա��Ԫ������ػ�
	   TableColumnModel tcm=jtable.getColumnModel();
	   TableColumn tcom= tcm.getColumn(4);
	   tcom.setCellRenderer(new TableCellRenderer()
	   {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column)
		{
			String vv=value.toString();
			if(vv.startsWith("100"))
			return new JLabel("����Ա");
			else if(vv.startsWith("010"))
				return new JLabel("����");
			else
				return new JLabel("��ͨ");
		}
	 });
	   
	   initListener();//�¼�����
   }
   

public void initListener()
   {
	//����
	   add.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			 String uname=name.getText();
			 String upwd=pwd.getText();
			 String pos=(String)posi.getSelectedItem();
			 String purview=(String)pur.getSelectedItem();
			 if(!"".equals(uname)&&!"".equals(upwd))
			 {
					 Admin admin=new Admin();
					 admin.setUname(uname);
					 admin.setUpwd(MD5.tomd5(upwd));
					 admin.setUposition(pos);
					 admin.setUstatus(0);
					 //byte int char shor 
					 switch (purview) 
					 {
					case "����Ա":
						admin.setUpur("1000000000");
						break;
					case "����":
						admin.setUpur("0100000000");
						break;
					case "��ͨ":
						admin.setUpur("0010000000");
						break;
					default:
						break;
					}
					AdminDao ad=(AdminDao)DaoFactory.getDao(AdminDao.class);
					try
					{
						ad.add(admin);
						initJTable();
						jtable.setModel(adminlist);
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
	});
	   //ɾ��
    delete.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
		    int re=jtable.getSelectedRow();
		    
		    if(re>=0)
		    {
		    	Long id=(Long)jtable.getValueAt(re,0);
		    	
		    	AdminDao ad=(AdminDao)DaoFactory.getDao(AdminDao.class);
				try
				{
					ad.delete(id.intValue());
					initJTable();
					jtable.setModel(adminlist);
					jtable.updateUI();
		
				} catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally
				{
					ConnectionManager.getInstance().closeConnection();
				}
		    }else
		    {
		    	JOptionPane.showMessageDialog(null,"��ѡ��Ҫɾ���ı��");
		    }
		}
	});
    //�޸�
    update.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			
			updateSection();
		}
	});
    //���ؾ�
      jtable.addMouseListener(new MouseAdapter()
	{
		
		@Override
		public void mouseReleased(MouseEvent e)
		{
			if(e.getClickCount()==2)
			{
				updateSection();
			}
			
		}
		
	});
   }
//�޸�ѡ��ʱ�Ĵ���
public void updateSection()
{
	 int re=jtable.getSelectedRow();
	    
	    if(re>=0)
	    {
	    	Long id=(Long)jtable.getValueAt(re,0);
	    	
	    	AdminDao ad=(AdminDao)DaoFactory.getDao(AdminDao.class);
			try
			{
				 Admin admin=ad.get(id.intValue());
				 
				 new AdminUpdateDialog(AdminFrame.this, admin);
	
			} catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally
			{
				ConnectionManager.getInstance().closeConnection();
			}
	    }else
	    {
	    	JOptionPane.showMessageDialog(null,"��ѡ��Ҫ�޸ĵı��");
	    }
}
//----------------------------------------------------------
public MainFrame getMain()
{
	return main;
}

public ArraysTableModel getAdminlist()
{
	return adminlist;
}

public void setAdminlist(ArraysTableModel adminlist)
{
	this.adminlist = adminlist;
}

public JTable getJtable()
{
	return jtable;
}

public void setJtable(JTable jtable)
{
	this.jtable = jtable;
}

   
}
