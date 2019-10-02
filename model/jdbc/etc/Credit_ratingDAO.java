package jdbc.etc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class Credit_ratingDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private Credit_ratingDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final Credit_ratingDAO DAO = new Credit_ratingDAO();
    }
	
	public static Credit_ratingDAO getInstance() {
        return Holder.DAO;
    }

	private Credit_ratingDTO Credit_rating(ResultSet rs, Credit_ratingDTO dto) {
		try {
			if(rs.next()) {
				dto = new Credit_ratingDTO();
				dto.setId(rs.getString("id"));
				dto.setRating(rs.getInt("rating"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_dateStr(rs.getString("register_date"));
				
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Credit_rating(ResultSet rs, ArrayList<Credit_ratingDTO> res) {
		try {
			while (rs.next()) {
				Credit_ratingDTO dto = new Credit_ratingDTO();
				
				dto.setId(rs.getString("id"));
				dto.setRating(rs.getInt("rating"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_dateStr(rs.getString("register_date"));
				
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<Credit_ratingDTO> list(){
		ArrayList<Credit_ratingDTO> res = new ArrayList<Credit_ratingDTO>();
		
		sql = "select * from credit_rating";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Credit_rating(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public Credit_ratingDTO selectID(String id){
		Credit_ratingDTO res = new Credit_ratingDTO();
		
		sql = "select * from credit_rating where id = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			Credit_rating(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	
	
	public void insert(Credit_ratingDTO dto){
		sql = 	"insert into credit_rating (" +
				"id, rating, status,register_date) "+
				"values ("+
				"? ,	?  ,     ? ,	 now()	)";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setInt(2, dto.getRating());
			pstmt.setString(3, dto.getStatus());
			
			
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	public void update(Credit_ratingDTO dto){
		sql = 	"update credit_rating set " +
				"rating = ? , status = ? " +
				"where id = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getRating());
			pstmt.setString(2, dto.getStatus());
			pstmt.setString(3, dto.getId());
			
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
