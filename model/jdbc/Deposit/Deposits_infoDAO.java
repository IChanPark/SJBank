package jdbc.Deposit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class Deposits_infoDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private Deposits_infoDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final Deposits_infoDAO DAO = new Deposits_infoDAO();
    }
	
	public static Deposits_infoDAO getInstance() {
        return Holder.DAO;
    }

	private Deposits_infoDTO Deposits_info(ResultSet rs, Deposits_infoDTO dto) {
		try {
			if(rs.next()) {
				dto = new Deposits_infoDTO();
				dto.setProduct(rs.getString("product"));
				dto.setDeposits_info(rs.getString("deposits_info"));
				dto.setMin_interest(rs.getFloat("min_interest"));
				dto.setMax_interest(rs.getFloat("max_interest"));
				dto.setMonth(rs.getString("month"));
				dto.setType(rs.getString("type"));
				dto.setInterest_type(rs.getString("interest_type"));
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
	
	private void Deposits_info(ResultSet rs, ArrayList<Deposits_infoDTO> res) {
		try {
			while (rs.next()) {
				Deposits_infoDTO dto = new Deposits_infoDTO();
				dto.setProduct(rs.getString("product"));
				dto.setDeposits_info(rs.getString("deposits_info"));
				dto.setMin_interest(rs.getFloat("min_Interest"));
				dto.setMax_interest(rs.getFloat("max_Interest"));
				dto.setMonth(rs.getString("month"));
				dto.setType(rs.getString("type"));
				dto.setInterest_type(rs.getString("interest_type"));
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
	
	public Deposits_infoDTO selectProUse(Deposits_infoDTO dto){
		Deposits_infoDTO SetDTO = null;
		
		sql =	"select * from deposits_info where product = ? "+
				"and status = ?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getProduct());
			pstmt.setString(2, "활성");
			rs = pstmt.executeQuery();
			
			SetDTO = Deposits_info(rs, dto);			
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return SetDTO;
	}
	
	public ArrayList<Deposits_infoDTO> list(){
		ArrayList<Deposits_infoDTO> res = new ArrayList<Deposits_infoDTO>();
		
		sql = "select * from deposits_info";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Deposits_info(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public ArrayList<Deposits_infoDTO> selectType(Deposits_infoDTO dto){
		ArrayList<Deposits_infoDTO> res = new ArrayList<Deposits_infoDTO>();
		
		sql = "select * from deposits_info where type = ?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getType());
			
			rs = pstmt.executeQuery();
			
			Deposits_info(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public ArrayList<Deposits_infoDTO> selectLikePro(Deposits_infoDTO dto){
		ArrayList<Deposits_infoDTO> res = new ArrayList<Deposits_infoDTO>();
		
		sql = "select * from deposits_info where product like ?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, "%"+dto.getProduct()+"%");
			
			rs = pstmt.executeQuery();
			
			Deposits_info(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public ArrayList<Deposits_infoDTO> selectLikeAnd(Deposits_infoDTO dto){
		ArrayList<Deposits_infoDTO> res = new ArrayList<Deposits_infoDTO>();
		
		sql = 	"select * from deposits_info where product like ?"+
				"AND type = ?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, "%"+dto.getProduct()+"%");
			pstmt.setString(2, dto.getType());
			
			rs = pstmt.executeQuery();
			
			Deposits_info(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public void insert(Deposits_infoDTO dto){
		sql = 	"insert into deposits_info (" +
				"product, deposits_info, min_interest, max_interest, month, type, interest_type, tax, preferential,"+ 
				"prf_content, prf_Interest, min_sum, max_sum, partialization, retention, status, register_date, end_date) values ("+
				"	 ?	,		?	   ,	  ?      ,		?		,	?  ,	?,	   	?	    ,	? ,			?	,"+
				"		?	,		?	  ,		?  ,	?	,		?		,	 ?	   ,	'활성'  ,	now() ,	null)";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getProduct());
			pstmt.setString(2,  dto.getDeposits_info());
			pstmt.setFloat(3, dto.getMin_interest());
			pstmt.setFloat(4, dto.getMax_interest());
			pstmt.setString(5, dto.getMonth());
			pstmt.setString(6, dto.getType());
			pstmt.setString(7, dto.getInterest_type());
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
	
	private void close() {
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(pstmt!=null) try {pstmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
		sql = null;
	}

}