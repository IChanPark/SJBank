package jdbc.Loan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Loan_InfoDTO {

	private	String 	product,			//상품명
					product_info,		//상품설명
					type,				//대출종류
					preferential,				//우대 구분 
					prf_content,			//우대 조건 내용
					prf_interest,			//우대 이자율
					status;				//상태 ex 판매중 판매중지
	
	private	Float 	min_interest,				//최저 연이자
					max_interest;		//최고 연이자
				
	private Integer	 month;		//만기개월
	private	Long		loanlimit;		//대출한도
	
	private	Date	register_date,		//상품 등록일
					end_date;			//상품 삭제일

	private	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	
	
	
	
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getType() {
		return type;
	}

	public String getProduct_info() {
		return product_info;
	}

	public void setProduct_info(String product_info) {
		this.product_info = product_info;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPreferential() {
		return preferential;
	}

	public void setPreferential(String preferential) {
		this.preferential = preferential;
	}

	public String getPrf_content() {
		return prf_content;
	}

	public void setPrf_content(String prf_content) {
		this.prf_content = prf_content;
	}

	public String getPrf_interest() {
		return prf_interest;
	}

	public void setPrf_interest(String prf_interest) {
		this.prf_interest = prf_interest;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Float getMin_interest() {
		return min_interest;
	}

	public void setMin_interest(Float min_interest) {
		this.min_interest = min_interest;
	}

	public Float getMax_interest() {
		return max_interest;
	}

	public void setMax_interest(Float max_interest) {
		this.max_interest = max_interest;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public Long getloanlimit() {
		return loanlimit;
	}

	public void setloanlimit(Long loanlimit) {
		this.loanlimit = loanlimit;
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
	
	
	
	public String getRegister_dateStr() {
		return sdf.format(register_date);
	}
	
	public void setRegister_dateStr(String register_date) {
		try {
			this.register_date = sdf.parse(register_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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

	@Override
	public String toString() {
		return  product + "," + product_info + "," + type
				+ ", " + preferential + "," + prf_content + "," + prf_interest
				+ "," + status + "," + min_interest + "," + max_interest
				+ "," + month + "," + loanlimit + "," + register_date + ","
				+ end_date;
	}

	

	

}
