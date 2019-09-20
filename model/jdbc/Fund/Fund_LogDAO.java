package jdbc.Fund;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class Fund_LogDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private Fund_LogDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final Fund_LogDAO DAO = new Fund_LogDAO();
    }
	
	public static Fund_LogDAO getInstance() {
        return Holder.DAO;
    }

	private Fund_LogDTO Fund_Log(ResultSet rs, Fund_LogDTO dto) {
		try {
			if(rs.next()) {
				dto = new Fund_LogDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setAccount_number(rs.getString("account_number"));
				dto.setFluctuation(rs.getFloat("fluctuation"));
				dto.setSum(rs.getInt("sum"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
				
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Fund_Log(ResultSet rs, ArrayList<Fund_LogDTO> res) {
		try {
			while (rs.next()) {
				Fund_LogDTO dto = new Fund_LogDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setAccount_number(rs.getString("account_number"));
				dto.setFluctuation(rs.getFloat("fluctuation"));
				dto.setSum(rs.getInt("sum"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<Fund_LogDTO> list(){
		ArrayList<Fund_LogDTO> res = new ArrayList<Fund_LogDTO>();
		
		sql = "select * from fund_log";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Fund_Log(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public ArrayList<Fund_LogDTO> selectAccountLog(String account){
		ArrayList<Fund_LogDTO> res = new ArrayList<Fund_LogDTO>();
		
		sql = "select * from fund_log where account_number = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, account);
			
			rs = pstmt.executeQuery();
			
			Fund_Log(rs, res);	
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	

	public void insert(Fund_LogDTO dto){
		sql = 	"insert into account (" +
				"account_number, fluctuation, sum, status, register_date) values ("+ 
				"	?  	       ,	 ?      ,  ? ,    ?   , now() )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getSeq());
			pstmt.setString(2, dto.getAccount_number());
			pstmt.setFloat(3, dto.getFluctuation());
			pstmt.setInt(4, dto.getSum());
			pstmt.setString(5, dto.getStatus());
		
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
