package jdbc.Transfer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transfer_autoDTO {
	private	Integer 	seq,
						sum	;				//로그번호
	
	private	String 		account_number,		//계좌 번호
						to_account_number,	//본인계좌 여부
						period,				//이체시간
						last_day,	
						memo,	
						to_memo,		
						status;				//성공/실패
		
	
	private Date		start_date,			//이체시작일
						finish_date,		//이체종료일
						register_date,		//등록일
						end_date;			//삭제일
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public String getend_dateStr() {
		return sdf.format(end_date);
	}
	public void setEnd_datetr(String end_date) {
		try {
			this.end_date = sdf.parse(end_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getFinish_dateStr() {
		return sdf.format(finish_date);
	}
	public void setFinish_dateStr(String finish_date) {
		try {
			this.finish_date = sdf.parse(finish_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public String getStart_dateStr() {
		return sdf.format(start_date);
	}
	public void setStart_dateStr(String start_date) {
		try {
			this.start_date = sdf.parse(start_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
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

	public String getTo_account_number() {
		return to_account_number;
	}

	public void setTo_account_number(String to_account_number) {
		this.to_account_number = to_account_number;
	}
	

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getLast_day() {
		return last_day;
	}

	public void setLast_day(String last_day) {
		this.last_day = last_day;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getTo_memo() {
		return to_memo;
	}

	public void setTo_memo(String to_memo) {
		this.to_memo = to_memo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getFinish_date() {
		return finish_date;
	}

	public void setFinish_date(Date finish_date) {
		this.finish_date = finish_date;
	}

	public Date getRegister_date() {
		return register_date;
	}

	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	@Override
	public String toString() {
		return seq + "," + account_number + ","
				+ to_account_number + "," + sum + "," + period + "," + start_date
				+ "," + finish_date + "," + last_day + "," + memo + "," + to_memo
				+ "," + status + "," + end_date + "," + register_date;
	}
}
