package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import mini.DAO;
import mini.DTO_Keep;

public class Acc_Member {
	Socket socket;
	public ObjectOutputStream dos;
	ObjectInputStream dis;

	TCPR rr;

	String id;
	public String type=""; 
	@FXML
	public ListView<String> au; 
	
	public TextArea ta;

	boolean chk = false;
	
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
					ArrayList<String> lu = new ArrayList<String>();
					ArrayList<String> la = new ArrayList<String>();
					Platform.runLater(()-> {
						if(data.target.equals("알림")) {
							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setTitle("보관 시간 알리미");
							alert.setHeaderText(data.msg);
							alert.show();
						} else if(data.target.equals("list")) {
							data.list.remove(id+"@"+data.type); //자신은 대화상대리스트에서 제외

							for (String s : data.list) {
								System.out.println(s);
								System.out.println(s.split("@")[1]);
								if(s.split("@")[1].equals("사용자"))
									lu.add(s.split("@")[0]);
								else {
									la.add(s.split("@")[0]);
								}
							}

							if(type.equals("사용자")) {
								au.getItems().clear();  //중복삭제
								au.getItems().addAll(la);
							} else {
								au.getItems().clear();  //중복삭제
								au.getItems().addAll(lu);
							}

						}
						else if(!data.type.equals("알림")) {
							System.out.println(data.msg);
							ta.appendText(data.msg+"\n");
						} 
						
					});			
				}
			} catch (Exception e) {}
		}	
	}

	class SelectDB extends Thread{
		@Override
		public void run() {
			boolean first = false;
			String found_t = null;
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			while(true) {
				try {
					sleep(1000);
					if(dos!=null) {
						Acc_Member_Data data = new Acc_Member_Data();
						data.target = "알림"; 
						data.name = id;
						data.type = "사용자";

						for (DTO_Keep dto : new DAO().OneReserveList(id)) {
							String [] dsp = dto.toString().split("=");
							found_t=dsp[8].substring(0, dsp[8].lastIndexOf(","));
							first = true;
						}
						
						if(first) {
							Date endDate = new Date();
							Date beginDate = fm.parse(found_t);
							long diff = (endDate.getTime() - beginDate.getTime()) * -1;
							long diffMinit = diff / (60 * 1000);
							if(diffMinit < 0)
								break;
							else if(diffMinit <= 60 && diffMinit >= 0) {
								data.msg = "보관 종료시간 "+found_t+" 까지 "+diffMinit+" 분 남았습니다."; 
								dos.writeObject(data);
								dos.flush();
								dos.reset();
								break;
							}
						} else {
							System.out.println("종료");
							break;
						}
						
					}
				} catch (Exception e) {}
			}
		}
	}
	

	public Acc_Member(String id) {
		try {
			if (socket == null) {
//				System.out.println("참이니");
				this.id = id;
				socket = new Socket("192.168.1.132", 7777);//192.168.1.180 // 192.168.0.18
				dos = new ObjectOutputStream(socket.getOutputStream());
				rr = new TCPR(socket);
				rr.setDaemon(true);
				rr.start(); //리시버 시작

				SelectDB s = new SelectDB();
				s.setDaemon(true);
				s.start();

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
