package jdbc.Fund;

public class FundDTO {
	
	private	String 	account_number, //계좌번호
					id,				//ID
					product;		//상품명
	
	private	Float	fluctuation;	//등락률
	
	
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
	public Float getFluctuation() {
		return fluctuation;
	}
	public void setFluctuation(Float fluctuation) {
		this.fluctuation = fluctuation;
	}
	@Override
	public String toString() {
		return account_number + "," + id + "," + product + ","
				+ fluctuation;
	}
	
	
}
