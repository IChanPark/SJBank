package jdbc.etc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class Credit_rating_logDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private Credit_rating_logDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final Credit_rating_logDAO DAO = new Credit_rating_logDAO();
    }
	
	public static Credit_rating_logDAO getInstance() {
        return Holder.DAO;
    }

	private Credit_rating_logDTO Credit_rating_log(ResultSet rs, Credit_rating_logDTO dto) {
		try {
			if(rs.next()) {
				dto = new Credit_rating_logDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setId(rs.getString("id"));
				dto.setChange(rs.getString("change"));
				dto.setReason(rs.getString("reason"));
				dto.setRating(rs.getInt("rating"));
				dto.setRegister_dateStr(rs.getString("register_date"));
				
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Credit_rating_log(ResultSet rs, ArrayList<Credit_rating_logDTO> res) {
		try {
			while (rs.next()) {
				Credit_rating_logDTO dto = new Credit_rating_logDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setId(rs.getString("id"));
				dto.setChange(rs.getString("change"));
				dto.setReason(rs.getString("reason"));
				dto.setRating(rs.getInt("rating"));
				dto.setRegister_dateStr(rs.getString("register_date"));
				
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<Credit_rating_logDTO> list(){
		ArrayList<Credit_rating_logDTO> res = new ArrayList<Credit_rating_logDTO>();
		
		sql = "select * from credit_rating_log";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Credit_rating_log(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public Credit_rating_logDTO selectSeq(int seq){
		Credit_rating_logDTO res = new Credit_rating_logDTO();
		
		sql = "select * from credit_rating_log where seq = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, seq);
			
			rs = pstmt.executeQuery();
			
			Credit_rating_log(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	
	
	public void insert(Credit_rating_logDTO dto){
		sql = 	"insert into Credit_rating_log (" +
				"id, change, reason, rating ,register_date) "+
				"values ("+
				"? ,	?  ,     ? ,	?   , now()	)";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getChange());
			pstmt.setString(3, dto.getReason());
			pstmt.setInt(4, dto.getRating());
			
			
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	public void update(Credit_rating_logDTO dto){
		sql = 	"update credit_rating_log set " +
				"change = ? ,  reason = ? ,rating = ? " +
				"where seq = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
						
			pstmt.setString(1, dto.getChange());
			pstmt.setString(2, dto.getReason());
			pstmt.setInt(3, dto.getRating());
			
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
