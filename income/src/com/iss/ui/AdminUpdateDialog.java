package com.iss.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.iss.dao.AdminDao;
import com.iss.dao.core.ConnectionManager;
import com.iss.dao.core.DaoFactory;
import com.iss.dao.core.Main;
import com.iss.pojo.Admin;
import com.iss.utils.MD5;
import com.iss.utils.Status;

public class AdminUpdateDialog extends JDialog
{
	AdminFrame adminFrame;
	private JLabel lname=new JLabel("用户名:");
	private JTextField name=new JTextField(12);
	private JLabel lpwd=new JLabel("密码:");
	private JTextField pwd=new JTextField(12);
	private JComboBox<Status> status=new JComboBox<Status>(new Status[]{new Status("正常",0),new Status("注销",1),new Status("锁定",2)});
	private JComboBox<String> posi=new JComboBox<String>(new String[]{"老板","财务","普通员工","其它"});
	private JComboBox<String> pur=new JComboBox<String>(new String[]{"管理员","财务","普通"});
	private JButton update=new JButton("修改");
	private Admin admin=null;
   public AdminUpdateDialog(final AdminFrame adminFrame,final Admin admin)
   {
	   super(adminFrame.getMain(),"修改管理员信息",true);
	   this.setSize(400,100);
	   this.setLocation(200,200);
	   this.admin=admin;
	   this.adminFrame=adminFrame;
	   this.setLayout(new FlowLayout());
	   this.add(lname);
	   this.add(name);
	   this.add(lpwd);
	   this.add(pwd);
	   this.add(posi);
	   this.add(pur);
	   this.add(status);
	   this.add(update);
	
	   //根据数据库中找出的Admin值 显示在界面上
	   name.setText(admin.getUname());
	   
	 switch (admin.getUstatus())
	 {
	case 0:
		status.setSelectedIndex(0);
		break;
	case 1:
		status.setSelectedIndex(1);
		break;
	case 2:
		status.setSelectedIndex(2);
		break;
	default:
		break;
	}
	 switch (admin.getUposition())
	 {
	case "老板":
		posi.setSelectedIndex(0);
		break;
	case "财务":
		posi.setSelectedIndex(1);
		break;
	case "普通员工":
		posi.setSelectedIndex(2);
		break;
	case "其它":
		posi.setSelectedIndex(3);
		break;
		
	default:
		break;
	}
	 
	 if(admin.getUpur().startsWith("100"))
	 {
		 pur.setSelectedIndex(0);
	 }
	 if(admin.getUpur().startsWith("010"))
	 {
		 pur.setSelectedIndex(1);
	 }
	 if(admin.getUpur().startsWith("001"))
	 {
		 pur.setSelectedIndex(2);
	 }
	   initListener();
	   this.setVisible(true);
	   
	
   }
  public void initListener()
  {
	
	   update.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			admin.setUname(name.getText());
			if(!"".equals(pwd))
			{
				admin.setUpwd(MD5.tomd5(pwd.getText()));
			}
			Status sta=(Status)status.getSelectedItem();
			admin.setUstatus(sta.getValue());
			admin.setUposition((String)posi.getSelectedItem());
			switch (pur.getSelectedIndex()) {
			case 0:
				admin.setUpur("1000000000");
				break;
			case 1:
				admin.setUpur("0100000000");
				break;
			case 2:
				admin.setUpur("0010000000");
				break;
			default:
				break;
			}
			AdminDao ad=(AdminDao)DaoFactory.getDao(AdminDao.class);
			try
			{
				ad.update(admin);

				adminFrame.initJTable();
				adminFrame.getJtable().setModel(adminFrame.getAdminlist());
				adminFrame.getJtable().updateUI();
				AdminUpdateDialog.this.dispose();
			} catch (SQLException e1)
			{
				e1.printStackTrace();
			}finally
			{
				ConnectionManager.getInstance().closeConnection();
			}
		}
	});
  }
  
}
