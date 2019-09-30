package jdbc.Loan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoanDTO {
	
	private	String 	account_number, //계좌번호
					id,				//ID
					product,		//상품명
					preferential,	//적용된 우대조건
					type,			//상환방식
					period;			//대출기간
	
	private	Float	interest;	//등락률
	
	private int sum;	//대출금액
	
	private Date end_date;
	
	private	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public String getEnd_dateStr() {
		return sdf.format(end_date);
	}
	
	
	public void setEnd_dateStr(String end_date) {
		try {
			this.end_date = sdf.parse(end_date);
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


	public String getPeriod() {
		return period;
	}


	public void setPeriod(String period) {
		this.period = period;
	}


	public Float getInterest() {
		return interest;
	}


	public void setInterest(Float interest) {
		this.interest = interest;
	}


	public int getSum() {
		return sum;
	}


	public void setSum(int sum) {
		this.sum = sum;
	}


	public Date getEnd_date() {
		return end_date;
	}


	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	
	
	
	
}
