package prodfund;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;
import jdbc.Fund.Fund_LogDTO;

public class Fund_LogDAO {

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String sql;
	
	public Fund_LogDAO() {
		try {
			String url ="jdbc:mariadb://192.168.1.14:3306/bank";
			String id = "bank";
			String pw = "1234";
			
			Class.forName("org.mariadb.jdbc.Driver");
		
			con = DriverManager.getConnection(url,id,pw);
			stmt = con.createStatement();
		} catch (Exception e) {
			// TODO: handle exception
		}
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
		
			rs = stmt.executeQuery(sql);
			
			Fund_Log(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public ArrayList<Fund_LogDTO> selectAccountLog(String account){
		ArrayList<Fund_LogDTO> res = new ArrayList<Fund_LogDTO>();
		
		sql = "select * from fund_log where account_number = '"+account+"'";
		System.out.println(sql);
		try {
			rs = stmt.executeQuery(sql);
			
			Fund_Log(rs, res);	
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	

	public void insert(Fund_LogDTO dto){
		sql = 	"insert into account (" +
				"account_number, fluctuation, sum, status, register_date) "
				+ "values ('"+dto.getAccount_number()+"',"+dto.getFluctuation()+","+
				dto.getSum() +",'"+dto.getStatus()+"', now() )";
						
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	void close() {
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(stmt!=null) try {stmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
		sql = null;
	}
}
