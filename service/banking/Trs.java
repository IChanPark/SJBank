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
		System.out.println(request.getParameter("acc"));
		System.out.println(request.getParameter("accpw"));
		try {
			System.out.println(AccountDAO.getInstance().getAliasbyAcc(request.getParameter("toAcc")));
			System.out.println(AccountDAO.getInstance().chkAccPw(request.getParameter("acc"), request.getParameter("accpw")));

			
			String acc = request.getParameter("acc");
			String toAcc = request.getParameter("toAcc");
			
			String memo = request.getParameter("memo");
			String to_memo = request.getParameter("to_memo");
			
			
			
			if(AccountDAO.getInstance().chkAccPw(request.getParameter("acc"), request.getParameter("accpw")))
			{
				String  toName = AccountDAO.getInstance().chkOurBank(toAcc);
				
				AccountDTO fdto = AccountDAO.getInstance().selectAccount(acc);
				dto.setAccount_number(acc);
				dto.setTarget(request.getParameter("transfer_receive"));
				dto.setTo_account_number(toAcc);
				dto.setFeetype("즉시이체");
				dto.setReceived(toName);
				dto.setSum( (long)Integer.parseInt(request.getParameter("money") ) );
				
				
				if(!toName.equals("외부계좌"))
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
				
				
				if(fdto.getSum()>dto.getSum()+dto.getFee()) {
					dto.setStatus("성공");
					AccountDAO.getInstance().updateMoney( (int)(-1 *dto.getSum()-1*dto.getFee() ) ,acc );
					//계좌 잔액 변경
					if(!toName.equals("외부계좌"))
					{
						Transfer_logDTO trfDTO = new Transfer_logDTO();
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
						Transfer_logDAO.getInstance().insert(dto);
						Transfer_logDAO.getInstance().insert(trfDTO);
					}
				}
				else {
					dto.setStatus("잔액부족");
					Transfer_logDAO.getInstance().insert(dto);
				}
			}
			else
			{
				request.setAttribute("msg", "비밀번호 오류로 실패했습니다.");
				request.setAttribute("mainUrl", "main");
				request.setAttribute("goUrl", "SJBank");
				RequestDispatcher dispatcher = request.getRequestDispatcher("alert.jsp"); 
				dispatcher.forward(request, response);
				return;
			}
			

		} catch (Exception e) {
			// TODO: handle exception
			
		}

		
		
		System.out.println("trs서비스들어온다");
		//리스트 정보를 키는 "data"로 어트리뷰트로 넘긴다
	}

}