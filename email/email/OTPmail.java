package email;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javafx.beans.property.StringProperty;
import jdbc.Account.AccountDAO;
import jdbc.Account.AccountDTO;
import jdbc.User.UserDAO;
import jdbc.User.UserDTO;



/**
 * Servlet implementation class myMail
 */
@WebServlet("/OTPmail.jsp")
public class OTPmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OTPmail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("??");
		Properties p = System.getProperties();
		p.put("mail.smtp.starttls.enable", "true");     // gmail은 무조건 true 고정
		p.put("mail.smtp.host", "smtp.gmail.com");      // smtp 서버 주소
		p.put("mail.smtp.auth","true");                 // gmail은 무조건 true 고정
		p.put("mail.smtp.port", "587");                 // gmail 포트

		Authenticator auth = new MyAuthentication();

		//session 생성 및  MimeMessage생성
		Session session = Session.getDefaultInstance(p, auth);
		MimeMessage msg = new MimeMessage(session);

		try{
			//편지보낸시간
			msg.setSentDate(new Date());
			InternetAddress from = new InternetAddress() ;
			from = new InternetAddress("갓갓갓갓이찬님?<dgh4486@gmail.com>");
			// 이메일 발신자
			msg.setFrom(from);

			HttpSession hsession = request.getSession();
			String toWho=(String)hsession.getAttribute("userID");
			UserDTO dto = UserDAO.getInstance().selectId(toWho);
			
			// 이메일 수신자
			InternetAddress to = new InternetAddress(dto.getEmail());
			msg.setRecipient(Message.RecipientType.TO, to);

			// 이메일 제목
			msg.setSubject("이글은 자바로 부터 보내졌습니다.", "UTF-8");

			int OTP = (int)(Math.random()*1000000);
			
			
			// 이메일 내용
			msg.setText("커밋 해주세요 커밋커밋커밋", "UTF-8");

			// 이메일 헤더
			msg.setHeader("content-Type", "text/html");

			//메일보내기
			javax.mail.Transport.send(msg);

		}catch (AddressException addr_e) {
			addr_e.printStackTrace();
		}catch (MessagingException msg_e) {
			msg_e.printStackTrace();
		}
		System.out.println("??!@#!");
		

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
       
        //DTO 타입의 어레이리스트를 json 형태로 바꿔주는 구문(라이브러리 필수, zip->jar 확장자명 꼭 확인)
        String gson = new Gson().toJson("\"abc\":\"asf\"");
       
        try {
        	 response.getWriter().write(gson);
		} catch (Exception e) {
			// TODO: handle exception
		}
           

		//HttpSession se= request.getSession();
//	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp" ); //여기로 보내
//	dispatcher.forward(request, response);
	}
	
	class MyAuthentication extends Authenticator {
	
		PasswordAuthentication pa;
	
	
		public MyAuthentication(){
	
			String id = "dgh4486";       // 구글 ID
			String pw = "ehdrb1992";          // 구글 비밀번호
	
			// ID와 비밀번호를 입력한다.
			pa = new PasswordAuthentication(id, pw);
	
		}
	
		// 시스템에서 사용하는 인증정보
		public PasswordAuthentication getPasswordAuthentication() {
			return pa;
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

}