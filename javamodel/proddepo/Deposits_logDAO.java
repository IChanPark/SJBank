package proddepo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;
import jdbc.Deposit.Deposits_logDTO;

public class Deposits_logDAO {
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String sql;
	
	private Deposits_logDAO() {
		
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
	
	private static class Holder {
        public static final Deposits_logDAO DAO = new Deposits_logDAO();
    }
	
	public static Deposits_logDAO getInstance() {
        return Holder.DAO;
    }

	private Deposits_logDTO Deposits_log(ResultSet rs, Deposits_logDTO dto) {
		try {
			if(rs.next()) {
				dto = new Deposits_logDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setAccount_number(rs.getString("account_number"));
				dto.setInterest(rs.getFloat("interest"));
				dto.setSum(rs.getInt("sum"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
		
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Deposits_log(ResultSet rs, ArrayList<Deposits_logDTO> res) {
		try {
			while (rs.next()) {
				Deposits_logDTO dto = new Deposits_logDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setAccount_number(rs.getString("account_number"));
				dto.setInterest(rs.getFloat("interest"));
				dto.setSum(rs.getInt("sum"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<Deposits_logDTO> list(){
		ArrayList<Deposits_logDTO> res = new ArrayList<Deposits_logDTO>();
		
		sql = "select * from deposits_log";
		try {
		
			rs = stmt.executeQuery(sql);
			
			Deposits_log(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public void insert(Deposits_logDTO dto){
		sql = 	"insert into deposits_log (" +
				"account_number, Interest, sum, status, register_date) "
				+ "values ('"+dto.getAccount_number()+"',"+
				dto.getInterest()+","+dto.getSum()+",'"+dto.getStatus()+"',now() )";
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
