package jdbc.etc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Credit_rating_logDTO {
	
	private String 	id,					//계좌 번호
					change,				//상승/하락
					reason;			//계좌 상태 ex 사용 정지 만료?
	
	private Integer seq,			//로그순서
					rating;			//변경된 등급
	
	private Date 	register_date;	//계좌 등록일
					
	private	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	//-------------------------------------------------
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

	
	
}
