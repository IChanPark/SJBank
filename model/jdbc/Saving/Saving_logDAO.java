package jdbc.Saving;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class Saving_logDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private Saving_logDAO() {
		ds = Data_Source.getInstance().getDs();
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
				dto.setSum(rs.getInt("sum"));
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
				dto.setSum(rs.getInt("sum"));
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Saving_log(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public void insert(Saving_logDTO dto){
		sql = 	"insert into saving_log (" +
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
