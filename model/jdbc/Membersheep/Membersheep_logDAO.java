package jdbc.Membersheep;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;


public class Membersheep_logDAO {
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;

	private Membersheep_logDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final Membersheep_logDAO DAO = new Membersheep_logDAO();
    }
	
	public static Membersheep_logDAO getInstance() {
        return Holder.DAO;
    }

	private Membersheep_logDTO Membersheep_log(ResultSet rs, Membersheep_logDTO dto) {
		try {
			if(rs.next()) {
				dto = new Membersheep_logDTO();
				dto.setId(rs.getString("id"));
				dto.setWay(rs.getString("way"));
				dto.setSeq(rs.getInt("seq"));
				dto.setPoint(rs.getInt("point"));
				dto.setStatus(rs.getString("status"));				
				dto.setRegister_date(rs.getDate("register_date"));			
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Membersheep_log(ResultSet rs, ArrayList<Membersheep_logDTO> res) {
		try {
			while (rs.next()) {
				Membersheep_logDTO dto = new Membersheep_logDTO();
				dto = new Membersheep_logDTO();
				dto.setId(rs.getString("id"));
				dto.setWay(rs.getString("way"));
				dto.setSeq(rs.getInt("seq"));
				dto.setPoint(rs.getInt("point"));
				dto.setStatus(rs.getString("status"));				
				dto.setRegister_date(rs.getDate("register_date"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<Membersheep_logDTO> list(){
		ArrayList<Membersheep_logDTO> res = new ArrayList<Membersheep_logDTO>();
		
		sql = "select * from Membersheep_log";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Membersheep_log(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public void insert(Membersheep_logDTO dto){
		sql = 	"insert into Membersheep_log (" +
				"id, point, way, status, register_date) values ("+
				" ? ,  ?  , ? ,  	?,	 now())";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setInt(2, dto.getPoint());
			pstmt.setString(3, dto.getWay());
			pstmt.setString(4, dto.getStatus());
			
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
