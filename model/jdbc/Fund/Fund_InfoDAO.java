package jdbc.Fund;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class Fund_InfoDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private Fund_InfoDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final Fund_InfoDAO DAO = new Fund_InfoDAO();
    }
	
	public static Fund_InfoDAO getInstance() {
        return Holder.DAO;
    }

	private Fund_InfoDTO Fund_Info(ResultSet rs, Fund_InfoDTO dto) {
		try {
			if(rs.next()) {
				dto = new Fund_InfoDTO();
				dto.setProduct(rs.getString("product"));
				dto.setType(rs.getString("type"));
				dto.setArea(rs.getString("area"));
				dto.setProperty(rs.getString("property"));
				dto.setManagement(rs.getString("management"));
				dto.setSector(rs.getString("sector"));
				dto.setStatus(rs.getString("status"));
				
				dto.setPrice(rs.getFloat("price"));
				dto.setPrice_modify(rs.getFloat("price_modify"));
				dto.setFirst_fee(rs.getFloat("first_fee"));
				dto.setFee(rs.getFloat("fee"));
			
				dto.setRegister_date(rs.getDate("register_date"));
				dto.setEnd_date(rs.getDate("end_date"));
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Fund_Info(ResultSet rs, ArrayList<Fund_InfoDTO> res) {
		try {
			while (rs.next()) {
				Fund_InfoDTO dto = new Fund_InfoDTO();
				dto = new Fund_InfoDTO();
				dto.setProduct(rs.getString("product"));
				dto.setType(rs.getString("type"));
				dto.setArea(rs.getString("area"));
				dto.setProperty(rs.getString("property"));
				dto.setManagement(rs.getString("management"));
				dto.setSector(rs.getString("sector"));
				dto.setStatus(rs.getString("status"));
				
				dto.setPrice(rs.getFloat("price"));
				dto.setPrice_modify(rs.getFloat("price_modify"));
				dto.setFirst_fee(rs.getFloat("first_fee"));
				dto.setFee(rs.getFloat("fee"));

				dto.setRegister_date(rs.getDate("register_date"));
				dto.setEnd_date(rs.getDate("end_date"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<Fund_InfoDTO> list(){
		ArrayList<Fund_InfoDTO> res = new ArrayList<Fund_InfoDTO>();
		
		sql = "select * from fund_info";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Fund_Info(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public ArrayList<Fund_InfoDTO> selectID(String product){
		ArrayList<Fund_InfoDTO> res = new ArrayList<Fund_InfoDTO>();
		
		sql = "select * from fund_info where product = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, product);
			
			rs = pstmt.executeQuery();
			
			Fund_Info(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	
	public void insert(Fund_InfoDTO dto){
		sql = 	"insert into fund_info (" +
				"product,price,price_modify,type,area,property,first_fee,"+ 
				"fee,management,sector,status,register_date, end_date) "+
				"values ("+
				"	?	,  ?  ,  ?	       ,  ? , ?  ,   ?    ,   ?	    ,"+
				" ?	, 	?	   ,   ?  ,	  ?	 ,	now()  	   , null)";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getProduct());
			pstmt.setFloat(2, dto.getPrice());
			pstmt.setFloat(3, dto.getPrice_modify());
			pstmt.setString(4, dto.getType());
			pstmt.setString(5, dto.getArea());
			pstmt.setString(6, dto.getProperty());
			pstmt.setFloat(7, dto.getFirst_fee());
			pstmt.setFloat(8, dto.getFee());
			pstmt.setString(9, dto.getManagement());
			pstmt.setString(10, dto.getSector());
			pstmt.setString(11, dto.getStatus());
			
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
