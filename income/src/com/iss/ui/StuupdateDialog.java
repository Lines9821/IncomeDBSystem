package com.iss.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.iss.dao.ClassinfoDao;
import com.iss.dao.PriceinfoDao;
import com.iss.dao.StuinfoDao;
import com.iss.dao.core.ConnectionManager;
import com.iss.dao.core.DaoFactory;
import com.iss.pojo.Classinfo;
import com.iss.pojo.Priceinfo;
import com.iss.pojo.Stuinfo;

public class StuupdateDialog extends JDialog
{
	public static String []days=new String[31];
	public static String[] years=new String[31];
	static
	{
		for(int i=1;i<32;i++)
		{
			days[i-1]=String.valueOf(i);
		}
		for(int i=1980;i<2011;i++)
		{
			years[i-1980]=String.valueOf(i);
		}
	}
	
	private StuinfoFrame sf=null;
	private Box main=Box.createVerticalBox();
	private Box box1=Box.createHorizontalBox();
	private Box box2=Box.createHorizontalBox();
	private Box box3=Box.createHorizontalBox();
	private Box box4=Box.createHorizontalBox();
	private Box box5=Box.createHorizontalBox();
	private Box box6=Box.createHorizontalBox();
	private Box box7=Box.createHorizontalBox();
	private Box box8=Box.createHorizontalBox();
	private Box box9=Box.createHorizontalBox();
	private Box box10=Box.createHorizontalBox();
	private Box box11=Box.createHorizontalBox();
	private Box box12=Box.createHorizontalBox();
	
	private JLabel lclazz=new JLabel("�༶:");
	private DefaultComboBoxModel comModel=new DefaultComboBoxModel();
	private JComboBox<Classinfo> clazz=new JComboBox<Classinfo>(comModel);
	
	private JLabel lname=new JLabel("������");
	private JTextField name=new JTextField(10);
	private JComboBox<String> sex=new JComboBox<String>(new String[]{"M","F"});
	
	private JLabel lbir=new JLabel("���գ�");
	private JComboBox<String> year=new JComboBox<String>(years);
	private JComboBox<String> mon=new JComboBox<String>(new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"});
	private JComboBox<String> day=new JComboBox<String>(days);
	
	private JLabel lschool=new JLabel("ѧУ��");
	private JTextField school=new JTextField(10);
	
	private JLabel biye=new JLabel("��ҵʱ��:");
	private JComboBox<String> year1=new JComboBox<String>(new String[]{"2015","2016","2017","2018","2019","2020"});
	private JComboBox<String> mon1=new JComboBox<String>(new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"});
	
	private JComboBox<String> xueli=new JComboBox<String>(new String[]{"˶ʿ","����","ר��","����"});
	
	private JLabel ltel=new JLabel("�绰��");
	private JTextField tel=new JTextField(10);
	
	private JLabel lemail=new JLabel("���䣺");
	private JTextField email=new JTextField(10);
	
	private JLabel lqq=new JLabel("QQ��");
	private JTextField qq=new JTextField(10);
	
	private JComboBox<String> status=new JComboBox<String>(new String[]{"��У","��ҵ","��ѧ"});
	
	private JLabel lzhuanye=new JLabel("רҵ��");
	private JTextField zhuanye=new JTextField(10);
	
	private JLabel lorgprice=new JLabel("ԭ�ۣ�");
	private JTextField orgprice=new JTextField(10);
	
	private JLabel lnewprice=new JLabel("�ּۣ�");
	private JTextField newprice=new JTextField(10);
	private JButton update=new JButton("�޸�ѧ��");
	
	private  List<Classinfo> classlist;
	private int stuId;//ѧ����Ϣ
	private Stuinfo stu;   //���ݿ���Ҫ�޸�ѧ������Ϣ
	private Priceinfo price;//��Ӧ�ļ۸�
	//�����ݿ����Ҫ�޸ĵ�ѧ����Ϣ
	public void initStuinfo()
	{
         StuinfoDao sd=(StuinfoDao)DaoFactory.getDao(StuinfoDao.class);
         PriceinfoDao pd=(PriceinfoDao)DaoFactory.getDao(PriceinfoDao.class);
         try
		{
			stu=sd.get(stuId);
			 price=pd.getByStuid(stuId);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			ConnectionManager.getInstance().closeConnection();
		}
  
	}
	//�ڽ����Ͼ���ʾ���ݿ��е�����
	public void initUI()
	{
		//��ʼ���༶
		for(int i=0;i<classlist.size();i++)
		{
			Classinfo tem=classlist.get(i);
			if(stu.getClassid()==tem.getId())
			{
				clazz.setSelectedIndex(i);
			}
		}
		//��ʼ��״̬
		switch (stu.getSstatus()) {
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
		//��ʼ����
		name.setText(stu.getSname());
		
		if(stu.getSsex().equals((String)sex.getSelectedItem()))
		{
			sex.setSelectedIndex(0);
		}else
		{
			sex.setSelectedIndex(1);
		}
		//��ʼ������
		java.sql.Date birthdate=stu.getSbirthday();
		String[]birtharr=birthdate.toString().split("[-]");
		for(int i=1980;i<2011;i++)
		{
			if(Integer.parseInt(birtharr[0])==i)
			{
				year.setSelectedIndex(i-1980);
			}
		}
		for(int i=0;i<12;i++)
		{
			if(Integer.parseInt(birtharr[1])==i+1)
			{
				mon.setSelectedIndex(i);
			}
		}
		for(int i=0;i<31;i++)
		{
			if(Integer.parseInt(birtharr[2])==i+1)
			{
				day.setSelectedIndex(i);
			}
		}
		//��ʼ��ѧУ
		school.setText(stu.getSchool());
		
		
		switch (stu.getSgrade()) {
		case "˶ʿ":
			xueli.setSelectedIndex(0);
			break;
		case "����":
			xueli.setSelectedIndex(1);
			break;
		case "ר��":
			xueli.setSelectedIndex(2);
			break;
		case "����":
			xueli.setSelectedIndex(3);
			break;
		default:
			break;
		}
		tel.setText(stu.getStel());
		email.setText(stu.getSmail());
		qq.setText(stu.getSqq());
		zhuanye.setText(stu.getSmajor());
		
		//��ʼ����ҵʱ��
		java.sql.Date biyesj=stu.getSgraduate();
		String[]biyesjarr=biyesj.toString().split("[-]");
		for(int i=2015;i<2021;i++)
		{
			if(Integer.parseInt(biyesjarr[0])==i)
			{
				year1.setSelectedIndex(i-2015);
			}
		}
		for(int i=0;i<12;i++)
		{
			if(Integer.parseInt(biyesjarr[1])==i+1)
			{
				mon1.setSelectedIndex(i);
			}
		}
		
		orgprice.setText(price.getOrgprice().toString());
		newprice.setText(price.getNewprice().toString());
		
		
	}
  //��ʼ���༶��Ϣ
	public void initClassinfo()
	{
		ClassinfoDao cd=(ClassinfoDao)DaoFactory.getDao(ClassinfoDao.class);
		
		try
		{
			classlist=cd.getAll();
			for(Classinfo clazz:classlist)
			{
				comModel.addElement(clazz);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public StuupdateDialog(StuinfoFrame sf,int stuId)
	{
		super(sf.getMain(),"�޸�ѧ����Ϣ",true);
		this.stuId=stuId;
		initStuinfo();//�����ݿ����ѧ����Ϣ
		this.sf=sf;
		this.setSize(600,600);
		this.setLocation(100, 30);
		initClassinfo();
		
		box1.add(Box.createHorizontalStrut(80));
		box1.add(lclazz);
		box1.add(clazz);
		box1.add(Box.createHorizontalStrut(15));
		box1.add(status);
		box1.add(Box.createHorizontalStrut(80));
		
		box2.add(Box.createHorizontalStrut(80));
		box2.add(lname);
		box2.add(name);
		box2.add(Box.createHorizontalStrut(15));
		box2.add(sex);
		box2.add(Box.createHorizontalStrut(80));
		
		
		box3.add(Box.createHorizontalStrut(80));
		box3.add(lbir);
        box3.add(year);
        box3.add(Box.createHorizontalStrut(10));
        box3.add(mon);
        box3.add(Box.createHorizontalStrut(10));
        box3.add(day);
        box3.add(Box.createHorizontalStrut(80));
		
        box4.add(Box.createHorizontalStrut(80));
        box4.add(lschool);
        box4.add(school);
        box4.add(Box.createHorizontalStrut(15));
        box4.add(xueli);
        box4.add(Box.createHorizontalStrut(80));
        
        box5.add(Box.createHorizontalStrut(80));
        box5.add(ltel);
        box5.add(tel);
        box5.add(Box.createHorizontalStrut(80));
        
        box6.add(Box.createHorizontalStrut(80));
        box6.add(lemail);
        box6.add(email);
        box6.add(Box.createHorizontalStrut(80));
        
        
        box7.add(Box.createHorizontalStrut(80));
        box7.add(lqq);
        box7.add(qq);
        box7.add(Box.createHorizontalStrut(80));
        
        box8.add(Box.createHorizontalStrut(80));
        box8.add(lzhuanye);
        box8.add(zhuanye);
        box8.add(Box.createHorizontalStrut(20));
        box8.add(biye);
        box8.add(year1);
        box8.add(Box.createHorizontalStrut(10));
        box8.add(mon1);
        box8.add(Box.createHorizontalStrut(80));
        
        box9.add(Box.createHorizontalStrut(80));
        box9.add(lorgprice);
        box9.add(orgprice);  
        orgprice.setText("13800.0");
        box9.add(Box.createHorizontalStrut(15));
        box9.add(lnewprice);
        box9.add(newprice);
        newprice.setText("8800.0");
        box9.add(Box.createHorizontalStrut(80));
        
        box10.add(Box.createHorizontalStrut(160));
        box10.add(update);
        box10.add(Box.createHorizontalStrut(160));
        
        
        main.add(Box.createVerticalStrut(30));
        main.add(box1);
        main.add(Box.createVerticalStrut(20));
        main.add(box2);
        main.add(Box.createVerticalStrut(20));
        main.add(box3);
        main.add(Box.createVerticalStrut(20));
        main.add(box4);
        main.add(Box.createVerticalStrut(20));
        main.add(box5);
        main.add(Box.createVerticalStrut(20));
        main.add(box6);	
        main.add(Box.createVerticalStrut(20));
        main.add(box7);	
        main.add(Box.createVerticalStrut(20));
        main.add(box8);	
        main.add(Box.createVerticalStrut(20));
        main.add(box9);	
        main.add(Box.createVerticalStrut(20));
        main.add(box10);	
        main.add(Box.createVerticalStrut(20));
		this.add(main);
		initListener();
		initUI();//��ʾ���ݿ��е�����
		this.setVisible(true);
	}
	//����ѧ��
	public String createStuno(Classinfo claszzinfo)
	{
		StringBuilder sb=new StringBuilder();
		sb.append(claszzinfo.getCno());
		 StuinfoDao sd=(StuinfoDao)DaoFactory.getDao(StuinfoDao.class);
		  try
		{
			int id=sd.nextNo(claszzinfo.getId());
			if(id<10)
			{
				sb.append(0);
				sb.append(id);
			}else
			{
				sb.append(id);
			}
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			ConnectionManager.getInstance().closeConnection();
		}
		
		
		
		return sb.toString();
		
	}
	//�޸�ѧ����Ϣ
	public void updatestu()
	{
		//stu����ֵ�������û�������
		Classinfo clazzinfo=(Classinfo)clazz.getSelectedItem();
	    stu.setClassid(clazzinfo.getId());
	    //stu.setStuno("20150201");
	 //  stu.setStuno(createStuno(clazzinfo));
	    switch ((String)status.getSelectedItem()) {
		case "��У":
			stu.setSstatus(0);
			break;
		case "��ҵ":
			stu.setSstatus(1);
			break;
		case "��ѧ":
			stu.setSstatus(2);
			break;
		default:
			break;
		}
		stu.setSname(name.getText());
		stu.setSsex((String)sex.getSelectedItem());
		String sbrith=((String)year.getSelectedItem())+"-"+((String)mon.getSelectedItem())+"-"+((String)day.getSelectedItem());
		stu.setSbirthday(java.sql.Date.valueOf(sbrith));
		stu.setSchool(school.getText());
		stu.setSgrade((String)xueli.getSelectedItem());
		stu.setStel(tel.getText());
		stu.setSmail(email.getText());
		stu.setSqq(qq.getText());
		String sbiye=((String)year1.getSelectedItem())+"-"+((String)mon1.getSelectedItem())+"-01";
		stu.setSgraduate(java.sql.Date.valueOf(sbiye));
		stu.setSmajor(zhuanye.getText());
	
		StuinfoDao sd=(StuinfoDao)DaoFactory.getDao(StuinfoDao.class);
		PriceinfoDao pd=(PriceinfoDao)DaoFactory.getDao(PriceinfoDao.class);
		try
		{
            ConnectionManager.getInstance().startTransaction();
            //�¼�
            //step1:����ѧ����Ϣ
            //step2:ȡ�¼ӵ�ѧ����Ϣ��id 
            //step3:����priceinfo��Ҫ�β�ѧ����Ϣid 
			sd.update(stu);
			String sorg=orgprice.getText();
			if(null!=sorg&&sorg.matches("\\d+[.]\\d+"))
			{
				price.setOrgprice(new BigDecimal(sorg));
			}
			String snew=newprice.getText();
			if(null!=snew&&snew.matches("\\d+[.]\\d+"))
			{
				price.setNewprice(new BigDecimal(snew));
			}
			
			pd.update(price);
			
			ConnectionManager.getInstance().commit();
			sf.initJTable(1);
			sf.getJtable().setModel(sf.getStulist());
			sf.getJtable().updateUI();
			StuupdateDialog.this.dispose();
		} catch (SQLException e)
		{
			ConnectionManager.getInstance().rollback();
			e.printStackTrace();
		}finally
		{
			ConnectionManager.getInstance().closeConnection();
		}
		
	}
    //�¼�����
	public void initListener()
	{
		update.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				 updatestu();
			}
		});
	}
}
