package jdbc.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User_simple_pw_logDTO {

	private	String 	id,				//ID
					status;			//로그 유효성
	
	private Integer seq;			//로그 순서

	private	Date	register_date;	//가입일

	private	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
		
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRegister_date() {
		return register_date;
	}
	
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
	
	public String getRegister_dateStr() {
		return sdf.format(register_date);
	}
	
	public void setRegister_dateStr(String register_date) {
		try {
			this.register_date = sdf.parse(register_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return seq + "," + id + "," + status + "," + register_date;
	}			
}
