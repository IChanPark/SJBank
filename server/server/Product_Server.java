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

public class Product_Server {
	private HashMap<String, ObjectOutputStream>list  = null;
	
	private Product_Server() {
		try {
			list = new HashMap<String, ObjectOutputStream>();
			Collections.synchronizedMap(list);
			ServerSocket server = new ServerSocket(7778);
			System.out.println("서버시작");
			SelectDB s = new SelectDB();
			s.setDaemon(true);
			s.start();
			
		} catch (IOException e) {}
	}
	
	private static class Holder {
        public static final Product_Server Server = new Product_Server();
    }
	
	public static Product_Server getInstance() {
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
}
