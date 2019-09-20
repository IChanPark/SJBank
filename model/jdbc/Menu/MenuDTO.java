package jdbc.Menu;

public class MenuDTO {

	private	String 	type,			//ID
					name,			//메뉴명
					kor_name,		//한국메뉴명
					prnts_name,		//부모메뉴
					status;			//사용여부
	
	private Integer depth,			//메뉴 깊이
					sort;			//정렬순서

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKor_name() {
		return kor_name;
	}

	public void setKor_name(String kor_name) {
		this.kor_name = kor_name;
	}

	public String getPrnts_name() {
		return prnts_name;
	}

	public void setPrnts_name(String prnts_name) {
		this.prnts_name = prnts_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return type + "," + name + "," + kor_name + "," + prnts_name
				+ "," + status + "," + depth + "," + sort;
	}
}
