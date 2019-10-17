package jdbc.Saving;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Saving_logDTO {
//월별 예금 이자
	private String 	account_number,		//계좌
					status;				//성공/실패
	
	private Float	Interest;
	
	private Integer seq;

	long sum;
	
	private Date  	register_date;		//날짜
	
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

	public Float getInterest() {
		return Interest;
	}

	public void setInterest(Float interest) {
		Interest = interest;
	}

	public long getSum() {
		return sum;
	}

	public void setSum(long l) {
		this.sum = l;
	}
	
	
//----------------------------------------------------------
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
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return seq + "," + account_number + "," + Interest
				+ "," + sum + "," + status + "," + register_date;
	}
	
	
}
