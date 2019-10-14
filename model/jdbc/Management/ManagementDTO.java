package jdbc.Management;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ManagementDTO {
	
	private	String 	id,				//ID
					pw,				//PW					
					name,			//이름
					department,		//부서
					tel,			//전화번호
					status;			//계정 상태 활성/정지/탈퇴
	
	private Integer access_level; 	//권한
					
					

	private	Date	register_date;	//가입일

	private	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getAccess_level() {
		return access_level;
	}

	public void setAccess_level(Integer access_level) {
		this.access_level = access_level;
	}
	

}
