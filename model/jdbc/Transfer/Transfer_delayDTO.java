package jdbc.Transfer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transfer_delayDTO {
	private	Integer 	seq,
						sum	;				//로그번호
	
	private	String 		account_number,		//계좌 번호
						to_account_number,	//본인계좌 여부
						memo,	
						to_memo,	
						cms,
						status;				//성공/실패
		
	
	private Date		register_date,		//등록일
						trs_time;			//삭제일
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public String gettrs_timeStr() {
		return sdf.format(trs_time);
	}
	public void settrs_timeStr(String trs_time) {
		try {
			this.trs_time = sdf.parse(trs_time);
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
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
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
	public Date getRegister_date() {
		return register_date;
	}
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
	public Date getTrs_time() {
		return trs_time;
	}
	public void setTrs_time(Date trs_time) {
		this.trs_time = trs_time;
	}
	
	
	
	
	
}
