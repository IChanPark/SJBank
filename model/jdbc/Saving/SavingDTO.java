package jdbc.Saving;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SavingDTO {
	//예금가입자
	private String  account_number,  //계좌번호
					id,              //사용자 id
					product,          //상품명
					preferential,	 //적용된 우대조건
					type;			//적금 납입방식
	
	private Float 	Interest;		 //

	Date end_date;
	
	private	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	
	
	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getPreferential() {
		return preferential;
	}

	public void setPreferential(String preferential) {
		this.preferential = preferential;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Float getInterest() {
		return Interest;
	}

	public void setInterest(Float interest) {
		Interest = interest;
	}

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
	
}
