package jdbc.Membersheep;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;


public class Membersheep_ratingDAO {
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;

	private Membersheep_ratingDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final Membersheep_ratingDAO DAO = new Membersheep_ratingDAO();
    }
	
	public static Membersheep_ratingDAO getInstance() {
        return Holder.DAO;
    }

	private Membersheep_ratingDTO Membersheep_rating(ResultSet rs, Membersheep_ratingDTO dto) {
		try {
			if(rs.next()) {
				dto = new Membersheep_ratingDTO();
				dto.setName(rs.getString("name"));
				dto.setCondition(rs.getInt("condition"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
						
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Membersheep_rating(ResultSet rs, ArrayList<Membersheep_ratingDTO> res) {
		try {
			while (rs.next()) {
				Membersheep_ratingDTO dto = new Membersheep_ratingDTO();
				dto = new Membersheep_ratingDTO();
				dto.setName(rs.getString("name"));
				dto.setCondition(rs.getInt("condition"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<Membersheep_ratingDTO> list(){
		ArrayList<Membersheep_ratingDTO> res = new ArrayList<Membersheep_ratingDTO>();
		
		sql = "select * from Membersheep_rating";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Membersheep_rating(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public void insert(Membersheep_ratingDTO dto){
		sql = 	"insert into Membersheep_rating (" +
				"name, condition, status, register_date) values ("+
				" ?  ,     ?    ,     ? ,  	 now())";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getCondition());
			pstmt.setString(3, dto.getStatus());
			pstmt.setString(4, dto.getRegister_dateStr());
			
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
