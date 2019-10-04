package banking;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inf.M_Action;
import jdbc.Account.AccountDAO;
import jdbc.Transfer.Transfer_logDAO;
import jdbc.Transfer.Transfer_logDTO;

public class Trs implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		Transfer_logDTO dto = new Transfer_logDTO();
		System.out.println(request.getParameter("acc"));
		System.out.println(request.getParameter("accpw"));
		try {
			System.out.println(AccountDAO.getInstance().getNamebyAcc(request.getParameter("toAcc")));
			System.out.println(AccountDAO.getInstance().chkAccPw(request.getParameter("acc"), request.getParameter("accpw")));

			if(AccountDAO.getInstance().chkAccPw(request.getParameter("acc"), request.getParameter("accpw")))
			{
				dto.setAccount_number(request.getParameter("acc"));
				dto.setSelf("본인");
				dto.setTarget(request.getParameter("transfer_receive"));
				dto.setTo_account_number(request.getParameter("toAcc"));
				dto.setReceived( AccountDAO.getInstance().getNamebyAcc(request.getParameter("toAcc"))   );
				dto.setSum( Integer.parseInt(request.getParameter("money") ) );
				dto.setFee(0);
				dto.setCms(request.getParameter("cms"));
				dto.setMemo(request.getParameter("memo"));
				dto.setTo_memo(request.getParameter("to_memo"));
				dto.setStatus("성공");
				dto.setRegister_date(new Date());
				System.out.println("여기옴 TRS");
				Transfer_logDAO.getInstance().insert(dto);
				AccountDAO.getInstance().updateMoney( -1*Integer.parseInt(request.getParameter("money")), request.getParameter("acc") );
				
				if(!AccountDAO.getInstance().getNamebyAcc(request.getParameter("toAcc")).equals("외부계좌") )
				{
					AccountDAO.getInstance().updateMoney( Integer.parseInt(request.getParameter("money")), request.getParameter("toAcc"));
				}
					
			}
			else
			{
				request.setAttribute("msg", "실패했습니다.");
				request.setAttribute("mainUrl", "main");
				request.setAttribute("goUrl", "index.jsp");
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