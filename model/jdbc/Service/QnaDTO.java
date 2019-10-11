package jdbc.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QnaDTO {
	
	private String 	type,			//문의 종류
					title,			//제목
					id,				//작성자
					content,       //문의내용								
					status;			//답변 여부
					
	
	private Integer seq,rseq;			//
								
	
	private Date 	register_date;	//등록일
						
	
	private	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	
	
	 
	
	
	

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
		
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}	

	public Integer getRseq() {
		return rseq;
	}
	public void setRseq(Integer rseq) {
		this.rseq = rseq;
	}
	//-------------------------------------------------
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

}
