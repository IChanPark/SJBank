package jdbc.Fund;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fund_InfoDTO {

	private	String 	product,			//상품명
					product_info,		//상품설명
					type,				//유형 주식 채권 기타
					area,				//국내 해외 
					property,			//상품속성 ex 인터넷
					management,			//운용사
					sector,				//섹터
	 				status,				//상태 ex 판매중 판매중지
					tax;				//과세여부
					
	
	private	Float 	price,				//초기 기준가
					price_modify,		//수정 기준가
					first_fee,			//선취 수수료
					fee;				//년보수
	
	private	Date	register_date,		//펀드 등록일
					end_date;			//펀드 폐지일

	private	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public String getProduct_info() {
		return product_info;
	}

	public void setProduct_info(String product_info) {
		this.product_info = product_info;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getManagement() {
		return management;
	}

	public void setManagement(String management) {
		this.management = management;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getPrice_modify() {
		return price_modify;
	}

	public void setPrice_modify(Float price_modify) {
		this.price_modify = price_modify;
	}

	public Float getFirst_fee() {
		return first_fee;
	}

	public void setFirst_fee(Float first_fee) {
		this.first_fee = first_fee;
	}

	public Float getFee() {
		return fee;
	}

	public void setFee(Float fee) {
		this.fee = fee;
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
		return product + ","+product_info+ "," + price + "," + price_modify + ","
				+ type + "," + area + "," + property + "," + first_fee + "," + fee
				+ "," + management + "," + sector + "," + status + ","
				+ register_date + "," + end_date;
	}

}
