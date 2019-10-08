<%@page import="jdbc.Fund.Fund_InfoDAO"%>
<%@page import="jdbc.Fund.Fund_InfoDTO"%>
<%@page import="jdbc.Deposit.Deposits_infoDAO"%>
<%@page import="jdbc.Deposit.Deposits_infoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.Account.AccountDTO"%>
<%@page import="jdbc.Account.AccountDAO"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");	//한글처리
Map<String,String> map = new HashMap<String,String>();
Gson gson = new Gson();
AccountDTO DataDTO = new AccountDTO();
DataDTO.setId((String)request.getSession().getAttribute("userID"));
DataDTO.setType("펀드");
ArrayList<AccountDTO> accDTO = AccountDAO.getInstance().selectID_Type(DataDTO);

Fund_InfoDTO setDTO = new Fund_InfoDTO();
setDTO.setProduct(request.getParameter("product"));
System.out.println(request.getParameter("product"));
Fund_InfoDTO dto = Fund_InfoDAO.getInstance().selectProUse(setDTO);
map.put("product", dto.getProduct());
map.put("product", dto.getProduct());
map.put("product_info", dto.getProduct_info());
map.put("price", dto.getPrice()+"");
map.put("price_modify", dto.getPrice_modify()+"");
map.put("area", dto.getArea());
map.put("type", dto.getType());
map.put("property",dto.getProperty());
map.put("tax", dto.getTax());
map.put("first_fee",dto.getFirst_fee()+"");
map.put("fee",dto.getFee()+"");
map.put("management",dto.getManagement());
map.put("sector",dto.getSector());
map.put("register_date",dto.getRegister_dateStr());

String acc = "";
String alias ="";
for (int i = 0; i < accDTO.size(); i++) {
	acc+= accDTO.get(i).getAccount_number();
	alias += accDTO.get(i).getAlias();
	if(i < accDTO.size()-1){
		acc +="#";
		alias +="#";	
	}
}
map.put("account_number",acc);
map.put("alias",alias);
String json = gson.toJson(map);
out.print(json);
%>