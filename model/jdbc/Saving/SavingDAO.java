package jdbc.Saving;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;


public class SavingDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private SavingDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final SavingDAO DAO = new SavingDAO();
    }
	
	public static SavingDAO getInstance() {
        return Holder.DAO;
    }

	private SavingDTO Saving(ResultSet rs, SavingDTO dto) {
		try {
			if(rs.next()) {
				dto = new SavingDTO();
				
				dto.setAccount_number(rs.getString("account_number"));
				dto.setId(rs.getString("id"));
				dto.setProduct(rs.getString("Product"));
				dto.setPreferential(rs.getString("preferential"));
				dto.setInterest(rs.getFloat("interest"));
				dto.setType(rs.getString("type"));
				dto.setEnd_date(rs.getDate("end_date"));
				
				
				
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Saving(ResultSet rs, ArrayList<SavingDTO> res) {
		try {
			while (rs.next()) {
				SavingDTO dto = new SavingDTO();
				
				dto.setAccount_number(rs.getString("account_number"));
				dto.setId(rs.getString("id"));
				dto.setProduct(rs.getString("Product"));
				dto.setPreferential(rs.getString("preferential"));
				dto.setInterest(rs.getFloat("interest"));
				dto.setType(rs.getString("type"));
				dto.setEnd_date(rs.getDate("end_date"));
				
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<SavingDTO> list(){
		ArrayList<SavingDTO> res = new ArrayList<SavingDTO>();
		
		sql = "select * from saving";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Saving(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public void insert(SavingDTO dto){
		sql = 	"insert into saving " +
				"(account_number, id, Product, preferential, interest , type) "
				+ "values "+
				"(	 	?       ,  ?,	?   ,      ?      ,		 ?   ,   ?   )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getAccount_number());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getProduct());
			pstmt.setString(4, dto.getPreferential());
			pstmt.setFloat(5, dto.getInterest());
			pstmt.setString(6, dto.getType());
			//pstmt.setString(7, dto.getEnd_dateStr());
			
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
