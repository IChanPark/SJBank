package jdbc.Fund;

public class FundDTO {
	
	private	String 	account_number, //계좌번호
					id,				//ID
					product;		//상품명
	
	private	Float	fluctuation,	//등락률
					amount,			//가입금액
					nowmoney,		//현재금액
					rest,			//나머지돈
					exchange,		//환산금액
					price_modify;	//수정가
	
	
	private	int		buynum;			//구매량
									
	
	
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	
	public Float getPrice_modify() {
		return price_modify;
	}
	public void setPrice_modify(Float price_modify) {
		this.price_modify = price_modify;
	}

	public Float getRest() {
		return rest;
	}
	public void setRest(Float rest) {
		this.rest = rest;
	}
	public Integer getBuynum() {
		return buynum;
	}
	public void setBuynum(Integer buynum) {
		this.buynum = buynum;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public Float getNowmoney() {
		return nowmoney;
	}
	public void setNowmoney(Float nowmoney) {
		this.nowmoney = nowmoney;
	}
	public Float getExchange() {
		return exchange;
	}
	public void setExchange(Float exchange) {
		this.exchange = exchange;
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
