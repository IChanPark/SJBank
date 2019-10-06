package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Acc_Member {
	Socket socket;
	public ObjectOutputStream dos;
	ObjectInputStream dis;

	TCPR rr;

	String name;

	class TCPR extends Thread{

		public TCPR(Socket socket) {
			try {
				dis = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) { e.printStackTrace(); }
		}

		@Override
		public void run() {
			try {
				while(dis!=null) {
					Acc_Member_Data data = (Acc_Member_Data)dis.readObject();

					System.out.println("mag =" + data.msg);
//					if(!data.target.equals(name)) {//자신은 대화상대리스트에서 제외
						//수신 완료 메시지 받을려면 여기에 적으시오 

//					}	
				}
			} catch (Exception e) {}
		}	
	}

	public Acc_Member(String name) {
		try {
			if (socket == null) {
				this.name = name;
				socket = new Socket(Access_IP.getInstance().getIP(), 7777);//192.168.1.180 // 192.168.0.18
				dos = new ObjectOutputStream(socket.getOutputStream());
				rr = new TCPR(socket);
				rr.setDaemon(true);
				rr.start(); //리시버 시작

				System.out.println("클라이언트->서버 연결 성공");
			} else {
				dos = null;
				dis = null;
				rr.stop();
				socket.close();
				socket = null;
			} 

		} catch (Exception e) {}}
}
