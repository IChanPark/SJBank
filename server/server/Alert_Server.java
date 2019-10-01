package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap; 

public class Alert_Server {
	HashMap<String, ObjectOutputStream>list  = new HashMap<String, ObjectOutputStream>();
	Alert_Server() {
		try {
			Collections.synchronizedMap(list);
			ServerSocket server = new ServerSocket(7777);
			System.out.println("서버시작");

			while(true) {
				Socket client = server.accept();
				new TCPMulServerReciever(client).start();
			}
		} catch (IOException e) {}
	}

	////각 클라이언트에 대한 리시버 이면서 받으면 바로 모든 접속자에게 메세지 보냄
	class TCPMulServerReciever extends Thread{

		ObjectOutputStream dos;
		ObjectInputStream dis;
		String name;

		TCPMulServerReciever(Socket client) {
			try {
				dos = new ObjectOutputStream(client.getOutputStream());
				dis = new ObjectInputStream(client.getInputStream());
			} catch (IOException e) {}
		}

		@Override
		public void run() { 
			try {   
				Acc_Member_Data def = (Acc_Member_Data)dis.readObject();

				name = def.name+"@"+def.type;
				list.put(def.name+"@"+def.type,dos);

				def.list = new ArrayList<String>(list.keySet());

				sendToAll(def);

				while(dis!=null) {
					Acc_Member_Data data = (Acc_Member_Data)dis.readObject();
					if(data.target.equals("알림")) {
						sendToAlt(data);
					} else if(data.target.equals("list")){
						sendToAll(data);
					} else if(data.target != null){
						sendToOne(data);
					}
				}
			}catch (Exception e) {}
			finally {  
				list.remove(name);   
				System.out.println("나감 "+name+" 접속자 "+list.keySet());
				System.out.println();
			}
		}
	}
	void sendToAll(Acc_Member_Data data) {
		System.out.println("bbb "+list.keySet());
		for (ObjectOutputStream dd : list.values()) {
			try {
				dd.writeObject(data);
				dd.flush();
				dd.reset();
			} catch (IOException e) { e.printStackTrace(); }
		}
	}
	////한명에게 데이터 보내는 메소드
	void sendToAlt(Acc_Member_Data data) {
		ObjectOutputStream dd = list.get(data.name+"@사용자"); 
		try {
			dd.writeObject(data);
			dd.flush();
			dd.reset();
		} catch (IOException e) { e.printStackTrace(); }
	}

	void sendToOne(Acc_Member_Data data) {
		ObjectOutputStream dd = list.get(data.target);
		System.out.println("타겟 "+data.target+" 보내는이 "+data.name);
		try {
			dd.writeObject(data);
			dd.flush();
			dd.reset();
		} catch (IOException e) { e.printStackTrace(); }
	}

	public static void main(String[] args) {
		new Alert_Server();
	}
}
