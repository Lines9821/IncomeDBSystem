package com.iss.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JButton;
/**
 * ��һ��Button����д��paint
 * �����ͼ����֤�룬�ĸ���ĸ���ͺܶ����������
 * ����ͼ����֤�룬��һҪ�����㻭����ʲô
 * @author ������
 *
 */
public class RandImg extends JButton
{
	/**
	 * ���ڼ�¼��ͼ����֤���ϵ���ĸ��
	 */
   private StringBuilder sb=null;//new StringBuilder();
   public RandImg()
   {
	   this.setSize(120, 34);
   }
   /**
    * ���ⲿ����Ե���������������õ���֤���ϵ���ĸ
    * @return
    */
   public String getRandcode()
   {
	   return sb.toString();
   }
   /**
    * ����Ƶ���Ŧ�ϲ���ʾ��̬Ч��
    */
@Override
public boolean isRolloverEnabled()
{
	return false;
}
/**
 * �ػ水Ŧ
 */
@Override
public void paint(Graphics g)
{
	//ÿ���ػ涼���³�ʼ��StringBuilder
	sb=new StringBuilder();
	//���������ͼ�α���
	g.setColor(new Color(220,220,0xEB));
	g.fillRect(0,0 ,120, 34);
	
	//��50����
	Random ran=new Random();
	for(int i=0;i<50;i++)
	{
		g.setColor(new Color(200+ran.nextInt(50),200+ran.nextInt(20),200+ran.nextInt(20)));
		g.drawLine(ran.nextInt(120), ran.nextInt(34), ran.nextInt(120), ran.nextInt(34));
	}
	//��4����ĸ��������׷�ӵ�sb��
	for(int i=0;i<4;i++)
	{
		g.setFont(new Font("����",Font.BOLD,20+ran.nextInt(5)));
		String s1=randChar();
		sb.append(s1);
		g.setColor(new Color(100+ran.nextInt(20),50+ran.nextInt(10),50+ran.nextInt(10)));
		g.drawString(s1,i*15+28, ran.nextInt(10)+15);
	}
}
/**
 * �������һ����д��ĸ
 * @return
 */
public static String randChar()
{
	 Random ran=new Random();
	 char c=(char)(65+ran.nextInt(26));
	 return String.valueOf(c);
}
@Override
public Dimension getPreferredSize()
{
	// TODO Auto-generated method stub
	return new Dimension(120,34);
} 
}
