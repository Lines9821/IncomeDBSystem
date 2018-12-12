package com.iss.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iss.utils.ExecuteSQL;

public class InitDatabase extends JFrame
{
   private JPanel jp1=new JPanel();
   private JPanel jp2=new JPanel();
   private JPanel jp3=new JPanel();
   private JPanel jp4=new JPanel();
   private JPanel jp5=new JPanel();
   private JPanel jp6=new JPanel();
   private JLabel lhost=new JLabel("服务器：");
   private JTextField host=new JTextField(10);
   
   private JLabel luser=new JLabel("用户名：");
   private JTextField user=new JTextField(10);
   private JLabel lpwd=new JLabel("密 码：");
   private JTextField pwd=new JTextField(10);
   private JLabel lport=new JLabel("端 口：");
   private JTextField port=new JTextField(10);
   private JLabel ldb=new JLabel("数据库：");
   private JTextField db=new JTextField(6);
   private JComboBox<String> jcom=new JComboBox<String>(new String[]{"不带示列数据","带示列数据"});
   private JButton ok=new JButton("初始化数据库"); 
   
   public InitDatabase()
   {
	   super("初始化数据库信息");
	   this.setSize(300,300);
	   this.setLocation(200,200);
	   jp1.add(lhost);
	   jp1.add(host);
	   jp2.add(luser);
	   jp2.add(user);
	   jp3.add(lpwd);
	   jp3.add(pwd);
	   jp4.add(lport);
	   jp4.add(port);
	   jp5.add(ldb);
	   jp5.add(db);
	   jp5.add(jcom);
	   jp6.add(ok);
	   this.setLayout(new GridLayout(6, 1));
	   this.add(jp1);
	   this.add(jp2);
	   this.add(jp3);
	   this.add(jp4);
	   this.add(jp5);
	   this.add(jp6);
	   this.setResizable(false);
	   host.setText("localhost");
	   user.setText("root");
	   pwd.setText("mysql");
	   db.setText("income");
       port.setText("3306");
       
	   this.setVisible(true);
	   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   initListener();
   }
   public void initListener()
   {
	   ok.addActionListener(new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String dhost=host.getText();
			   String duser=user.getText();
			   String dport=port.getText();
			   String dpwd=pwd.getText();
			   int isempty=jcom.getSelectedIndex();
			   String ddb=db.getText();
			   ExecuteSQL exsql=new ExecuteSQL(dhost,duser,dpwd,dport,ddb);
			   try
			{
				if(exsql.executeBatch(isempty)&&exsql.executeFunction())
				   {
					/*   Properties p=new Properties();
					   p.load(InitDatabase.class.getResourceAsStream("/c3p0.properties"));
					   p.put("c3p0.jdbcUrl","jdbc:mysql://"+dhost+":"+dport+"/"+ddb);
					   p.put("c3p0.user",duser);
					   p.put("c3p0.password",dpwd);
					   p.store(new FileOutputStream("c3p0.properties"), "sss");*/
					
					   JOptionPane.showMessageDialog(null,"初始化成功!");
				       InitDatabase.this.dispose();
				   }
			}  catch (Exception xe)
			{
				// TODO Auto-generated catch block
				xe.printStackTrace();
			}
			
		}
	});
   }
   public static void main(String[] args)
{
	new InitDatabase();
}
}
