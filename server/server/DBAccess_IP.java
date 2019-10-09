package server;

import java.io.Serializable;

public class DBAccess_IP implements Serializable{
	private String ip;

	private DBAccess_IP() {
		ip = "192.168.219.101";
	}

	private static class Holder {
		public static final DBAccess_IP Data = new DBAccess_IP();
	}

	public static DBAccess_IP getInstance() {
		return Holder.Data;
	}
	
	public String getIP() {
		return ip;
	}
}