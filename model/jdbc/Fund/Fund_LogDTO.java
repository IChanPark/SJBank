package jdbc.Fund;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fund_LogDTO {
	
	private	Integer seq,				//로그번호
					sum;				//등락금액
	
	
	private	Float 	fluctuation;		//등락률
	
	private	String	account_number,		//계좌번호
					status;				//성공/실패
	
	private	Date 	register_date;		//날짜

	
	private	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
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
	
	
	
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public Float getFluctuation() {
		return fluctuation;
	}

	public void setFluctuation(Float fluctuation) {
		this.fluctuation = fluctuation;
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

	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}


	@Override
	public String toString() {
		return seq + "," + sum + "," + fluctuation + ","
				+ account_number + "," + status + "," + register_date;
	}
	
	
	
}	
