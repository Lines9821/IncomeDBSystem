package com.iss.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JButton;
/**
 * 是一个Button，重写了paint
 * 绘出了图形验证码，四个字母，和很多条随机的线
 * 画了图形验证码，但一要记信你画的是什么
 * @author 刘慧涛
 *
 */
public class RandImg extends JButton
{
	/**
	 * 用于记录，图形验证码上的字母的
	 */
   private StringBuilder sb=null;//new StringBuilder();
   public RandImg()
   {
	   this.setSize(120, 34);
   }
   /**
    * 在外部类可以调用这个方法，来得到验证码上的字母
    * @return
    */
   public String getRandcode()
   {
	   return sb.toString();
   }
   /**
    * 鼠标移到按纽上不显示动态效果
    */
@Override
public boolean isRolloverEnabled()
{
	return false;
}
/**
 * 重绘按纽
 */
@Override
public void paint(Graphics g)
{
	//每次重绘都重新初始化StringBuilder
	sb=new StringBuilder();
	//先填充整个图形背景
	g.setColor(new Color(220,220,0xEB));
	g.fillRect(0,0 ,120, 34);
	
	//画50条线
	Random ran=new Random();
	for(int i=0;i<50;i++)
	{
		g.setColor(new Color(200+ran.nextInt(50),200+ran.nextInt(20),200+ran.nextInt(20)));
		g.drawLine(ran.nextInt(120), ran.nextInt(34), ran.nextInt(120), ran.nextInt(34));
	}
	//画4个字母，并将其追加到sb中
	for(int i=0;i<4;i++)
	{
		g.setFont(new Font("黑体",Font.BOLD,20+ran.nextInt(5)));
		String s1=randChar();
		sb.append(s1);
		g.setColor(new Color(100+ran.nextInt(20),50+ran.nextInt(10),50+ran.nextInt(10)));
		g.drawString(s1,i*15+28, ran.nextInt(10)+15);
	}
}
/**
 * 随机产生一个大写字母
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
