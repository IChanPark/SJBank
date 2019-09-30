package security.otp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inf.M_Action;
import jdbc.Account.AccountDAO;
import jdbc.Security.OTPDAO;
import jdbc.Security.OTPDTO;

public class Reg implements M_Action{
	
	private String makeSerial()
	{
		String serial = (int)(Math.random()*1000)+"-"+
				(int)(Math.random()*10000)+"-" +
				(int)(Math.random()*10000);
		
		return serial;
	}
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		System.out.println("보안의  REG 서비스들어온다");
		//리스트 정보를 키는 "data"로 어트리뷰트로 넘긴다
		String id = (String)session.getAttribute("userID");
		
		OTPDAO.getInstance().UpdateToInactive(id);
		
		String serial;
		OTPDTO dto= new OTPDTO();
		do {
			serial=makeSerial();
		}		
		while( OTPDAO.getInstance().chkSerial(serial));
		
		dto.setSerial(serial);
		dto.setId(id );
		
		OTPDAO.getInstance().insert(dto);
	}

}