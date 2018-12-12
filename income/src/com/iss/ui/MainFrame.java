package com.iss.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.iss.pojo.Admin;
/**
 * �����ڣ�
 * һ��ѡ��Ŀ���ڸ�ѡ���Ӧһ������
 * ÿ�����涼 ��Jpanel,�����Լ�д��Jpanl
 * @author ������
 *
 */
public class MainFrame extends JFrame
{
	private Admin admin;//��ס�����û���½�ˣ�������ʾ��ӭ��Ϣ
	//ѡ�
	public JTabbedPane  tops=new JTabbedPane(JTabbedPane.TOP);
	
	//����Ϊÿ������д�Ĵ��ڣ��� ��Jpannel��ÿ��Jpanel��֪������MainFrame
	private StuinfoFrame  stuinfo=null;//new StuinfoFrame(this);  //ѧ����Ϣ
	private ClassinfoFrame  classinfo=null;//new ClassinfoFrame(this);//�༶��Ϣ
	private AdminFrame  admininfo=null;//new AdminFrame(this);//����Ա��Ϣ
	private FeeinfoFrame  feeinfo=null;//new FeeinfoFrame(this);//�շ���ϸ
	//��ʼ���������
	public void initFrames()
	{
		stuinfo=new StuinfoFrame(this);
		 if(admin.getUpur().startsWith("010")||admin.getUpur().startsWith("100"))
		   {
		classinfo=new ClassinfoFrame(this);
		feeinfo=new FeeinfoFrame(this);
		   }
		 if(admin.getUpur().startsWith("100"))
		   {
		admininfo=new AdminFrame(this);
		   }
		
	}
   /**
    * ���췽��
    * @param admin
    */
   public MainFrame(Admin admin)
   {
	   super("�շѹ���ϵͳ   ��ӭ����"+admin.getUname());
	   this.admin=admin;
	   initFrames();
	   this.setSize(1000, 600);
	   this.setLocation(10,30);
	   
	   tops.add("ѧ����Ϣ",stuinfo);
	   if(admin.getUpur().startsWith("010")||admin.getUpur().startsWith("100"))
	   {
	   tops.add("�շ���Ϣ",feeinfo);
	   tops.add("�༶��Ϣ",classinfo);
	   }
	   if(admin.getUpur().startsWith("100"))
	   {
	   tops.add("�û���Ϣ",admininfo);
	   }
	   this.add(tops);
	   this.setVisible(true);
	   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
/************************************getter and seter***********************************************/
public Admin getAdmin()
{
	return admin;
}
public void setAdmin(Admin admin)
{
	this.admin = admin;
}

}
