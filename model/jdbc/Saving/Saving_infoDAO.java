package jdbc.Saving;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class Saving_infoDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private Saving_infoDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final Saving_infoDAO DAO = new Saving_infoDAO();
    }
	
	public static Saving_infoDAO getInstance() {
        return Holder.DAO;
    }

	private Saving_infoDTO Saving_info(ResultSet rs, Saving_infoDTO dto) {
		try {
			if(rs.next()) {
				dto = new Saving_infoDTO();
				
				dto.setProduct(rs.getString("product"));
				dto.setMin_interest(rs.getFloat("min_interest"));
				dto.setMax_interest(rs.getFloat("max_interest"));
				dto.setMonth(rs.getInt("month"));
				dto.setType(rs.getString("type"));
				dto.setRegular(rs.getString("regular"));
				dto.setJnterest_type(rs.getString("jnterest_type"));
				dto.setTax(rs.getString("tax"));
				dto.setPreferential(rs.getString("preferential"));
				dto.setPrf_content(rs.getString("prf_content"));
				dto.setPrf_interest(rs.getString("prf_interest"));
				dto.setPartialization(rs.getString("partialization"));
				dto.setRetention(rs.getString("retention"));
				dto.setStatus(rs.getString("status"));
				dto.setMin_sum(rs.getInt("min_sum"));
				dto.setMax_sum(rs.getInt("max_sum"));
				dto.setRegister_date(rs.getDate("register_date"));
				dto.setEnd_date(rs.getDate("end_date"));
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Saving_info(ResultSet rs, ArrayList<Saving_infoDTO> res) {
		try {
			while (rs.next()) {
				Saving_infoDTO dto = new Saving_infoDTO();
				
				dto.setProduct(rs.getString("product"));
				dto.setMin_interest(rs.getFloat("min_interest"));
				dto.setMax_interest(rs.getFloat("max_interest"));
				dto.setMonth(rs.getInt("month"));
				dto.setType(rs.getString("type"));
				dto.setRegular(rs.getString("regular"));
				dto.setJnterest_type(rs.getString("jnterest_type"));
				dto.setTax(rs.getString("tax"));
				dto.setPreferential(rs.getString("preferential"));
				dto.setPrf_content(rs.getString("prf_content"));
				dto.setPrf_interest(rs.getString("prf_interest"));
				dto.setPartialization(rs.getString("partialization"));
				dto.setRetention(rs.getString("retention"));
				dto.setStatus(rs.getString("status"));
				dto.setMin_sum(rs.getInt("min_sum"));
				dto.setMax_sum(rs.getInt("max_sum"));
				dto.setRegister_date(rs.getDate("register_date"));
				dto.setEnd_date(rs.getDate("end_date"));
				
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<Saving_infoDTO> list(){
		ArrayList<Saving_infoDTO> res = new ArrayList<Saving_infoDTO>();
		
		sql = "select * from saving_info";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Saving_info(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public void insert(Saving_infoDTO dto){
		sql = 	"insert into saving_info (" +
				"product, min_interest, max_interest, month, type, regular , jnterest_type, tax, preferential,"+ 
				"prf_content, prf_Interest, min_sum, max_sum, partialization, retention, status, register_date, end_date) values ("+
				"	 ?	,		?     ,		?		,	?  ,	?,	  ?  	,     ?	    ,	? ,			?	,"+
				"		?	,		?	  ,		?  ,	?	,		?		,	 ?	   ,	'활성'  ,	now() ,	null)";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getProduct());
			pstmt.setFloat(2, dto.getMin_interest());
			pstmt.setFloat(3, dto.getMax_interest());
			pstmt.setInt(4, dto.getMonth());
			pstmt.setString(5, dto.getType());
			pstmt.setString(6, dto.getRegular());
			pstmt.setString(7, dto.getJnterest_type());
			pstmt.setString(8, dto.getTax());
			pstmt.setString(9, dto.getPreferential());
			pstmt.setString(10, dto.getPrf_content());
			pstmt.setString(11, dto.getPrf_interest());
			pstmt.setInt(12, dto.getMin_sum());
			pstmt.setInt(13, dto.getMax_sum());
			pstmt.setString(14, dto.getPartialization());
			pstmt.setString(15, dto.getRetention());
			
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
