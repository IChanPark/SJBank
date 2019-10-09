package server;

import java.io.Serializable;

public class Access_IP implements Serializable{
	private String ip;

	private Access_IP() {
		ip = "192.168.219.141";
	}

	private static class Holder {
		public static final Access_IP Data = new Access_IP();
	}

	public static Access_IP getInstance() {
		return Holder.Data;
	}
	
	public String getIP() {
		return ip;
	}
}