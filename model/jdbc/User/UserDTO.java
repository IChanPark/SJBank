package jdbc.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDTO {

	private	String 	id,				//ID
					pw,				//PW					
					name,			//이름
					gen,			//성별
					email,			//이메일
					job_group,		//직업군
					addr,			//주소
					tel,			//전화번호
					status,			//계정 상태 활성/정지/탈퇴
					postal_code; 	//우편번호
	
	private Integer simple_pw; 			//간편비밀번호
					
					

	private	Date	register_date,	//가입일
					end_date;		//탈퇴일

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
	
	public String getGen() {
		return gen;
	}
	
	public void setGen(String gen) {
		this.gen = gen;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getJob_group() {
		return job_group;
	}
	
	public void setJob_group(String job_group) {
		this.job_group = job_group;
	}
	
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public String getPostal_code() {
		return postal_code;
	}
	
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
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
	
	public Integer getSimple_pw() {
		return simple_pw;
	}
	
	public void setSimple_pw(Integer simple_pw) {
		this.simple_pw = simple_pw;
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
	
	public Date getEnd_date() {
		return end_date;
	}
	
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	} 
	
	public String getEnd_dateStr() {
		return sdf.format(end_date);
	}
	
	public void setEnd_dateStr(String end_date) {
		try {
			this.end_date = sdf.parse(end_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "{"+id + "," + pw + "," + simple_pw + "," + name + ", " + tel
				+ "," + gen + "," + email + "," + job_group + "," + addr
				+ "," + postal_code + "," + status + "," + register_date
				+ "," + end_date + "}";
	}
}
