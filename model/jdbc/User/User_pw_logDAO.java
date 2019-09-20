package jdbc.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;


public class User_pw_logDAO {
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;

	private User_pw_logDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final User_pw_logDAO DAO = new User_pw_logDAO();
    }
	
	public static User_pw_logDAO getInstance() {
        return Holder.DAO;
    }

	private User_pw_logDTO User_pw_log(ResultSet rs, User_pw_logDTO dto) {
		try {
			if(rs.next()) {
				dto = new User_pw_logDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setId(rs.getString("id"));
				dto.setStatus(rs.getString("status"));				
				dto.setRegister_date(rs.getDate("register_date"));			
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void User_pw_log(ResultSet rs, ArrayList<User_pw_logDTO> res) {
		try {
			while (rs.next()) {
				User_pw_logDTO dto = new User_pw_logDTO();
				dto = new User_pw_logDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setId(rs.getString("id"));
				dto.setStatus(rs.getString("status"));				
				dto.setRegister_date(rs.getDate("register_date"));			
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<User_pw_logDTO> list(){
		ArrayList<User_pw_logDTO> res = new ArrayList<User_pw_logDTO>();
		
		sql = "select * from User_pw_log";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			User_pw_log(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public void insert(User_pw_logDTO dto){
		sql = 	"insert into User_pw_log (" +
				"id, status, register_date) values ("+
				" ?  ,	?  , now()		  )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());			
			pstmt.setString(2, dto.getStatus());	
			
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
