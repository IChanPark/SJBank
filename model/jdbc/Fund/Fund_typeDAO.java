package jdbc.Fund;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class Fund_typeDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private Fund_typeDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final Fund_typeDAO DAO = new Fund_typeDAO();
    }
	
	public static Fund_typeDAO getInstance() {
        return Holder.DAO;
    }

	private Fund_typeDTO Fund(ResultSet rs, Fund_typeDTO dto) {
		try {
			if(rs.next()) {
				dto = new Fund_typeDTO();
				
				dto.setName(rs.getString("name"));
		
			
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Fund(ResultSet rs, ArrayList<Fund_typeDTO> res) {
		try {
			while (rs.next()) {
				Fund_typeDTO dto = new Fund_typeDTO();
				dto = new Fund_typeDTO();
				dto.setName(rs.getString("name"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<Fund_typeDTO> list(){
		ArrayList<Fund_typeDTO> res = new ArrayList<Fund_typeDTO>();
		
		sql = "select * from Fund_type";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Fund(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	
	
	public void insert(Fund_typeDTO dto){
		sql = 	"insert into Fund_type (" +
				"name) values ("+
				" ?	)";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			
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
