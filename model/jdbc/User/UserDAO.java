package jdbc.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class UserDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private UserDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final UserDAO DAO = new UserDAO();
    }
	
	public static UserDAO getInstance() {
        return Holder.DAO;
    }

	private UserDTO User(ResultSet rs, UserDTO dto) {
		try {
			if(rs.next()) {
				dto = new UserDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setSimple_pw(rs.getInt("simple_pw"));
				dto.setName(rs.getString("name"));
				dto.setGen(rs.getString("gen"));
				dto.setFamily(rs.getString("family"));
				dto.setJob_group(rs.getString("job_group"));
				dto.setIncome(rs.getString("income"));
				dto.setResidence(rs.getString("residence"));
				dto.setCounty(rs.getString("county"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
				dto.setRegister_date(rs.getDate("end_date"));
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void User(ResultSet rs, ArrayList<UserDTO> res) {
		try {
			while (rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setSimple_pw(rs.getInt("simple_pw"));
				dto.setName(rs.getString("name"));
				dto.setGen(rs.getString("gen"));
				dto.setFamily(rs.getString("family"));
				dto.setJob_group(rs.getString("job_group"));
				dto.setIncome(rs.getString("income"));
				dto.setResidence(rs.getString("residence"));
				dto.setCounty(rs.getString("county"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
				dto.setRegister_date(rs.getDate("end_date"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<UserDTO> list(){
		ArrayList<UserDTO> res = new ArrayList<UserDTO>();
		
		sql = "select * from UserDTO";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			User(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public UserDTO selectId(String id){
		UserDTO dto = null;
		
		sql = 	"select * from menu where id = ?";
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
	

	
	public void insert(UserDTO dto){
		sql = 	"insert into User (" +
				"id, pw, simple_pw, name, gen, family, job_group, income,"+ 
				"residence, county,status,register_date,end_date) "+
				"values "+
			    "(? ,? ,	?	 ,	?	 , ? ,	 ?	 ,   ?	 	, 	?	,"+
			    "	?	  ,    ?  ,   ?  ,  now()	   ,null )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setInt(3, dto.getSimple_pw());
			pstmt.setString(4, dto.getName());
			pstmt.setString(5, dto.getGen());
			pstmt.setString(6, dto.getFamily());
			pstmt.setString(7, dto.getJob_group());
			pstmt.setString(8, dto.getIncome());
			pstmt.setString(9, dto.getResidence());
			pstmt.setString(10, dto.getCounty());
			pstmt.setString(11, dto.getStatus());
			
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	public void updateUser(UserDTO dto){
		sql = 	"update user set " +
				"pw = ? , simple_pw= ?, name = ?, gen = ?, family = ? ,"+ 
				"job_group = ? , income = ? residence = ? " +
				"where id	 = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getPw());
			pstmt.setInt(2, dto.getSimple_pw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getGen());
			pstmt.setString(5, dto.getFamily());
			pstmt.setString(6, dto.getJob_group());
			pstmt.setString(7, dto.getIncome());
			pstmt.setString(8, dto.getResidence());
			pstmt.setString(9, dto.getId());
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
