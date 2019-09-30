package jdbc.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class User_analysis_logDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private User_analysis_logDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final User_analysis_logDAO DAO = new User_analysis_logDAO();
    }
	
	public static User_analysis_logDAO getInstance() {
        return Holder.DAO;
    }

	private User_analysis_logDTO User_analysis_log(ResultSet rs, User_analysis_logDTO dto) {
		try {
			if(rs.next()) {
				dto = new User_analysis_logDTO();
			
				dto.setSeq(rs.getInt("seq"));
				dto.setId(rs.getString("id"));
				dto.setCategory(rs.getString("category"));
				dto.setScore(rs.getString("score"));
				dto.setTotal(rs.getFloat("total"));
				dto.setPropensity(rs.getString("propensity"));
				dto.setRegister_date(rs.getDate("register_date"));
				
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void User_analysis_log(ResultSet rs, ArrayList<User_analysis_logDTO> res) {
		try {
			while (rs.next()) {
				User_analysis_logDTO dto = new User_analysis_logDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setId(rs.getString("id"));
				dto.setCategory(rs.getString("category"));
				dto.setScore(rs.getString("score"));
				dto.setTotal(rs.getFloat("total"));
				dto.setPropensity(rs.getString("propensity"));
				dto.setRegister_date(rs.getDate("register_date"));
				
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<User_analysis_logDTO> list(){
		ArrayList<User_analysis_logDTO> res = new ArrayList<User_analysis_logDTO>();
		
		sql = "select * from User_analysis_log";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			User_analysis_log(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public ArrayList<User_analysis_logDTO> selectId(String id){
		ArrayList<User_analysis_logDTO> res = new ArrayList<User_analysis_logDTO>();
		
		sql = 	"select * from user where id = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			User_analysis_log(rs, res);
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	

	
	public void insert(User_analysis_logDTO dto){
		sql = 	"insert into User_analysis_log (" +
				"id, category, score, total, propensity, register_date"+ 
				"values "+
			    "(?,	?    ,?	    ,	?  ,  ?        ,  	now()  )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getCategory());
			pstmt.setString(3, dto.getScore());
			pstmt.setFloat(4, dto.getTotal());
			pstmt.setString(5, dto.getPropensity());
			
			
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	public void updateUser(User_analysis_logDTO dto){
		sql = 	"update User_analysis_log set " +
				"id = ? , category= ?, score = ?, total = ? , propensity= ? , "+ 
				"register_date = ? " +
				"where seq	 = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getCategory());
			pstmt.setString(3, dto.getScore());
			pstmt.setFloat(4, dto.getTotal());
			pstmt.setString(5, dto.getPropensity());
			pstmt.setString(6, dto.getRegister_dateStr());
			
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
