package jdbc.Deposit;

public class DepositDTO {
	//예금가입자
	private String  account_number,  //계좌번호
					id,              //사용자 id
					prduct,          //상품명
					preferential;	 //적용된 우대조건
	
	private Float 	Interest;		 //

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

	public String getPrduct() {
		return prduct;
	}

	public void setPrduct(String prduct) {
		this.prduct = prduct;
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
		return account_number + "," + id +","+ prduct + ","
				+ preferential + "," + Interest;
	}
	
	
}
