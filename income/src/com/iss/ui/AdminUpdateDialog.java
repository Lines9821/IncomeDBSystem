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
	private JLabel lname=new JLabel("�û���:");
	private JTextField name=new JTextField(12);
	private JLabel lpwd=new JLabel("����:");
	private JTextField pwd=new JTextField(12);
	private JComboBox<Status> status=new JComboBox<Status>(new Status[]{new Status("����",0),new Status("ע��",1),new Status("����",2)});
	private JComboBox<String> posi=new JComboBox<String>(new String[]{"�ϰ�","����","��ͨԱ��","����"});
	private JComboBox<String> pur=new JComboBox<String>(new String[]{"����Ա","����","��ͨ"});
	private JButton update=new JButton("�޸�");
	private Admin admin=null;
   public AdminUpdateDialog(final AdminFrame adminFrame,final Admin admin)
   {
	   super(adminFrame.getMain(),"�޸Ĺ���Ա��Ϣ",true);
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
	
	   //�������ݿ����ҳ���Adminֵ ��ʾ�ڽ�����
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
	case "�ϰ�":
		posi.setSelectedIndex(0);
		break;
	case "����":
		posi.setSelectedIndex(1);
		break;
	case "��ͨԱ��":
		posi.setSelectedIndex(2);
		break;
	case "����":
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
