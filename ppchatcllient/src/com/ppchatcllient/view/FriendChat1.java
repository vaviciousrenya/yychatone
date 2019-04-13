package com.ppchatcllient.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.*;

import com.ppchatlient.controller.ClientConnect;
import com.yychat.model.Message;

public class FriendChat1 extends JFrame implements ActionListener{

	//zhong
	JScrollPane jsp;
	JTextArea jta;
	
	//nan
	JPanel jk;
	JTextField jtb;
	JButton ji;

    String sender;
    String receiver;
	public FriendChat1(String sender,String receiver){
		this.sender=sender;
		this.receiver=receiver;
		jta=new JTextArea();//�ı���
		jta.setEditable(false);
		
		jsp=new JScrollPane(jta);
		this.add(jsp,"Center");
		//nan
		jk=new JPanel();
		jtb=new JTextField(15);
		ji=new JButton("����");
		ji.addActionListener(this);
		jk.add(jtb);
		jk.add(ji);
		this.add(jk,"South");
		
		this.setSize(350,240);
		this.setTitle(sender+"���ں�"+receiver+"�����������");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		//FriendChat1 friendChat=new FriendChat1("1","2");
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==ji) {
			jta.append(jtb.getText()+"\r\n");
			//�����������������Ϣ
			Message mess=new Message();
			mess.setSender(sender);
			mess.setReceiver(receiver);
			mess.setContent(jtb.getText());
			mess.setMessageType(Message.message_Common);
			ObjectOutputStream oos;
			try {
				Socket s=(Socket)ClientConnect.hmSocket.get(sender);
				oos = new ObjectOutputStream(ClientConnect.s.getOutputStream());
				oos.writeObject(mess);
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}	
	}

public void appendJta(String showMessage){
	jta.append(showMessage+"/r/n");
}
}