package jdbc.Account;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountDTO {
	
	private String 	account_number,	//계좌 번호
					type,			//계좌 타입 ex 예금, 적금, 펀드 등
					alias,			//계좌 별칭 (사용자가 정한 이름)
					id,				//계좌 사용자 아이디
					pw,				//계좌 비밀번호
					status;			//계좌 상태 ex 사용 정지 만료?
	
	private Integer sum;			//계좌 잔액
	
	private Date 	register_date,	//계좌 등록일
					end_date;		//계좌 정지일
	
	private	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
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
//-------------------------------------------------
	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	public String getEnd_dateStr() {
		return sdf.format(end_date);
	}
	
	public void SetEnd_dateStr(String end_date) {
		try {
			this.end_date = sdf.parse(end_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return 	account_number + "," + type + "," + sum + "," + alias + "," + 
				id + "," + pw + "," + status + "," + register_date + "," + end_date;
	}
}
