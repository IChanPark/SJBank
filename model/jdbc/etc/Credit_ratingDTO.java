package jdbc.etc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Credit_ratingDTO {
	
	private String 	id,	//계좌 번호
					status;			//계좌 상태 ex 사용 정지 만료?
	
	private Integer rating;			//계좌 잔액
	
	private Date 	register_date;	//계좌 등록일
					
	private	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



	
	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
