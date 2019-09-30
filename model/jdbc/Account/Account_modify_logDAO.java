package jdbc.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class Account_modify_logDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private Account_modify_logDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final Account_modify_logDAO DAO = new Account_modify_logDAO();
    }
	
	public static Account_modify_logDAO getInstance() {
        return Holder.DAO;
    }

	private Account_modify_logDTO Account_modify_log(ResultSet rs, Account_modify_logDTO dto) {
		try {
			if(rs.next()) {
				dto = new Account_modify_logDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setAccount_number(rs.getString("account_number"));
				dto.setPw(rs.getString("pw"));
				dto.setModify_pw(rs.getString("modify_pw"));
				dto.setRegister_date(rs.getDate("register_date"));
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Account_modify_log(ResultSet rs, ArrayList<Account_modify_logDTO> res) {
		try {
			while (rs.next()) {
				Account_modify_logDTO dto = new Account_modify_logDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setAccount_number(rs.getString("account_number"));
				dto.setPw(rs.getString("pw"));
				dto.setModify_pw(rs.getString("modify_pw"));
				dto.setRegister_date(rs.getDate("register_date"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<Account_modify_logDTO> list(){
		ArrayList<Account_modify_logDTO> res = new ArrayList<Account_modify_logDTO>();
		
		sql = "select * from account_modify_log";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Account_modify_log(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
		
	
	public Account_modify_logDTO selectAccount(String acc){
		Account_modify_logDTO dto = null;
		
		sql = "select * from account_modify_log where account_number = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, acc);
			
			rs = pstmt.executeQuery();
			
			dto = Account_modify_log(rs, dto);
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return dto;
	}
	
	public void insert(Account_modify_logDTO dto){
		sql = 	"insert into Account_modify_log (" +
				"seq, pw,account_number, modify_pw, register_date) "+
				"values ("+
				"? ,  ?	,	?     ,    ?	 ,	    now()     )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getSeq());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getAccount_number());
			pstmt.setString(4, dto.getModify_pw());
			
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
