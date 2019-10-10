package management.accmanag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Account.AccountDAO;
import jdbc.Account.AccountDTO;

public class UpdatePw  implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AccountDTO dto = new AccountDTO();
		
		dto.setPw(request.getParameter("pw"));//1
		dto.setStatus(request.getParameter("status"));//2
		dto.setAlias(request.getParameter("alias"));//3
		dto.setAccount_number(request.getParameter("account_number"));//4
		
		AccountDAO.getInstance().updatePw(dto);
		request.setAttribute("mainUrl", "main");

	}
}