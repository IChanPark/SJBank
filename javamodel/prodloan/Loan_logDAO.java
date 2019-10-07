package prodloan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;
import jdbc.Loan.Loan_logDTO;

public class Loan_logDAO {

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String sql;
	
	private Loan_logDAO() {
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
        public static final Loan_logDAO DAO = new Loan_logDAO();
    }
	
	public static Loan_logDAO getInstance() {
        return Holder.DAO;
    }

	private Loan_logDTO Loan_log(ResultSet rs, Loan_logDTO dto) {
		try {
			if(rs.next()) {
				dto = new Loan_logDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setAccount_number(rs.getString("account_number"));
				dto.setSum(rs.getInt("sum"));
				dto.setRemain(rs.getInt("remain"));
				dto.setStatus(rs.getString("status"));				
				dto.setRegister_date(rs.getDate("register_date"));
				
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Loan_log(ResultSet rs, ArrayList<Loan_logDTO> res) {
		try {
			while (rs.next()) {
				Loan_logDTO dto = new Loan_logDTO();
				dto = new Loan_logDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setAccount_number(rs.getString("account_number"));
				dto.setSum(rs.getInt("sum"));
				dto.setRemain(rs.getInt("remain"));
				dto.setStatus(rs.getString("status"));				
				dto.setRegister_date(rs.getDate("register_date"));
				
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<Loan_logDTO> list(){
		ArrayList<Loan_logDTO> res = new ArrayList<Loan_logDTO>();
		
		sql = "select * from loan_log";
		try {
			
			rs = stmt.executeQuery(sql);
			
			Loan_log(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public ArrayList<Loan_logDTO> selectAccount(String acc){
		ArrayList<Loan_logDTO> res = new ArrayList<Loan_logDTO>();
		
		sql = "select * from loan_log where account_number = '"+acc+"'";
		System.out.println(sql);
		try {
			
			rs = stmt.executeQuery(sql);
			
			Loan_log(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	
	public void insert(Loan_logDTO dto){
		sql = 	"insert into loan_log (" +
				"account_number , sum , remain ,status ,register_date )"+ 
				"values "+
				"('"+dto.getAccount_number()+"',"+ dto.getSum()+","+ dto.getRemain()
				+",'"+ dto.getStatus()+"',now())";
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
