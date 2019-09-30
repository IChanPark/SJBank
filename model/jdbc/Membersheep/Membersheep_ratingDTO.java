package jdbc.Membersheep;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Membersheep_ratingDTO {
	
	private	String 	name,		//메버십등급명
					status;		//사용여부
	
	private int condition;	//등급 조건 포인트
	
	private Date 	register_date;
	
	private	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getRegister_dateStr() {
		return sdf.format(register_date);
	}
	
	public void setRegister_dateStr(String register_date) {
		try {
			this.register_date = sdf.parse(register_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Date getRegister_date() {
		return register_date;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}




	public int getCondition() {
		return condition;
	}


	public void setCondition(int condition) {
		this.condition = condition;
	}


	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}

	
}
