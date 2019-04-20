package com.chatserver.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import com.yychat.model.Message;

public class ServerReceiverThread extends Thread{//����Ҫ��run()����
	Socket s;
	HashMap hmSocket;
	String sender;
	Message mess;
	ObjectOutputStream oos;
	
	public ServerReceiverThread(Socket s,HashMap hmSocket){
		this.s=s;
		this.hmSocket=hmSocket;		
	}
	
	public void run(){		
		ObjectInputStream ois;
		while(true){
			try {
				//����Message��Ϣ
				ois=new ObjectInputStream(s.getInputStream());
				mess=(Message)ois.readObject();//�����û���������������Ϣ��������10���û���100����
				System.out.println("�ȴ��û�����������Ϣ");
				System.out.println(mess.getSender()+"��"+mess.getReceiver()+"˵:"+mess.getContent());
				sender=mess.getSender();
				
				//ת��Message��Ϣ
				if(mess.getMessageType().equals(Message.message_Common)){
					Socket s1=(Socket)hmSocket.get(mess.getReceiver());
					ObjectOutputStream oos=new ObjectOutputStream(s1.getOutputStream());
					oos.writeObject(mess);//ת��Message
					System.out.println("������ת������Ϣ"+mess.getSender()+"��"+mess.getReceiver()+"˵:"+mess.getContent());
				}
				if(mess.getMessageType().equals(Message.message_RequestOnlineFriend)){
					
					Set friendSet=StartServer.hmSocket.keySet();
					Iterator it=friendSet.iterator();
					String friendName;
					String friendString="";
					while(it.hasNext()){
						friendName=(String)it.next();
						if(!friendName.equals(mess.getSender()));
						friendString=friendName+" "+friendString;
					}
					System.out.println("ȫ�����ѵ�����"+friendString);
					
					//��mess��ֵ
					mess.setContent(friendString);
					mess.setReceiver(sender);
					mess.setSender("Server");
					mess.setMessageType(Message.message_OnlineFriend);
				}
				
				//����mess
				Socket s1=(Socket)hmSocket.get(sender);
				sendMessage(s1,mess);
				
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
			
		
		
		
	}

	private void sendMessage(Socket s, Message mess) throws IOException{
		oos=new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(mess);
		
	}
	
}
