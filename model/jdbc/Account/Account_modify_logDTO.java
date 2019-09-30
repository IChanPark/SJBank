package jdbc.Account;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Account_modify_logDTO {
	
	private String	account_number,	//계좌 번호
					pw,			//변경전
					modify_pw;			//변경후
	private Integer seq;			//
	
	private Date 	register_date;	//수정일
					
	private	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	
	public Date getRegister_date() {
		return register_date;
	}
//-------------------------------------------------
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
			e.printStackTrace();
		}
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getModify_pw() {
		return modify_pw;
	}

	public void setModify_pw(String modify_pw) {
		this.modify_pw = modify_pw;
	}

	@Override
	public String toString() {
		return account_number + "," + pw + "," + modify_pw
				+ "," + seq + "," + register_date;
	}


	
}
