package com.ppchatserver.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.qqchatserver.controller.StertServer;

public class Chatserver extends JFrame implements ActionListener{

	JButton jb1;
	JButton jb2;
	JPanel jp;

	
	public Chatserver(){
		jb1=new JButton("����");
		jb1.addActionListener(this);//�¼�������
		jb2=new JButton("�ر�");
		jp=new JPanel();
		jp.add(jb1);
		jp.add(jb2);
		this.add(jp);
		
		this.setSize(240,300);
		this.setTitle("������");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		Chatserver chatserver=new Chatserver();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		new StertServer();

		
	}

}
