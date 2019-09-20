package jdbc.Account;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Account_pw_logDTO {
	
	private String	account_number,	//계좌 번호
					status;			//계좌 상태 ex 사용 정지 만료?
	
	private Integer seq;			//
	
	private Date 	register_date;	//계좌 등록일
					
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	@Override
	public String toString() {
		return seq + "," + account_number + "," + status + "," + register_date;
	}
}
