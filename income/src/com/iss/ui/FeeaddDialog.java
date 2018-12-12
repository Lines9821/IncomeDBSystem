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
	private JLabel lno=new JLabel("�վݱ��:");
	private JTextField no=new JTextField(10);
	
	private JLabel  lnote=new JLabel("��ע:");
	private JTextField note=new JTextField(10);
	private JLabel  lrmb=new JLabel("���:");
	private JTextField rmb=new JTextField(8);
	
	private JComboBox<String> type=new JComboBox<String>(new String[]{"�ֽ�","ˢ��","ת��"});
	
	private JLabel  lfeeby=new JLabel("�տ���:");
	private JTextField feeby=new JTextField(6);
	
	private JButton ok=new JButton("ȷ������");
	
	private JLabel  admininfo=new JLabel();
    
	private ArraysTableModel  feelist;
	private JTable  jtable=new JTable();
	private JScrollPane  jsp=new JScrollPane(jtable);


	FeeaddDialog(final StuinfoFrame sf,final Admin admin, final Stuinfo stu)
	{
		super(sf.getMain(),"�շѴ���",true);
		this.setSize(500,360);
		this.setLocation(200,200);
		this.setResizable(false);
		//��ʼ�����
		FeeinfoDao fd=(FeeinfoDao)DaoFactory.getDao(FeeinfoDao.class);
		
        try
		{
			feelist=new ArraysTableModel(fd.getByStu(stu.getId()), new String[]{"�վݺ�","���","����","������","�տ���","�տʽ"});
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
		topinfo.setText("�༶��ţ�"+stu.getClazzno()+"   ѧ�ţ�"+stu.getStuno()+"   ����:"+stu.getSname());
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

		admininfo.setText("�����ˣ�"+admin.getUname());
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
						JOptionPane.showMessageDialog(null, "���ʳɹ���");
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
					 JOptionPane.showMessageDialog(null,"���ݲ��Ϸ�!!");
				 }
				 
				
				 
				
			}
		});
		
		this.setVisible(true);
		
	}
}
