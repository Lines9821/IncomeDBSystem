package com.iss.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.iss.dao.AdminDao;
import com.iss.dao.core.ConnectionManager;
import com.iss.dao.core.DaoFactory;
import com.iss.pojo.Admin;
import com.iss.utils.MD5;
/**
 * 登陆界面
 * @author 刘慧涛
 */
public class Login extends JFrame
{
	private static final long serialVersionUID = -782484498495200691L;
	private JLabel ul=new JLabel("用户名:");
	private JLabel pl=new JLabel("密  码：");
	private JTextField uname=new JTextField(20);
	private JPasswordField upwd=new JPasswordField(20);
	private JButton  ok=new JButton("登陆");
	private JButton  cancel=new JButton("取消");
	private JLabel rl=new JLabel("验证码:");
	private JTextField rand=new JTextField(6);
	
	//图形验证码
	private RandImg img=new RandImg();

	public Login()
	{
		super("登陆界面");
		this.setSize(300, 260);
		this.setLocation(200,200);
		this.setResizable(false);
		//this.setUndecorated(true);
		//绝对布局
		this.setLayout(null);
		ul.setBounds(30, 30, 60, 30);
		uname.setBounds(100,30,120, 30);
		pl.setBounds(30, 70, 60,30);
		upwd.setBounds(100, 70, 120, 30);
		rl.setBounds(30, 120, 60, 30);
		rand.setBounds(100, 120, 40, 30);
		img.setBounds(150,120,120, 34);
		
		ok.setBounds(50,160, 80, 30);
		cancel.setBounds(160, 160, 80, 30);
		this.add(ul);
		this.add(uname);
		this.add(pl);
		this.add(upwd);
		this.add(rl);
		this.add(rand);
		this.add(img);
		this.add(ok);
		this.add(cancel);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//事件处理程序
		initListener();
	}
/**
 * 封装所有事件处理程序
 */
	public void initListener()
	{
		//登陆按
          ok.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				login();
			}
		});
		
	    rand.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				login();
				
			}
		});	
	}
	
	/**
	 * 登陆验证
	 */
	public void login()
	{
		String user=uname.getText();
		char [] pwd=upwd.getPassword();
		String ran=rand.getText();
		
		if(null!=user&&!"".equals(user)&&null!=pwd&&pwd.length>0&&null!=ran&&!"".equals(ran))
		{
			if(ran.equals(img.getRandcode()))
			{
				String spwd=new String(pwd);
				AdminDao ad=(AdminDao)DaoFactory.getDao(AdminDao.class);
				try
				{
					//数据库存的密码是密文，你输的明文，我们比对密文
					Admin admin=ad.checkLogin(user, MD5.tomd5(spwd));
					if(null!=admin)
					{
						//正确，弹出主窗口
						new MainFrame(admin);
						//把登陆窗口销毁
						Login.this.dispose();
					}else
					{
						JOptionPane.showMessageDialog(null, "验证用户名和密码不正确!");	
					}
				} catch (SQLException e1)
				{
					JOptionPane.showMessageDialog(null, "验证用户名和密码失败!");
					e1.printStackTrace();
				}finally
				{
					ConnectionManager.getInstance().closeConnection();
				}
				
			}else
			{
				JOptionPane.showMessageDialog(null,"验证码不对!");
			}
			
		}else
		{
			JOptionPane.showMessageDialog(null,"用户名密码不能为空");
		}
	}

	public static void main(String[] args)
	{
		new Login();

	}

}
