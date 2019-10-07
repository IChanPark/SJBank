package jdbc.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class User_mymenuDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private User_mymenuDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final User_mymenuDAO DAO = new User_mymenuDAO();
    }
	
	public static User_mymenuDAO getInstance() {
        return Holder.DAO;
    }

	private User_mymenuDTO User(ResultSet rs, User_mymenuDTO dto) {
		try {
			if(rs.next()) {
				dto = new User_mymenuDTO();
				dto.setId(rs.getString("id"));
				dto.setMymenu1(rs.getString("mymenu1"));
				dto.setMymenu2(rs.getString("mymenu2"));
				dto.setMymenu3(rs.getString("mymenu3"));
				dto.setMymenu4(rs.getString("mymenu4"));
				dto.setMymenu5(rs.getString("mymenu5"));
				
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void User(ResultSet rs, ArrayList<User_mymenuDTO> res) {
		try {
			while (rs.next()) {
				User_mymenuDTO dto = new User_mymenuDTO();
				dto.setId(rs.getString("id"));
				dto.setMymenu1(rs.getString("mymenu1"));
				dto.setMymenu2(rs.getString("mymenu2"));
				dto.setMymenu3(rs.getString("mymenu3"));
				dto.setMymenu4(rs.getString("mymenu4"));
				dto.setMymenu5(rs.getString("mymenu5"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<User_mymenuDTO> list(){
		ArrayList<User_mymenuDTO> res = new ArrayList<User_mymenuDTO>();
		
		sql = "select * from user_mymenu";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			User(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public User_mymenuDTO selectId(String id){
		User_mymenuDTO dto = null;
		
		sql = 	"select * from user_mymenu where id = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			dto = User(rs, dto);
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return dto;
	}
	
	
	public void insert(User_mymenuDTO dto){
		sql = 	"insert into user_mymenu (" +
				"id, mymenu1, mymenu2, mymenu3, mymenu4, mymenu5) "+
				"values "+
			    "(? ,? ,	?	 ,	?	, ? ,	 ? )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getMymenu1());
			pstmt.setString(3, dto.getMymenu2());
			pstmt.setString(4, dto.getMymenu3());
			pstmt.setString(5, dto.getMymenu4());
			pstmt.setString(6, dto.getMymenu5());
			
			
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	public void updateUser(User_mymenuDTO dto){
		sql = 	"update user_mymenu set " +
				"mymenu1 = ? , mymenu2= ?, mymenu3 = ?, mymenu4 = ?, mymenu5 = ? " +
				"where id	 = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getMymenu1());
			pstmt.setString(2, dto.getMymenu2());
			pstmt.setString(3, dto.getMymenu3());
			pstmt.setString(4, dto.getMymenu4());
			pstmt.setString(5, dto.getMymenu5());
			pstmt.setString(6, dto.getId());
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
