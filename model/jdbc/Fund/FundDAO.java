package jdbc.Fund;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class FundDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private FundDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final FundDAO DAO = new FundDAO();
    }
	
	public static FundDAO getInstance() {
        return Holder.DAO;
    }

	private FundDTO Fund(ResultSet rs, FundDTO dto) {
		try {
			if(rs.next()) {
				dto = new FundDTO();
				dto.setAccount_number(rs.getString("account_number"));
				dto.setId(rs.getString("id"));
				dto.setProduct(rs.getString("product"));
				dto.setFluctuation(rs.getFloat("fluctuation"));
			
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Fund(ResultSet rs, ArrayList<FundDTO> res) {
		try {
			while (rs.next()) {
				FundDTO dto = new FundDTO();
				dto = new FundDTO();
				dto.setAccount_number(rs.getString("account_number"));
				dto.setId(rs.getString("id"));
				dto.setProduct(rs.getString("product"));
				dto.setFluctuation(rs.getFloat("fluctuation"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<FundDTO> list(){
		ArrayList<FundDTO> res = new ArrayList<FundDTO>();
		
		sql = "select * from fund";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Fund(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public ArrayList<FundDTO> selectID(String id){
		ArrayList<FundDTO> res = new ArrayList<FundDTO>();
		
		sql = "select * from fund where id = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			Fund(rs, res);	
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public FundDTO selectAccount(String acc){
		FundDTO dto = null;
		
		sql = "select * from fund where account_number = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, acc);
			
			rs = pstmt.executeQuery();
			
			dto = Fund(rs, dto);
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return dto;
	}
	
	public void insert(FundDTO dto){
		sql = 	"insert into fund (" +
				"account_number, id, product,fluctuation) values ("+
				"			?  , ?,	  ?,	  ?	)";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getAccount_number());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getProduct());
			pstmt.setFloat(4, dto.getFluctuation());
			
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	public void updateFluctuation(FundDTO dto){
		sql = 	"update fund set " +
				"fluctuation = ? " +
				"where account_number = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setFloat(1, dto.getFluctuation());
			pstmt.setString(2, dto.getAccount_number());
			
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
