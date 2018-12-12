package com.iss.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.iss.pojo.Admin;
/**
 * 主窗口，
 * 一个选项目，第个选项对应一个界面
 * 每个界面都 是Jpanel,是我自己写的Jpanl
 * @author 刘慧涛
 *
 */
public class MainFrame extends JFrame
{
	private Admin admin;//记住哪用用户登陆了，用于提示欢迎信息
	//选项卡
	public JTabbedPane  tops=new JTabbedPane(JTabbedPane.TOP);
	
	//我们为每个界面写的窗口，都 是Jpannel，每个Jpanel都知道属于MainFrame
	private StuinfoFrame  stuinfo=null;//new StuinfoFrame(this);  //学生信息
	private ClassinfoFrame  classinfo=null;//new ClassinfoFrame(this);//班级信息
	private AdminFrame  admininfo=null;//new AdminFrame(this);//管理员信息
	private FeeinfoFrame  feeinfo=null;//new FeeinfoFrame(this);//收费明细
	//初始化界面对象
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
    * 构造方法
    * @param admin
    */
   public MainFrame(Admin admin)
   {
	   super("收费管理系统   欢迎您，"+admin.getUname());
	   this.admin=admin;
	   initFrames();
	   this.setSize(1000, 600);
	   this.setLocation(10,30);
	   
	   tops.add("学生信息",stuinfo);
	   if(admin.getUpur().startsWith("010")||admin.getUpur().startsWith("100"))
	   {
	   tops.add("收费信息",feeinfo);
	   tops.add("班级信息",classinfo);
	   }
	   if(admin.getUpur().startsWith("100"))
	   {
	   tops.add("用户信息",admininfo);
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
