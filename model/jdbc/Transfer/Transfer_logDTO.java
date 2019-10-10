package jdbc.Transfer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transfer_logDTO {

	private	String		account_number,		//보내는 계좌 - key
						feetype,				//본인계좌여부
						target,				//은행/증권사명
						to_account_number,	//받는 계좌
						received,			//받는이
						cms,				//CMS코드
						memo,				//내 통장메모
						to_memo,			//받는 통장메모
						status;				//성공/실패

	

	private	Integer 	seq,				//로그 번호
						fee;				//수수료
	
	private Long 		sum;				//총액

	private	Date		register_date;		//가입일

	private	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	
	public String getFeetype() {
		return feetype;
	}

	public void setFeetype(String feetype) {
		this.feetype = feetype;
	}
	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTo_account_number() {
		return to_account_number;
	}

	public void setTo_account_number(String to_account_number) {
		this.to_account_number = to_account_number;
	}

	public String getReceived() {
		return received;
	}

	public void setReceived(String received) {
		this.received = received;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public String getCms() {
		return cms;
	}

	public void setCms(String cms) {
		this.cms = cms;
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

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Long getSum() {
		return sum;
	}

	public void setSum(Long sum) {
		this.sum = sum;
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
		return seq + "," + account_number + "," + ","
				+ target + "," + to_account_number + "," + received + "," + sum
				+ "," + fee + "," + cms + "," + memo + "," + to_memo + "," + status
				+ "," + register_date;
	}
}
