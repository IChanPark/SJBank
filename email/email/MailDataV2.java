package email;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailDataV2 {
	class MyAuthentication extends Authenticator {
		private PasswordAuthentication pa;
		private MyAuthentication(){
			String	id = "dgh4486",		      // 구글 ID
					pw = "ehdrb1992";          // 구글 비밀번호			
			pa = new PasswordAuthentication(id, pw); // ID와 비밀번호를 입력한다.
		}
		// 시스템에서 사용하는 인증정보
		public PasswordAuthentication getPasswordAuthentication() {
			return pa;
		}
	}
	private Properties p = null;
	private	Authenticator auth = null;
	private	Session session =  null;
	private	MimeMessage msg = null;
	private InternetAddress from =null;
	private String 	recever =null,
					title =null,
					context =null;
	
	private MailDataV2() {
		p = System.getProperties();
		p.put("mail.smtp.starttls.enable", "true");     // gmail은 무조건 true 고정
		p.put("mail.smtp.host", "smtp.gmail.com");      // smtp 서버 주소
		p.put("mail.smtp.auth","true");                 // gmail은 무조건 true 고정
		p.put("mail.smtp.port", "587");                 // gmail 포트
		auth = new MyAuthentication();
		session = Session.getDefaultInstance(p, auth);
		msg = new MimeMessage(session);
		from = new InternetAddress() ;
	}

	private static class Holder {
		public static final MailDataV2 Data = new MailDataV2();
	}

	public static MailDataV2 getInstance() {
		return Holder.Data;
	}

	public void SendingMail(String recever ,String title, String context) {
		try{
			this.recever = recever;
			this.title = title;
			this.context = context;
			//편지보낸시간
			msg.setSentDate(new Date());
			from = new InternetAddress("네모장군?<dgh4486@gmail.com>");
			// 이메일 발신자
			msg.setFrom(from);
			// 이메일 수신자		
			InternetAddress to = new InternetAddress(recever);
			msg.setRecipient(Message.RecipientType.TO, to);		
			// 이메일 제목
			msg.setSubject(title, "UTF-8");	
			// 이메일 내용
			msg.setText(context, "UTF-8");
			// 이메일 헤더
			msg.setHeader("content-Type", "text/html");
			//메일보내기
			javax.mail.Transport.send(msg);

		}catch (Exception e) {e.printStackTrace();}
	}
}


