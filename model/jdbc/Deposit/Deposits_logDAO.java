package jdbc.Deposit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class Deposits_logDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private Deposits_logDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final Deposits_logDAO DAO = new Deposits_logDAO();
    }
	
	public static Deposits_logDAO getInstance() {
        return Holder.DAO;
    }

	private Deposits_logDTO Deposits_info(ResultSet rs, Deposits_logDTO dto) {
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
	
	private void Deposits_info(ResultSet rs, ArrayList<Deposits_logDTO> res) {
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Deposits_info(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public void insert(Deposits_logDTO dto){
		sql = 	"insert into deposits_log (" +
				"account_number, Interest, sum, status, register_date) values ("+
				"		?	   ,	?	 ,	? ,		? ,	now() )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getAccount_number());
			pstmt.setFloat(2, dto.getInterest());
			pstmt.setInt(3, dto.getSum());
			pstmt.setString(4, dto.getStatus());
			
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
