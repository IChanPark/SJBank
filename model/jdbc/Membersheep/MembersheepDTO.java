package jdbc.Membersheep;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MembersheepDTO {
	
	private	String 	id,
					membersheep,
					status;
	
	private	Integer point;
	
	private Date 	register_date;
	
	
	private	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getMembersheep() {
		return membersheep;
	}


	public void setMembersheep(String membersheep) {
		this.membersheep = membersheep;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Integer getPoint() {
		return point;
	}


	public void setPoint(Integer point) {
		this.point = point;
	}


	public Date getRegister_date() {
		return register_date;
	}


	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}

	@Override
	public String toString() {
		return  id + "," + membersheep + "," + status + "," + point
				+ "," + register_date;
	}
	
	
			
		
	
	
	
	

}
