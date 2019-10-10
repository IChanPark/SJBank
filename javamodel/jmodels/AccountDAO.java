package jmodels;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import jdbc.Account.AccountDTO;
import server.DBAccess_IP;

public class AccountDAO {

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String sql;

	public AccountDAO() {
		try {
			String url ="jdbc:mariadb://"+DBAccess_IP.getInstance().getIP()+":3306/bank";
			String id = "bank";
			String pw = "1234";
			
			Class.forName("org.mariadb.jdbc.Driver");
		
			con = DriverManager.getConnection(url,id,pw);
			stmt = con.createStatement();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static class Holder {
		public static final AccountDAO DAO = new AccountDAO();
	}

	public static AccountDAO getInstance() {
		return Holder.DAO;
	}

	private AccountDTO Account(ResultSet rs, AccountDTO dto) {
		try {
			if(rs.next()) {
				dto = new AccountDTO();
				dto.setAccount_number(rs.getString("account_number"));
				dto.setType(rs.getString("type"));
				dto.setSum(rs.getInt("sum"));
				dto.setAlias(rs.getString("alias"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
				dto.setEnd_date(rs.getDate("end_date"));
			} 
		} catch (Exception e) {}
		return dto;
	}

	private void Account(ResultSet rs, ArrayList<AccountDTO> res) {
		try {
			while (rs.next()) {
				AccountDTO dto = new AccountDTO();
				dto.setAccount_number(rs.getString("account_number"));
				dto.setType(rs.getString("type"));
				dto.setSum(rs.getInt("sum"));
				dto.setAlias(rs.getString("alias"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
				dto.setEnd_date(rs.getDate("end_date"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}

	public ArrayList<AccountDTO> list(){
		ArrayList<AccountDTO> res = new ArrayList<AccountDTO>();

		sql = "select * from account";
		try {
			rs = stmt.executeQuery(sql);

			Account(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}

	public ArrayList<AccountDTO> selectID(String id){
		ArrayList<AccountDTO> res = new ArrayList<AccountDTO>();

		sql = "select * from account where id = '"+id+"'";
		System.out.println(sql);
		try {
			rs = stmt.executeQuery(sql);
			Account(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}

	public AccountDTO selectAccount(String acc){
		AccountDTO dto = null;

		sql = "select * from account where account_number = '"+acc+"'";
		System.out.println(sql);
		try {
			rs = stmt.executeQuery(sql);

			dto = Account(rs, dto);
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return dto;
	}
	
	public ArrayList<AccountDTO> selectID_Type(AccountDTO DataDTO){
		ArrayList<AccountDTO> res = new ArrayList<AccountDTO>();

		sql = "select * from account where id = '"+ DataDTO.getId()+"' and type = '" +DataDTO.getType()+"'";
		System.out.println(sql);
		try {			
			rs = stmt.executeQuery(sql);

			Account(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}

	public void insert(AccountDTO dto){
		sql = 	"insert into account (" +
				"account_number, type, sum, alias, id, pw, status, register_date, end_date) "+
				"values ('"+dto.getAccount_number()+"','"+dto.getType()+
				"',"+dto.getSum()+",'"+dto.getAlias()+"','"+dto.getId()+
				"','"+dto.getPw()+"','활성',now(),null)";

		System.out.println(sql);
		try {
			stmt.executeUpdate(sql); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}

	public void updateMoney(AccountDTO dto){
		sql = 	"update account set " +
				"sum = "+dto.getSum() +
				"  where account_number = '"+dto.getAccount_number()+"'";
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}

	///////////////////////// 계좌정보 다 넣기가 귀찮아서 일단 계좌 번호만으로 업데이트
	
	public void updateMoney(int money,String acc){
		sql = 	"update account set " +
				"sum = sum + " +money+
				" where account_number = '" +acc+"'";
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	


	/////////////////////////////// 당행이체 계좌아이디 빼가기 10 04 

	public String getAliasbyAcc(String acc){

		sql = "SELECT alias FROM account WHERE id = "
				+ "(SELECT id FROM ACCOUNT WHERE account_number = '"+acc+"' )";
		System.out.println(sql);
		try {
			 rs = stmt.executeQuery(sql);

			if(rs.next())
				return rs.getString("alias");
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return "외부계좌";
	}

	
	///////////////////////
	
	public String chkOurBank(String acc){

		sql = "SELECT name FROM user WHERE id = (select id from account where account_number = '"+acc+"' )";
		System.out.println(sql);
		try {
			 rs = stmt.executeQuery(sql);

			if(rs.next())
				return rs.getString("name");
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return "외부계좌";
	}

	
	
	
	
	
	
	
	
	//////////////////////////////


	////////////////////////////////////// 계좌 비밀번호 맞는지 확인 10/4 05 : 45

	public boolean chkAccPw(String acc, String pw){

		sql = "SELECT pw FROM account WHERE account_number = '"+acc+"'";
		System.out.println(sql);
		try {
			rs = stmt.executeQuery(sql);

			if(rs.next())
			{
				if(rs.getString("pw").equals(pw))
					return true;
				if(rs.getString("pw").equals("")|| rs.getString("pw")==null )
					return true;
			}
			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return false;
	}

	
	void close() {
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(stmt!=null) try {stmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
		sql = null;
	}
}
