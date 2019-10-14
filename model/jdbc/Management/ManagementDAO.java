package jdbc.Management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class ManagementDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private ManagementDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final ManagementDAO DAO = new ManagementDAO();
    }
	
	public static ManagementDAO getInstance() {
        return Holder.DAO;
    }

	private ManagementDTO Management(ResultSet rs, ManagementDTO dto) {
		try {
			if(rs.next()) {
				dto = new ManagementDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));			
				dto.setName(rs.getString("name"));
				dto.setDepartment(rs.getString("department"));
				dto.setTel(rs.getString("tel"));
				dto.setAccess_level(rs.getInt("access_level"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Management(ResultSet rs, ArrayList<ManagementDTO> res) {
		try {
			while (rs.next()) {
				ManagementDTO dto = new ManagementDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));			
				dto.setName(rs.getString("name"));
				dto.setDepartment(rs.getString("department"));
				dto.setTel(rs.getString("tel"));
				dto.setAccess_level(rs.getInt("access_level"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<ManagementDTO> list(){
		ArrayList<ManagementDTO> res = new ArrayList<ManagementDTO>();
		
		sql = "select * from management";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Management(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public ManagementDTO selectId(String id){
		ManagementDTO dto = null;
		
		sql = 	"select * from management where id = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			dto = Management(rs, dto);
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return dto;
	}
	
	void close() {
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(pstmt!=null) try {pstmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
		sql = null;
	}
}
