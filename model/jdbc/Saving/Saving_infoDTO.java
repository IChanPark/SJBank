package jdbc.Saving;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//예금 상품 정보
public class Saving_infoDTO {
	private String  product,         //상품명
					interest_type,   //이자지급방식
					type,			 //가입타입 ex 인터넷, 모바일
					tax,			 //세금종류
					regular,		//정기/비정기
					preferential,	 //우대구분
					prf_content,	 //우대조건 내용
					prf_interest,	 //우대이자율
					partialization,	 //일부해지가능여부
					retention,		 //재예치가능여부
					status;			 //상태
					
	private Integer	month,			//약정 개월 수
					min_sum,		//최소 납입금
					max_sum;		//최대 납입금
	
	private Float  	min_interest,	//최저 연이자
					max_interest;	//최대 연이자
	
	private Date    register_date,   //상품등록일
					end_date;		 //상품삭제일
	
	private	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	
	
	
	

	public String getInterest_type() {
		return interest_type;
	}

	public void setInterest_type(String interest_type) {
		this.interest_type = interest_type;
	}

	public String getRegular() {
		return regular;
	}

	public void setRegular(String regular) {
		this.regular = regular;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	
	

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
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

	public String getPartialization() {
		return partialization;
	}

	public void setPartialization(String partialization) {
		this.partialization = partialization;
	}

	public String getRetention() {
		return retention;
	}

	public void setRetention(String retention) {
		this.retention = retention;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getMin_sum() {
		return min_sum;
	}

	public void setMin_sum(Integer min_sum) {
		this.min_sum = min_sum;
	}

	public Integer getMax_sum() {
		return max_sum;
	}

	public void setMax_sum(Integer max_sum) {
		this.max_sum = max_sum;
	}

	public Float getMin_interest() {
		return min_interest;
	}

	public void setMin_interest(Float min_Interest) {
		this.min_interest = min_Interest;
	}

	public Float getMax_interest() {
		return max_interest;
	}

	public void setMax_interest(Float max_Interest) {
		this.max_interest = max_Interest;
	}
	
	//-------------------------------------------------------------
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
		return	product + "," + min_interest + "," + max_interest + ","
				+ month + "," + type + "," + interest_type + ","
				+ tax + "," + preferential + "," + prf_content + ","
				+ prf_interest + "," + min_sum + "," + max_sum
				+ "," + partialization + "," + retention + "," + status
				+ "," + register_date + "," + end_date;
	}
}
