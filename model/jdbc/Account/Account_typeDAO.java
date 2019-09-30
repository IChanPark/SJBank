package jdbc.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class Account_typeDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private Account_typeDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final Account_typeDAO DAO = new Account_typeDAO();
    }
	
	public static Account_typeDAO getInstance() {
        return Holder.DAO;
    }

	private Account_typeDTO Account_type(ResultSet rs, Account_typeDTO dto) {
		try {
			if(rs.next()) {
				dto = new Account_typeDTO();
				dto.setName(rs.getString("name"));
				dto.setType(rs.getString("type"));
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Account_type(ResultSet rs, ArrayList<Account_typeDTO> res) {
		try {
			while (rs.next()) {
				Account_typeDTO dto = new Account_typeDTO();
				
				dto.setName(rs.getString("name"));
				dto.setType(rs.getString("type"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<Account_typeDTO> list(){
		ArrayList<Account_typeDTO> res = new ArrayList<Account_typeDTO>();
		
		sql = "select * from Account_type";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Account_type(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	
	
	void close() {
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(pstmt!=null) try {pstmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
		sql = null;
	}
}
