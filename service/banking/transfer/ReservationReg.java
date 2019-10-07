package banking.transfer;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inf.M_Action;
import jdbc.Account.AccountDAO;
import jdbc.Transfer.Transfer_reserveDAO;
import jdbc.Transfer.Transfer_reserveDTO;
import server.send;

public class ReservationReg implements M_Action{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		System.out.println(request.getParameter("scheduled_date"));
		System.out.println(request.getParameter("time"));
		System.out.println(request.getParameter("toAcc"));
		System.out.println(request.getParameter("time")); 
		System.out.println(request.getParameter("to_memo")); 
		Transfer_reserveDTO dto= new Transfer_reserveDTO();
		dto.setAccount_number(request.getParameter("acc"));
		dto.setTo_account_number(request.getParameter("toAcc"));
		dto.setSum(request.getParameter("sum"));
		dto.setRegister_date(new Date());
		dto.setTime(request.getParameter("scheduled_date"));
		dto.setScheduled_date(request.getParameter("scheduled_date"));
		dto.setCms(request.getParameter("cms"));
		dto.setMemo(request.getParameter("memo"));
		dto.setTo_memo(request.getParameter("to_memo"));
		dto.setStatus("신청");
		
		
		String trs_time=request.getParameter("time")+" "+ request.getParameter("scheduled_date")+":00:00";
		
		new send( (String)session.getAttribute("userID"),"reserve","신청",trs_time);
		int seq = Transfer_reserveDAO.getInstance().reSeqInsert(dto);
		System.out.println(seq + "seq 값입니다.");
		System.out.println("Reg서비스들어온다");
		//리스트 정보를 키는 "data"로 어트리뷰트로 넘긴다
	}

}