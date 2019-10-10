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
				dto.setTel(rs.getString("tel"));
				dto.setGen(rs.getString("gen"));
				dto.setEmail(rs.getString("email"));
				dto.setJob_group(rs.getString("job_group"));
				dto.setAddr(rs.getString("addr"));
				dto.setPostal_code(rs.getInt("postal_code"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
				dto.setEnd_date(rs.getDate("end_date"));
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
				dto.setTel(rs.getString("tel"));
				dto.setGen(rs.getString("gen"));
				dto.setEmail(rs.getString("email"));
				dto.setJob_group(rs.getString("job_group"));
				dto.setAddr(rs.getString("addr"));
				dto.setPostal_code(rs.getInt("postal_code"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
				dto.setEnd_date(rs.getDate("end_date"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<UserDTO> list(){
		ArrayList<UserDTO> res = new ArrayList<UserDTO>();
		
		sql = "select * from user";
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
		
		sql = 	"select * from user where id = ?";
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
		sql = 	"insert into user (" +
				"id, pw, simple_pw, name, tel, gen, email, job_group,"+ 
				"addr, postal_code, status,register_date,end_date) "+
				"values "+
			    "(? ,? ,	?	 ,	?	, ? ,	 ?,	  ?	 , 	?	,"+
			    "  ? ,    ?  	  ,   '활성'  ,  	now()   ,null )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setInt(3, dto.getSimple_pw());
			pstmt.setString(4, dto.getName());
			pstmt.setString(5, dto.getTel());
			pstmt.setString(6, dto.getGen());
			pstmt.setString(7, dto.getEmail());
			pstmt.setString(8, dto.getJob_group());
			pstmt.setString(9, dto.getAddr());
			pstmt.setInt(10, dto.getPostal_code());
			
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	public void updateUser(UserDTO dto){
		sql = 	"update user set " +
				"pw = ? , simple_pw= ?, tel = ?, gen = ?, email = ? ,"+ 
				"job_group = ? , addr = ?, postal_code = ? " +
				"where id = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getPw());
			pstmt.setInt(2, dto.getSimple_pw());
			pstmt.setString(3, dto.getTel());
			pstmt.setString(4, dto.getGen());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getJob_group());
			pstmt.setString(7, dto.getAddr());
			pstmt.setInt(8, dto.getPostal_code());
			pstmt.setString(9, dto.getId());
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	
	////////////////////////////// 1001추가 아이디에 따른 패스워드 체크 HDG
	
	public boolean chkPw(String id,String pw){
		boolean res = false;
		
		sql = 	"select pw from user where id = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				if(rs.getString("pw").equals(pw))
					res=true;
			}
				
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	////상태변경
	public void changeStatus(String status, String id){
		sql = 	"update user set " +
				"status = ? "  +
				"where id	 = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, status);
			pstmt.setString(2, id);
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	

	//////////////////10-09 아이디 search
	
	public UserDTO searchId(UserDTO dto){
		
		sql = 	"select * from user " + 
				"where name = ? and tel = ? and email = ?";	
		
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1 ,dto.getName());
			pstmt.setString(2 ,dto.getTel());
			pstmt.setString(3 ,dto.getEmail());
			
			rs = pstmt.executeQuery();
			dto = User(rs, dto);
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return dto;
	}
	
	public void updatePw(UserDTO dto){
		sql = 	"update user set " +
				"pw = ? "+
				"where id = ? and name = ? and tel = ? and email = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getTel());
			pstmt.setString(5, dto.getEmail());
			
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
