package jdbc.Membersheep;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;


public class MembersheepDAO {
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;

	private MembersheepDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final MembersheepDAO DAO = new MembersheepDAO();
    }
	
	public static MembersheepDAO getInstance() {
        return Holder.DAO;
    }

	private MembersheepDTO Membersheep(ResultSet rs, MembersheepDTO dto) {
		try {
			if(rs.next()) {
				dto = new MembersheepDTO();
				dto.setId(rs.getString("id"));
				dto.setMembersheep(rs.getString("membersheep"));
				dto.setPoint(rs.getInt("poing"));
				dto.setStatus(rs.getString("status"));				
				dto.setRegister_date(rs.getDate("register_date"));			
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Membersheep(ResultSet rs, ArrayList<MembersheepDTO> res) {
		try {
			while (rs.next()) {
				MembersheepDTO dto = new MembersheepDTO();
				dto = new MembersheepDTO();
				dto.setId(rs.getString("id"));
				dto.setMembersheep(rs.getString("membersheep"));
				dto.setPoint(rs.getInt("point"));
				dto.setStatus(rs.getString("status"));				
				dto.setRegister_date(rs.getDate("register_date"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<MembersheepDTO> list(){
		ArrayList<MembersheepDTO> res = new ArrayList<MembersheepDTO>();
		
		sql = "select * from Membersheep";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Membersheep(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public void insert(MembersheepDTO dto){
		sql = 	"insert into Membersheep (" +
				"id, membersheep, status, register_date) values ("+
				" ?  ,	 '일반',	   '활성',	 sysdate())";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());			
			
			
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
