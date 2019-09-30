package jdbc.Account;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Account_typeDTO {
	
	private String 	name,			//타입이름
					type;			//계좌 타입 ex 예금, 적금, 펀드 등

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return name + "," + type ;
	}
					
	
	
}
