package jdbc.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class User_status_logDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private User_status_logDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final User_status_logDAO DAO = new User_status_logDAO();
    }
	
	public static User_status_logDAO getInstance() {
        return Holder.DAO;
    }

	private User_status_logDTO User_status_log(ResultSet rs, User_status_logDTO dto) {
		try {
			if(rs.next()) {
				dto = new User_status_logDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setId(rs.getString("id"));
				dto.setType(rs.getString("type"));
				dto.setComment(rs.getString("comment"));
				dto.setRegister_date(rs.getDate("register_date"));
				
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void User_status_log(ResultSet rs, ArrayList<User_status_logDTO> res) {
		try {
			while (rs.next()) {
				User_status_logDTO dto = new User_status_logDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setId(rs.getString("id"));
				dto.setType(rs.getString("type"));
				dto.setComment(rs.getString("comment"));
				dto.setRegister_date(rs.getDate("register_date"));
				
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<User_status_logDTO> list(){
		ArrayList<User_status_logDTO> res = new ArrayList<User_status_logDTO>();
		
		sql = "select * from User_status_log";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			User_status_log(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public User_status_logDTO selectId(String id){
		User_status_logDTO dto = null;
		
		sql = 	"select * from User_status_log where id = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			dto = User_status_log(rs, dto);
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return dto;
	}
	

	
	public void insert(User_status_logDTO dto){
		sql = 	"insert into User_status_log (" +
				"id, type,comment,register_date"+ 
				"values "+
			    "(?,   ? ,	?	 ,	now() )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getType());
			pstmt.setString(3, dto.getComment());
			pstmt.setString(4, dto.getRegister_dateStr());
			
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	public void updateUser(User_status_logDTO dto){
		sql = 	"update user set " +
				"type = ? , comment= ?, register_date = ? " +
				"where seq	 = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getType());
			pstmt.setString(2, dto.getComment());
			pstmt.setString(3, dto.getRegister_dateStr());
			pstmt.setInt(4, dto.getSeq());
			
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
