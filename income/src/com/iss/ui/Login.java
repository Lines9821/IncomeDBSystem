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
 * ��½����
 * @author ������
 */
public class Login extends JFrame
{
	private static final long serialVersionUID = -782484498495200691L;
	private JLabel ul=new JLabel("�û���:");
	private JLabel pl=new JLabel("��  �룺");
	private JTextField uname=new JTextField(20);
	private JPasswordField upwd=new JPasswordField(20);
	private JButton  ok=new JButton("��½");
	private JButton  cancel=new JButton("ȡ��");
	private JLabel rl=new JLabel("��֤��:");
	private JTextField rand=new JTextField(6);
	
	//ͼ����֤��
	private RandImg img=new RandImg();

	public Login()
	{
		super("��½����");
		this.setSize(300, 260);
		this.setLocation(200,200);
		this.setResizable(false);
		//this.setUndecorated(true);
		//���Բ���
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
		//�¼��������
		initListener();
	}
/**
 * ��װ�����¼��������
 */
	public void initListener()
	{
		//��½��
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
	 * ��½��֤
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
					//���ݿ������������ģ���������ģ����Ǳȶ�����
					Admin admin=ad.checkLogin(user, MD5.tomd5(spwd));
					if(null!=admin)
					{
						//��ȷ������������
						new MainFrame(admin);
						//�ѵ�½��������
						Login.this.dispose();
					}else
					{
						JOptionPane.showMessageDialog(null, "��֤�û��������벻��ȷ!");	
					}
				} catch (SQLException e1)
				{
					JOptionPane.showMessageDialog(null, "��֤�û���������ʧ��!");
					e1.printStackTrace();
				}finally
				{
					ConnectionManager.getInstance().closeConnection();
				}
				
			}else
			{
				JOptionPane.showMessageDialog(null,"��֤�벻��!");
			}
			
		}else
		{
			JOptionPane.showMessageDialog(null,"�û������벻��Ϊ��");
		}
	}

	public static void main(String[] args)
	{
		new Login();

	}

}
