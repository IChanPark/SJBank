package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inf.M_Action;
import jdbc.Account.AccountDAO;
import jdbc.Account.AccountDTO;
import jdbc.Deposit.DepositsDAO;
import jdbc.Deposit.DepositsDTO;
import jdbc.User.UserDAO;
import jdbc.User.UserDTO;
import util.New_Account;

public class joinReg  implements M_Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("올체크 reg드ㅜㄹ어온다");
		UserDTO dto = new UserDTO();

		
		System.out.println(request.getParameter("id"));
		System.out.println("pw \t"+request.getParameter("pw"));
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		dto.setSimple_pw(Integer.parseInt(request.getParameter("simple_pw")));	
		dto.setName(request.getParameter("name"));
		dto.setTel(request.getParameter("tel"));
		dto.setGen(request.getParameter("gen"));
		dto.setEmail(request.getParameter("email1")+"@"+request.getParameter("email2"));		
		dto.setJob_group(request.getParameter("position"));
		dto.setAddr(request.getParameter("addr")+"/"+request.getParameter("datail"));		
		dto.setPostal_code(request.getParameter("zipcode"));
		
		//계좌번호 넣어주세요
		
		AccountDTO dt = new AccountDTO();
		dt.setAccount_number(New_Account.getInstance().getAccount());
		dt.setType("예금");
		dt.setSum(0);
		dt.setAlias("");
		dt.setId(dto.getId());
		dt.setPw(request.getParameter("acc_pw"));
		
		AccountDAO.getInstance().insert(dt);
		
		DepositsDTO ddto = new DepositsDTO();
		
		ddto.setAccount_number(dt.getAccount_number());
		ddto.setId(dto.getId());
		ddto.setProduct("기본");
		ddto.setPreferential("");
		ddto.setInterest(0.9f);
		ddto.setType("보통");
		
		DepositsDAO.getInstance().insert(ddto);
		
		//넣어주세요
		
		UserDAO.getInstance().insert(dto); 
		request.setAttribute("mainUrl", "main");

	}
}