package jdbc.etc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;


public class AnalysisDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private AnalysisDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final AnalysisDAO DAO = new AnalysisDAO();
    }
	
	public static AnalysisDAO getInstance() {
        return Holder.DAO;
    }

	private AnalysisDTO Analysis(ResultSet rs, AnalysisDTO dto) {
		try {
			if(rs.next()) {
				dto = new AnalysisDTO();
				dto.setPropensity(rs.getString("propensity"));
				dto.setName(rs.getString("name"));
				dto.setScope(rs.getString("scope"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_dateStr(rs.getString("register_date"));
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Analysis(ResultSet rs, ArrayList<AnalysisDTO> res) {
		try {
			while (rs.next()) {
				AnalysisDTO dto = new AnalysisDTO();
				dto.setPropensity(rs.getString("propensity"));
				dto.setName(rs.getString("name"));
				dto.setScope(rs.getString("scope"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_dateStr(rs.getString("register_date"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<AnalysisDTO> list(){
		ArrayList<AnalysisDTO> res = new ArrayList<AnalysisDTO>();
		
		sql = "select * from analysis";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Analysis(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public void insert(AnalysisDTO dto){
		sql = 	"insert into analysis (" +
				"propensity, name, scope, status, register_date) values ("+
				"	 	?  ,	?,  ?   ,	   ?,  		 ? )";
		
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getPropensity());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getScope());
			pstmt.setString(4, dto.getStatus());
			pstmt.setString(5, dto.getRegister_dateStr());
			
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
