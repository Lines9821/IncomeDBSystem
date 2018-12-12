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
 * 管理员界面
 * BorderLayout
 * 顶部是一个Jpannel  有增加管理员的界面
 * 中部是一个表格，用于显示所有管理员信息
 * @author 刘慧涛
 *
 */
public class AdminFrame extends JPanel
{
	private static final long serialVersionUID = 2243630199292599402L;
	private MainFrame main=null;//构造方法传来的主窗口对象
	private JPanel top=new JPanel();//顶部容器
	private JLabel lname=new JLabel("用户名:");
	private JTextField name=new JTextField(12);
	private JLabel lpwd=new JLabel("密码:");
	private JTextField pwd=new JTextField(12);
	private JComboBox<String> posi=new JComboBox<String>(new String[]{"老板","财务","普通员工","其它"});//职位
	private JComboBox<String> pur=new JComboBox<String>(new String[]{"管理员","财务","普通"});//权限
	private JButton add=new JButton("增加");
	
	private JPanel bottom=new JPanel();
	private JButton update=new JButton("修改");
	private JButton delete=new JButton("删除");
	

	//初始化表格
	private ArraysTableModel adminlist=null;
	private JTable jtable=new JTable();
	private JScrollPane jsp=new JScrollPane(jtable);
	/**
	 * 初始化TableMode，从数据库加载数据，
	 * 显示给表格
	 */
	public void initJTable()
	{
		AdminDao ad=(AdminDao)DaoFactory.getDao(AdminDao.class);
		try
		{
			//List<Admin> list=ad.getAll();
			//adminlist=new ListTableModel<Admin>(list, new String[]{"序号","姓名","密码","状态","职位","权限"});
			QueryRunner q=new QueryRunner();
			String sql="select id,uname,CASE ustatus WHEN 0 THEN '正常' WHEN 1 THEN '注销' WHEN 2 THEN '锁定' END  as ustatus,uposition,upur from admin";
			List<Object[]> list=q.query(ConnectionManager.getInstance().getConnection(), sql,new ArrayListHandler());
			adminlist=new ArraysTableModel(list, new String[]{"序号","姓名","状态","职位","权限"});
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
	   initJTable();//初始化表格数据，来自于数据库
	   jtable.setModel(adminlist);
	   //让表格的列不可称动
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
	   //对表格单元格进行重绘
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
			return new JLabel("管理员");
			else if(vv.startsWith("010"))
				return new JLabel("财务");
			else
				return new JLabel("普通");
		}
	 });
	   
	   initListener();//事件处理
   }
   

public void initListener()
   {
	//增加
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
					case "管理员":
						admin.setUpur("1000000000");
						break;
					case "财务":
						admin.setUpur("0100000000");
						break;
					case "普通":
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
	   //删除
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
		    	JOptionPane.showMessageDialog(null,"先选中要删除的表格");
		    }
		}
	});
    //修改
    update.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			
			updateSection();
		}
	});
    //修秘诀
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
//修改选中时的代码
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
	    	JOptionPane.showMessageDialog(null,"先选中要修改的表格");
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
