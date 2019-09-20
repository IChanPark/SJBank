package jdbc.Deposit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//예금 상품 정보
public class Deposits_infoDTO {
	private String  product,         //상품명
					jnterest_type,   //이자지급방식
					type,			 //가입타입 ex 인터넷, 모바일
					tax,			 //세금종류
					preferential,	 //우대구분
					prf_content,	 //우대조건 내용
					prf_Interest,	 //우대이자율
					partialization,	 //일부해지가능여부
					retention,		 //재예치가능여부
					regular,		 //정기/비정기
					status;			 //상태
					
	private Integer	Month,
					min_sum,
					max_sum;
	
	private Float  Min_Interest,
					Max_Interest;
	
	private Date    register_date,   //상품등록일
					end_date;		 //상품삭제일
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d H:m:s");
	
	
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

	public String getJnterest_type() {
		return jnterest_type;
	}

	public void setJnterest_type(String jnterest_type) {
		this.jnterest_type = jnterest_type;
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

	public String getPrf_Interest() {
		return prf_Interest;
	}

	public void setPrf_Interest(String prf_Interest) {
		this.prf_Interest = prf_Interest;
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
		return Month;
	}

	public void setMonth(Integer month) {
		Month = month;
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

	public Float getMin_Interest() {
		return Min_Interest;
	}

	public void setMin_Interest(Float min_Interest) {
		Min_Interest = min_Interest;
	}

	public Float getMax_Interest() {
		return Max_Interest;
	}

	public void setMax_Interest(Float max_Interest) {
		Max_Interest = max_Interest;
	}
	
	public String getRegular() {
		return regular;
	}

	public void setRegular(String regular) {
		this.regular = regular;
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
		return  product + "," + jnterest_type + "," + tax+ "," +type+"," +regular
				+ "," + preferential + "," + prf_content + "," + prf_Interest
				+ "," + partialization + "," + retention + "," + status + ","
				+ Month + "," + min_sum + "," + max_sum + "," + Min_Interest
				+ "," + Max_Interest + "," + register_date + "," + end_date;
	}
	
	
	
					
	
	
	
					
}
