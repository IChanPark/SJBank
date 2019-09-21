package jdbc.Transfer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transfer_reserveDTO {
	private Integer 	seq;				//로그번호
					
	
	private	String 		account_number,		//계좌 번호
						to_account_number,	//본인계좌 여부
						sum,				//은행/증권사명
						time,				//이체시간
						memo,	
						to_memo,	
						cms,		
						status,
						scheduled_date;		//예약 이체 예정시간

	private Date		register_date;		//예약 이체 등록일
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

	public String getCms() {
		return cms;
	}

	public void setCms(String cms) {
		this.cms = cms;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getScheduled_date() {
		return scheduled_date;
	}

	public void setScheduled_date(String scheduled_date) {
		this.scheduled_date = scheduled_date;
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
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return seq + "," + account_number + "," + to_account_number + "," + sum + "," +
				time + "," + memo + "," + to_memo + "," + cms + "," + status + "," +
				scheduled_date + "," + register_date;
	}
}
