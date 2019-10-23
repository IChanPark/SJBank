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
		
	
		String acc = request.getParameter("acc");
		String pw = request.getParameter("accpw");
		String target = request.getParameter("bank");
		
		String toAcc = request.getParameter("toAcc");
		String toName = AccountDAO.getInstance().chkOurBank(toAcc);
		
		if(target.toUpperCase().equals("SJ은행") || target.toUpperCase().equals("SJBANK"))
		 target = "SJBank";
		
		if(!AccountDAO.getInstance().chkAccPw(acc, pw))
		{
		
			request.setAttribute("msg", "패스워드가 일치하지 않습니다...ByServelet");
			throw new Exception("패스워드 불일치!!!");
		}
		
		
		if(toName.equals("외부계좌") && ( target.toUpperCase().equals("SJBANK") 
				||  target.toUpperCase().equals("SJ은행")    )   )
		{
			request.setAttribute("msg", "이체 대상 계좌가 존재 하지 않습니다.");
			throw new Exception("대상없음");
		}
				
		
		
		Transfer_reserveDTO dto= new Transfer_reserveDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		dto.setAccount_number(acc);
		dto.setTo_account_number(toAcc);
		dto.setSum(request.getParameter("sum"));
		dto.setTarget(target);
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
		
		request.setAttribute("data", dto);
	}

}