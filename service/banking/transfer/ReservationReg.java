package banking.transfer;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inf.M_Action;
import jdbc.Account.AccountDAO;
import jdbc.Account.AccountDTO;
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
		String acc = request.getParameter("acc");
		String pw = request.getParameter("accpw");
		if(!AccountDAO.getInstance().chkAccPw(acc, pw))
		{
			System.out.println("예외 발생?!!!!!");

			request.setAttribute("msg", "패스워드가 일치하지 않습니다...ByServelet");
			request.setAttribute("goUrl", "SJBank");
			request.setAttribute("mainUrl", "main");
			throw new Exception("패스워드 불일치!!!");
		}
		
		
		Transfer_reserveDTO dto= new Transfer_reserveDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		dto.setAccount_number(request.getParameter("acc"));
		dto.setTo_account_number(request.getParameter("toAcc"));
		dto.setSum(request.getParameter("sum"));
		dto.setRegister_date(new Date());
		String trs_time=request.getParameter("time")+" "+ request.getParameter("scheduled_date");
		dto.setTimeStr(trs_time);
		dto.setScheduled_date(request.getParameter("scheduled_date"));
		dto.setCms(request.getParameter("cms"));
		dto.setMemo(request.getParameter("memo"));
		dto.setTo_memo(request.getParameter("to_memo"));
		dto.setStatus("활성");
		
		System.out.println(dto.getTimeStr()+"dto시간");
		
		new send("추가", "reserve", dto);
		
		System.out.println("Reg서비스들어온다");
		//리스트 정보를 키는 "data"로 어트리뷰트로 넘긴다
	}

}