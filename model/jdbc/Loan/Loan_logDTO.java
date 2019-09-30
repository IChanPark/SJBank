package jdbc.Loan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Loan_logDTO {
	
	private	String 	account_number, //계좌번호
				
					status;			//성공/실패(사유)
	
	
	private int seq,	//로그번호
				sum,	//납입금액
				remain;	//납입상환액
				
	private Date register_date;
	
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


	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public int getSum() {
		return sum;
	}


	public void setSum(int sum) {
		this.sum = sum;
	}


	public int getRemain() {
		return remain;
	}


	public void setRemain(int remain) {
		this.remain = remain;
	}


	public Date getRegister_date() {
		return register_date;
	}


	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}

	
	
	
	
}
