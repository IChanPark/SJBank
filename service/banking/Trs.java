package banking;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inf.M_Action;
import jdbc.Account.AccountDAO;
import jdbc.Account.AccountDTO;
import jdbc.Transfer.Transfer_logDAO;
import jdbc.Transfer.Transfer_logDTO;
import jmodels.Transfer_reserveDAO;

public class Trs implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();



		Transfer_logDTO dto = new Transfer_logDTO();

		String acc = request.getParameter("acc");
		String toAcc = request.getParameter("toAcc");
		String pw = request.getParameter("accpw");
		
		String memo = request.getParameter("memo");
		String to_memo = request.getParameter("to_memo");
		if(!AccountDAO.getInstance().chkAccPw(acc, pw))
		{
			System.out.println("예외 발생?!!!!!");

			request.setAttribute("msg", "패스워드가 일치하지 않습니다...ByServelet");
			request.setAttribute("goUrl", "SJBank");
			request.setAttribute("do", "banking/Transfer");

			throw new Exception("패스워드 불일치!!!");
		}


		String  toName = AccountDAO.getInstance().chkOurBank(toAcc);
		
		AccountDTO fdto = AccountDAO.getInstance().selectAccount(acc);
	
		dto.setAccount_number(acc);
		dto.setTarget(request.getParameter("transfer_receive"));
		dto.setTo_account_number(toAcc);
		dto.setFeetype("즉시이체");
		dto.setReceived(toName);
		dto.setSum( (long)Integer.parseInt(request.getParameter("money") ) );

		if(dto.getTarget().toUpperCase().equals("SJBANK") ||  dto.getTarget().toUpperCase().equals("SJ은행") )
		{
			dto.setFee(0);
		}
		else {
			dto.setFee(500);
		}

		dto.setCms(request.getParameter("cms"));
		dto.setMemo(memo);
		dto.setTo_memo(to_memo);
		dto.setRegister_date(new Date());
		
		
		if(toName.equals("외부계좌") && ( dto.getTarget().toUpperCase().equals("SJBANK") 
				||  dto.getTarget().toUpperCase().equals("SJ은행")    )   )
		{

			request.setAttribute("msg", "이체 대상 계좌가 존재 하지 않습니다.");
			request.setAttribute("goUrl", "SJBank");
			request.setAttribute("do", "banking/Transfer");

			throw new Exception("패스워드 불일치!!!");
			
		}
		
		//////////////////자행 없는거 처리
		
		
		
		
		
		
		
		
		if(fdto.getSum()>dto.getSum()+dto.getFee()) {
			dto.setStatus("성공");
			AccountDAO.getInstance().updateMoney( (int)(-1 *dto.getSum()-1*dto.getFee() ) ,acc );
			//계좌 잔액 변경
			Transfer_logDTO trfDTO = new Transfer_logDTO();
			if( dto.getTarget().toUpperCase().equals("SJBANK") ||  dto.getTarget().toUpperCase().equals("SJ은행")   )
			{
				trfDTO.setAccount_number(toAcc);
				trfDTO.setTo_account_number(acc);
				trfDTO.setFee(0);
				trfDTO.setCms("");
				trfDTO.setFeetype("즉시입금");
				trfDTO.setMemo(to_memo);
				trfDTO.setTo_memo(memo);
				trfDTO.setReceived(toName);
				trfDTO.setRegister_date(new Date());
				trfDTO.setStatus("성공");
				trfDTO.setSum(dto.getSum());
				trfDTO.setTarget("SJBank");
				AccountDAO.getInstance().updateMoney( (int)(dto.getSum()+0),toAcc );
				Transfer_logDAO.getInstance().insert(trfDTO);
			}
	
		}
		else { 
			dto.setStatus("잔액부족");

		}
		Transfer_logDAO.getInstance().insert(dto);
		request.setAttribute("data", dto);




		System.out.println("trs서비스들어온다");
		//리스트 정보를 키는 "data"로 어트리뷰트로 넘긴다
	}

}