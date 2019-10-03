package jdbc.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class AccountDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;

	private AccountDAO() {
		ds = Data_Source.getInstance().getDs();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			Account(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}

	public ArrayList<AccountDTO> selectID(String id){
		ArrayList<AccountDTO> res = new ArrayList<AccountDTO>();

		sql = "select * from account where id = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			Account(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}

	public AccountDTO selectAccount(String acc){
		AccountDTO dto = null;

		sql = "select * from account where account_number = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, acc);

			rs = pstmt.executeQuery();

			dto = Account(rs, dto);
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return dto;
	}

	public void insert(AccountDTO dto){
		sql = 	"insert into account (" +
				"account_number, type, sum, name, id, pw, status, register_date, end_date) "+
				"values ("+
				"			?  ,	?,	 0,	 ''	,  ?,  ?, '활성' ,	  now(), 	 null)";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getAccount_number());
			pstmt.setString(2, dto.getType());
			pstmt.setString(3, dto.getId());
			pstmt.setString(4, dto.getPw());

			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}

	public void updateMoney(AccountDTO dto){
		sql = 	"update account set " +
				"money = ? " +
				"where account_number = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, dto.getSum());
			pstmt.setString(2, dto.getAccount_number());

			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}




	/////////////////////////////// 당행이체 계좌아이디 빼가기 10 04 

	public String getNamebyAcc(String acc){

		sql = "SELECT NAME FROM user WHERE id = "
				+ "(SELECT id FROM ACCOUNT WHERE account_number = ? )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, acc);

			rs = pstmt.executeQuery();

			if(rs.next())
				return rs.getString("name");
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return "외부계좌";
	}



	////////////////////////////////////// 계좌 비밀번호 맞는지 확인 10/4 05 : 45

	public boolean chkAccPw(String acc, String pw){

		sql = "SELECT pw FROM account WHERE account_number = ? ";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, acc);

			rs = pstmt.executeQuery();

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
		if(pstmt!=null) try {pstmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
		sql = null;
	}
}
