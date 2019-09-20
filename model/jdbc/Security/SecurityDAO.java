package jdbc.Security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class SecurityDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private SecurityDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final SecurityDAO DAO = new SecurityDAO();
    }
	
	public static SecurityDAO getInstance() {
        return Holder.DAO;
    }

	private SecurityDTO Security(ResultSet rs, SecurityDTO dto) {
		try {
			if(rs.next()) {
				dto = new SecurityDTO();
				dto.setId(rs.getString("id"));
				dto.setSerial(rs.getString("serial"));
				dto.setType(rs.getString("Type"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
				dto.setEnd_date(rs.getDate("end_date"));
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Security(ResultSet rs, ArrayList<SecurityDTO> res) {
		try {
			while (rs.next()) {
				SecurityDTO dto = new SecurityDTO();
				dto.setId(rs.getString("id"));
				dto.setSerial(rs.getString("serial"));
				dto.setType(rs.getString("Type"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
				dto.setEnd_date(rs.getDate("end_date"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<SecurityDTO> list(){
		ArrayList<SecurityDTO> res = new ArrayList<SecurityDTO>();
		
		sql = "select * from security";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Security(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public void insert(SecurityDTO dto){
		sql = 	"insert into security (" +
				"id, serial, type, status, register_date, end_date) values ("+
				" ?,   ?   ,	?,  '활성',	 now()		,	null  )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getSerial());
			pstmt.setString(3, dto.getType());
			
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
