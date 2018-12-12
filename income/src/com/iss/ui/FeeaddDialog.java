package com.iss.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.iss.dao.FeeinfoDao;
import com.iss.dao.core.ConnectionManager;
import com.iss.dao.core.DaoFactory;
import com.iss.pojo.Admin;
import com.iss.pojo.Feeinfo;
import com.iss.pojo.Stuinfo;

public class FeeaddDialog extends JDialog
{
	private JPanel top=new JPanel();
    private JLabel  topinfo=new JLabel();
	
    private Box box1=Box.createHorizontalBox();
    private Box box2=Box.createHorizontalBox();
    private Box box3=Box.createHorizontalBox();
    private Box box4=Box.createHorizontalBox();
    
    private Box cont=Box.createVerticalBox();
	
	private JPanel center=new JPanel();
	private JLabel lno=new JLabel("收据编号:");
	private JTextField no=new JTextField(10);
	
	private JLabel  lnote=new JLabel("备注:");
	private JTextField note=new JTextField(10);
	private JLabel  lrmb=new JLabel("金额:");
	private JTextField rmb=new JTextField(8);
	
	private JComboBox<String> type=new JComboBox<String>(new String[]{"现金","刷卡","转帐"});
	
	private JLabel  lfeeby=new JLabel("收款人:");
	private JTextField feeby=new JTextField(6);
	
	private JButton ok=new JButton("确定记帐");
	
	private JLabel  admininfo=new JLabel();
    
	private ArraysTableModel  feelist;
	private JTable  jtable=new JTable();
	private JScrollPane  jsp=new JScrollPane(jtable);


	FeeaddDialog(final StuinfoFrame sf,final Admin admin, final Stuinfo stu)
	{
		super(sf.getMain(),"收费窗口",true);
		this.setSize(500,360);
		this.setLocation(200,200);
		this.setResizable(false);
		//初始化表格
		FeeinfoDao fd=(FeeinfoDao)DaoFactory.getDao(FeeinfoDao.class);
		
        try
		{
			feelist=new ArraysTableModel(fd.getByStu(stu.getId()), new String[]{"收据号","金额","日期","记帐人","收款人","收款方式"});
			jtable.setModel(feelist);
		} catch (SQLException e2)
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}finally
		{
			ConnectionManager.getInstance().closeConnection();
		}
		
	
		topinfo.setForeground(Color.RED);
		topinfo.setText("班级编号："+stu.getClazzno()+"   学号："+stu.getStuno()+"   姓名:"+stu.getSname());
		top.add(topinfo);
		
		center.setLayout(new FlowLayout(15));
		box1.add(Box.createHorizontalStrut(30));
		box1.add(lno);
		box1.add(no);
		box1.add(Box.createHorizontalStrut(20));
		box1.add(lrmb);
		box1.add(rmb);
		box1.add(Box.createHorizontalStrut(20));
		box1.add(type);
		box1.add(Box.createHorizontalStrut(30));
		
		box2.add(Box.createHorizontalStrut(30));
		box2.add(lfeeby);
		box2.add(feeby);
		box2.add(lnote);
		box2.add(note);
		box2.add(admininfo);
		box2.add(ok);
		box2.add(Box.createHorizontalStrut(30));

		admininfo.setText("记帐人："+admin.getUname());
		feeby.setText(admin.getUname());
		
		cont.add(top);
		cont.add(box1);
		cont.add(Box.createVerticalStrut(15));
		cont.add(box2);
		cont.add(Box.createVerticalStrut(15));
		cont.add(box3);
		cont.add(Box.createVerticalStrut(15));
		cont.add(jsp);
		this.add(cont);
		ok.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				 Feeinfo fee=new Feeinfo();
				 fee.setStuid(stu.getId());
				 fee.setAdminid(admin.getId());
				 fee.setFeedate(new Date());
				 
				 String feeno=no.getText();
				 String rmbs=rmb.getText();
				 if(!"".equals(feeno)&&!"".equals(rmbs)&&rmbs.matches("\\d+"))
				 {
					 fee.setFeeno(feeno);
					 fee.setFeermb(new BigDecimal(rmbs+".00"));
					 fee.setFeenote(note.getText());
					 fee.setFeetype(type.getSelectedIndex());
					fee.setFeeby(feeby.getText());
					 
					 FeeinfoDao fd=(FeeinfoDao)DaoFactory.getDao(FeeinfoDao.class);
					 try
					{
						fd.add(fee);
						JOptionPane.showMessageDialog(null, "记帐成功！");
						sf.clickByPage();
				        FeeaddDialog.this.dispose();
						
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
					 JOptionPane.showMessageDialog(null,"数据不合法!!");
				 }
				 
				
				 
				
			}
		});
		
		this.setVisible(true);
		
	}
}
