package jdbc.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDTO {

	private	String 	id,				//ID
					pw,				//PW
					name,			//이름
					gen,			//성별
					family,			//가게형태
					job_group,		//직업군
					Income,			//연소득
					residence, 		//거주시
					county,			//거주구 또는 동
					status;			//계정 상태 활성/정지/탈퇴
	
	private Integer simple_pw;		//간편비밀번호

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
	
	public String getFamily() {
		return family;
	}
	
	public void setFamily(String family) {
		this.family = family;
	}
	
	public String getJob_group() {
		return job_group;
	}
	
	public void setJob_group(String job_group) {
		this.job_group = job_group;
	}
	
	public String getIncome() {
		return Income;
	}
	
	public void setIncome(String income) {
		Income = income;
	}
	
	public String getResidence() {
		return residence;
	}
	
	public void setResidence(String residence) {
		this.residence = residence;
	}
	
	public String getCounty() {
		return county;
	}
	
	public void setCounty(String county) {
		this.county = county;
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
		return id + "," + pw + "," + name + "," + gen + "," + family
				+ "," + job_group + "," + Income + "," + residence + "," + county
				+ "," + status + "," + simple_pw + "," + register_date + ","
				+ end_date;
	}
	
	
		
		
}
