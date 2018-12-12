package com.iss.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.iss.dao.ClassinfoDao;
import com.iss.dao.core.ConnectionManager;
import com.iss.dao.core.DaoFactory;
import com.iss.pojo.Classinfo;
import com.iss.utils.Classutils;

public class ClassinfoUPdateDialog extends JDialog
{
	private ClassinfoFrame cf;
	private Classinfo info;
	   private JLabel ldesc=new JLabel("����");
	   private JTextField desc=new JTextField(12);
	   private JLabel ltea=new JLabel("��ʦ");
	   private JTextField tea=new JTextField(12);
	   private JLabel ltor=new JLabel("��Ա");
	   private JTextField tor=new JTextField(12);
	   private JButton update=new JButton("�޸�");
    public ClassinfoUPdateDialog(ClassinfoFrame cf,Classinfo info)
    {
    	super(cf.getMain(),"�޸İ༶��Ϣ",true);
    	this.cf=cf;
    	this.info=info;
    	this.setLayout(new FlowLayout());
    	this.setSize(400,200);
    	this.setLocation(200,300);
    	this.setResizable(false);
    	
    	 this.add(ldesc);
    	 this.add(desc);
    	 this.add(ltea);
    	 this.add(tea);
    	 this.add(ltor);
    	 this.add(tor);
    	 this.add(update);
    	
    	 intEdit();
    		initLisenter();
    	this.setVisible(true);
    
    }
    public void intEdit()
    {
    	desc.setText(info.getCdesc());
    	tea.setText(info.getCteacher());
    	tor.setText(info.getCtutor());
    }
    /**
     * �¼�����
     */
    public void initLisenter()
    {
    	update.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				   String cdesc=desc.getText();
				   String ctea=tea.getText();
				   String ctor=tor.getText();
				   if("".equals(cdesc)||"".equals(ctea)||"".equals(ctor))
				   {
					   JOptionPane.showMessageDialog(null,"���ݲ���Ϊ��");
				   }else
				   {
					  
		
					   info.setCdesc(cdesc);
					   info.setCteacher(ctea);
					   info.setCtutor(ctor);
					   
					   ClassinfoDao cd=(ClassinfoDao)DaoFactory.getDao(ClassinfoDao.class);
					   try
					{
						cd.update(info);
						cf.initJTable();
						cf.getJtable().setModel(cf.getClasslist());
						cf.getJtable().updateUI();
						ClassinfoUPdateDialog.this.dispose();
					} catch (SQLException ex)
					{
						JOptionPane.showConfirmDialog(null,"���Ӱ༶��Ϣʧ��");
						ex.printStackTrace();
					}finally
					{
						ConnectionManager.getInstance().closeConnection();
					}
				   }//end else
				
			}//end actionPerformed
		});
    }
}
