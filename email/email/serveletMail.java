package email;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;





/**
 * Servlet implementation class serveletMail
 */
@WebServlet("/serveletMail")
public class serveletMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public serveletMail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		   Properties p = System.getProperties();
	        p.put("mail.smtp.starttls.enable", "true");     // gmail은 무조건 true 고정
	        p.put("mail.smtp.host", "smtp.gmail.com");      // smtp 서버 주소
	        p.put("mail.smtp.auth","true");                 // gmail은 무조건 true 고정
	        p.put("mail.smtp.port", "587");                 // gmail 포트
	           
	        Authenticator auth = new SMTPAuthenticator();
	         
	        //session 생성 및  MimeMessage생성
	        Session session = Session.getDefaultInstance(p, auth);
	        MimeMessage msg = new MimeMessage(session);
	         
	        try{
	            //편지보낸시간
	            msg.setSentDate(new Date());
	             
	            InternetAddress from = new InternetAddress() ;
	             
	             
	            from = new InternetAddress("네모장군?<dgh4486@gmail.com>");
	             
	            // 이메일 발신자
	            msg.setFrom(from);
	             
	             
	            // 이메일 수신자
	            InternetAddress to = new InternetAddress("ehdrb1992@naver.com");
	            msg.setRecipient(Message.RecipientType.TO, to);
	             
	            // 이메일 제목
	            msg.setSubject("메일 전송 테스트", "UTF-8");
	             
	            // 이메일 내용
	            msg.setText("이것은 저주의 편지 빨리빨리 진행하지 않으면 곧 뒤질", "UTF-8");
	             
	            // 이메일 헤더
	            msg.setHeader("content-Type", "text/html");
	             
	            //메일보내기
	            javax.mail.Transport.send(msg);
	             System.out.println("끝났어요?!@~~~");
	        }catch (AddressException addr_e) {
	            addr_e.printStackTrace();
	        }catch (MessagingException msg_e) {
	            msg_e.printStackTrace();
	        }
	    }
		
	}



