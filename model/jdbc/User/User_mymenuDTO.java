package jdbc.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User_mymenuDTO {

	private	String 	id,				//ID
					mymenu1,		//
					mymenu2,		//
					mymenu3,		//
					mymenu4,		//
					mymenu5;		//
					
	

	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getMymenu1() {
		return mymenu1;
	}

	public void setMymenu1(String mymenu1) {
		this.mymenu1 = mymenu1;
	}

	public String getMymenu2() {
		return mymenu2;
	}

	public void setMymenu2(String mymenu2) {
		this.mymenu2 = mymenu2;
	}

	public String getMymenu3() {
		return mymenu3;
	}

	public void setMymenu3(String mymenu3) {
		this.mymenu3 = mymenu3;
	}

	public String getMymenu4() {
		return mymenu4;
	}

	public void setMymenu4(String mymenu4) {
		this.mymenu4 = mymenu4;
	}

	public String getMymenu5() {
		return mymenu5;
	}

	public void setMymenu5(String mymenu5) {
		this.mymenu5 = mymenu5;
	}

	@Override
	public String toString() {
		return "User_mymenuDTO [id=" + id + ", mymenu1=" + mymenu1 + ", mymenu2=" + mymenu2 + ", mymenu3=" + mymenu3
				+ ", mymenu4=" + mymenu4 + ", mymenu5=" + mymenu5 + "]";
	}
	
	

}
