package prodsaving;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;
import jdbc.Saving.Saving_logDTO;
import server.DBAccess_IP;

public class Saving_logDAO {
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String sql;
	
	public Saving_logDAO() {
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
        public static final Saving_logDAO DAO = new Saving_logDAO();
    }
	
	public static Saving_logDAO getInstance() {
        return Holder.DAO;
    }

	private Saving_logDTO Saving_log(ResultSet rs, Saving_logDTO dto) {
		try {
			if(rs.next()) {
				dto = new Saving_logDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setAccount_number(rs.getString("account_number"));
				dto.setInterest(rs.getFloat("interest"));
				dto.setSum(rs.getLong("sum"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
		
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Saving_log(ResultSet rs, ArrayList<Saving_logDTO> res) {
		try {
			while (rs.next()) {
				Saving_logDTO dto = new Saving_logDTO();

				dto.setSeq(rs.getInt("seq"));
				dto.setAccount_number(rs.getString("account_number"));
				dto.setInterest(rs.getFloat("interest"));
				dto.setSum(rs.getLong("sum"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
				
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<Saving_logDTO> list(){
		ArrayList<Saving_logDTO> res = new ArrayList<Saving_logDTO>();
		
		sql = "select * from saving_log";
		try {
			
			rs = stmt.executeQuery(sql);
			
			Saving_log(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public void insert(Saving_logDTO dto){
		sql = 	"insert into saving_log (" +
				"account_number, Interest, sum, status, register_date) values ('"+
				dto.getAccount_number()+"',"+  dto.getInterest()+","+dto.getSum()+",'"+ dto.getStatus()+"', now() )";
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
