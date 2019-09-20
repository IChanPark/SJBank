package jdbc.User;

public class User_transfer_limitDTO {

	private	String		id;				//ID
	
	private Integer		daily_limit,	//일일제한
						number_limit;	//횟수제한
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getDaily_limit() {
		return daily_limit;
	}
	public void setDaily_limit(Integer daily_limit) {
		this.daily_limit = daily_limit;
	}
	public Integer getNumber_limit() {
		return number_limit;
	}
	public void setNumber_limit(Integer number_limit) {
		this.number_limit = number_limit;
	}
	
	@Override
	public String toString() {
		return id + "," + daily_limit + "," + number_limit;
	}		
}
