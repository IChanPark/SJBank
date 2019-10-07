package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap; 

public class Deposits_Server {
	private HashMap<String, ObjectOutputStream>list  = null;
	
	private Deposits_Server() {
		try {
			list = new HashMap<String, ObjectOutputStream>();
			Collections.synchronizedMap(list);
			ServerSocket server = new ServerSocket(7777);
			System.out.println("서버시작");
			SelectDB s = new SelectDB();
			s.setDaemon(true);
			s.start();
			
			while(true) {
				Socket client = server.accept();
				new TCPMulServerReciever(client).start();
			}
		} catch (IOException e) {}
	}
	
	private static class Holder {
        public static final Deposits_Server Server = new Deposits_Server();
    }
	
	public static Deposits_Server getInstance() {
        return Holder.Server;
    }

	class SelectDB extends Thread{
		@Override
		public void run() {
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			while(true) {
				try {
					sleep(10000);
					
					System.out.println("돈다디비");
					//DB를 여기서 돌리시오 
					
				} catch (Exception e) {}
			}
		}
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

				name = def.name;
				list.put(name,dos);

				def.list = new ArrayList<String>(list.keySet());

				sendToOne(def);

				while(dis!=null) {
					Acc_Member_Data data = (Acc_Member_Data)dis.readObject();
					if(data.target != "DB"){
						sendToOne(data);
					}
				}
			}catch (Exception e) {}
			finally {  
				list.remove(name);   
				System.out.println("나감 "+name+"\n 접속자 "+list.keySet());
			}
		}
	}

	void sendToOne(Acc_Member_Data data) {
		ObjectOutputStream dd = list.get(data.target);
		System.out.println("타겟 "+data.target+" 보내는이 "+data.name);
		System.out.println("보내는내용"+data.msg);
		try {
			dd.writeObject(data);
			dd.flush();
			dd.reset();
		} catch (IOException e) { e.printStackTrace(); }
	}
}
