package jdbc.Deposit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;


public class DepositDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private DepositDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final DepositDAO DAO = new DepositDAO();
    }
	
	public static DepositDAO getInstance() {
        return Holder.DAO;
    }

	private DepositDTO Deposit(ResultSet rs, DepositDTO dto) {
		try {
			if(rs.next()) {
				dto = new DepositDTO();
				dto.setAccount_number(rs.getString("account_number"));
				dto.setId(rs.getString("id"));
				dto.setPrduct(rs.getString("prduct"));
				dto.setPreferential(rs.getString("preferential"));
				dto.setInterest(rs.getFloat("interest"));
				
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Deposit(ResultSet rs, ArrayList<DepositDTO> res) {
		try {
			while (rs.next()) {
				DepositDTO dto = new DepositDTO();
				dto.setAccount_number(rs.getString("account_number"));
				dto.setId(rs.getString("id"));
				dto.setPrduct(rs.getString("prduct"));
				dto.setPreferential(rs.getString("preferential"));
				dto.setInterest(rs.getFloat("interest"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<DepositDTO> list(){
		ArrayList<DepositDTO> res = new ArrayList<DepositDTO>();
		
		sql = "select * from deposit";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Deposit(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public void insert(DepositDTO dto){
		sql = 	"insert into deposit (" +
				"account_number, id, prduct, preferential, interest) values ("+
				"	 	?  ,	  ?,	?,	       ?,  		 ? )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getAccount_number());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getPrduct());
			pstmt.setString(4, dto.getPreferential());
			pstmt.setFloat(4, dto.getInterest());
			
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
