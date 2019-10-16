package jdbc.Calc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JungDTO {
	
	private String 	product,
					type;
	
	private Long	count,
					sum;			//계좌 잔액
	
	private Date 	date;
	
	private	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
	private	SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
	private	SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");

	public String getType() {
		return type;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getDateStr() {
		return sdf.format(date);
	}
	
	public void setDateStr(String date) {
		try {
			if(date.length() == 4) 
				this.date = sdfy.parse(date);
			else if(date.length() == 7) 
				this.date = sdf.parse(date);
			else if(date.length() == 10)
				this.date = sdfd.parse(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Long getSum() {
		return sum;
	}

	public void setSum(Long sum) {
		this.sum = sum;
	}
}
