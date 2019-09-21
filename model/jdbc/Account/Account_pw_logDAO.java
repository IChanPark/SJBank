package jdbc.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class Account_pw_logDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private Account_pw_logDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final Account_pw_logDAO DAO = new Account_pw_logDAO();
    }
	
	public static Account_pw_logDAO getInstance() {
        return Holder.DAO;
    }

	private Account_pw_logDTO Account_pw_log(ResultSet rs, Account_pw_logDTO dto) {
		try {
			if(rs.next()) {
				dto = new Account_pw_logDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setAccount_number(rs.getString("account_number"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Account_pw_log(ResultSet rs, ArrayList<Account_pw_logDTO> res) {
		try {
			while (rs.next()) {
				Account_pw_logDTO dto = new Account_pw_logDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setAccount_number(rs.getString("account_number"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<Account_pw_logDTO> list(){
		ArrayList<Account_pw_logDTO> res = new ArrayList<Account_pw_logDTO>();
		
		sql = "select * from account_pw_log";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Account_pw_log(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
		
	
	public Account_pw_logDTO selectAccount(String acc){
		Account_pw_logDTO dto = null;
		
		sql = "select * from account where account_number = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, acc);
			
			rs = pstmt.executeQuery();
			
			dto = Account_pw_log(rs, dto);
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return dto;
	}
	
	public void insert(Account_pw_logDTO dto){
		sql = 	"insert into account (" +
				"account_number, status, register_date) "+
				"values ("+
				"		?      ,  ?	   ,	    now()     )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getAccount_number());
			pstmt.setString(2, dto.getStatus());
			
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	
	void close() {
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(pstmt!=null) try {pstmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
		sql = null;
	}
}
