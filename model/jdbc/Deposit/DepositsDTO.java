package jdbc.Deposit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DepositsDTO {
	//예금가입자
	private String  account_number,  //계좌번호
					id,              //사용자 id
					product,          //상품명
					preferential,	 //적용된 우대조건
					type;			//타입
	
	private Float 	Interest;		 //

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

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String prduct) {
		this.product = prduct;
	}

	public String getPreferential() {
		return preferential;
	}

	public void setPreferential(String preferential) {
		this.preferential = preferential;
	}

	public Float getInterest() {
		return Interest;
	}

	public void setInterest(Float interest) {
		Interest = interest;
	}
	
	@Override
	public String toString() {
		return account_number + "," + id +","+ product + ","
				+ preferential + "," + Interest +","+ type;
	}
	
	
}
