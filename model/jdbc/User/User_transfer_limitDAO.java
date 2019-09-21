package jdbc.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;


public class User_transfer_limitDAO {
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;

	private User_transfer_limitDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final User_transfer_limitDAO DAO = new User_transfer_limitDAO();
    }
	
	public static User_transfer_limitDAO getInstance() {
        return Holder.DAO;
    }

	private User_transfer_limitDTO User_transfer_limit(ResultSet rs, User_transfer_limitDTO dto) {
		try {
			if(rs.next()) {
				dto = new User_transfer_limitDTO();
				dto.setId(rs.getString("id"));
				dto.setDaily_limit(rs.getInt("daily_limit"));				
				dto.setNumber_limit(rs.getInt("number_limit"));				
				
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void User_transfer_limit(ResultSet rs, ArrayList<User_transfer_limitDTO> res) {
		try {
			while (rs.next()) {
				User_transfer_limitDTO dto = new User_transfer_limitDTO();
				
				dto.setId(rs.getString("id"));
				dto.setDaily_limit(rs.getInt("daily_limit"));				
				dto.setNumber_limit(rs.getInt("number_limit"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<User_transfer_limitDTO> list(){
		ArrayList<User_transfer_limitDTO> res = new ArrayList<User_transfer_limitDTO>();
		
		sql = "select * from User_transfer_limit";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			User_transfer_limit(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public void insert(User_transfer_limitDTO dto){
		sql = 	"insert into User_transfer_limitDTO (" +
				"id, daily_limit, number_limit) values ("+
				" ?,	?	    ,		?	  )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());			
			pstmt.setInt(2, dto.getDaily_limit());	
			pstmt.setInt(3, dto.getNumber_limit());	
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
