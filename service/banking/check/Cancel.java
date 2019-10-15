package banking.check;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Account.AccountDAO;
import jdbc.Transfer.Transfer_reserveDTO;
import jmodels.Transfer_reserveDAO;
import server.send;

public class Cancel implements M_Action{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String type = request.getParameter("type");
		if(type.equals("reserve")) {
		Transfer_reserveDTO dto = new Transfer_reserveDTO();
		dto.setSeq(Integer.parseInt(request.getParameter("seq") ) );
			new send("취소", "reserve", dto);
		
		}
		
		if(type.equals("delay")) {
			
		}
		
		if(type.equals("auto")) {
			
		}
		
		System.out.println("서비스들어온다");
		//리스트 정보를 키는 "data"로 어트리뷰트로 넘긴다
	}

}