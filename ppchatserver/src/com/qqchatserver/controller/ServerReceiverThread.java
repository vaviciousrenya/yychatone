package com.qqchatserver.controller;

import java.io.*;
import java.net.*;
import java.util.Set;
import java.util.Iterator;



import javax.swing.JOptionPane;



import com.yychat.model.Message;

public class ServerReceiverThread extends Thread{
	 
	Socket s;
     public ServerReceiverThread(Socket s){//
    	 this.s=s;
     }
     public void run(){
    	 ObjectInputStream ois;
    	 ObjectOutputStream oos;
    	 Message mess;
    	 while(true){
		try {
			//������Ϣ
			ois = new ObjectInputStream(s.getInputStream());
			mess=(Message)ois.readObject();
			System.out.println(mess.getSender()+"��"+mess.getReceiver()+"˵"+mess.getContent());
			if(mess.getMessageType().equals(Message.message_Common)){
			Socket s1=(Socket)StertServer.hmSocket.get(mess.getReceiver());
			oos=new ObjectOutputStream(s1.getOutputStream());
			 oos.writeObject(mess);
			}
			
			//��2�������������ܵ�������������ߺ�����Ϣ(���ͣ�mess_OnlineFriend)
			if(mess.getMessageType().equals(Message.message_RequestOnlineFriend)){
				Set friendSet=StertServer.hmSocket.keySet();
				Iterator it=friendSet.iterator();
				String friendName;
				String friendString=" ";
				while(it.hasNext()){
					friendName=(String)it.next();
					if(friendName.equals(mess.getSender()))
					   friendString=friendString+friendName+" ";
					
				}
				System.out.println("ȫ�����ѵ�����"+friendString);
			
			}
		} catch (IOException e) {
		e.printStackTrace();
			 
		}
		catch ( ClassNotFoundException e){
			e.printStackTrace();
		}
    	 
     }
}}