package jdbc.Deposit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;


public class DepositsDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private DepositsDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final DepositsDAO DAO = new DepositsDAO();
    }
	
	public static DepositsDAO getInstance() {
        return Holder.DAO;
    }

	private DepositsDTO Deposit(ResultSet rs, DepositsDTO dto) {
		try {
			if(rs.next()) {
				dto = new DepositsDTO();
				dto.setAccount_number(rs.getString("account_number"));
				dto.setId(rs.getString("id"));
				dto.setPrduct(rs.getString("product"));
				dto.setPreferential(rs.getString("preferential"));
				dto.setInterest(rs.getFloat("interest"));
				dto.setType(rs.getString("type"));
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Deposit(ResultSet rs, ArrayList<DepositsDTO> res) {
		try {
			System.out.println("여기옴????");
			while (rs.next()) {
				System.out.println("여기안옴??");
				DepositsDTO dto = new DepositsDTO();
				dto.setAccount_number(rs.getString("account_number"));
				dto.setId(rs.getString("id"));
				dto.setPrduct(rs.getString("product"));
				dto.setPreferential(rs.getString("preferential"));
				dto.setInterest(rs.getFloat("interest"));
				dto.setType(rs.getString("type"));
				res.add(dto);
				System.out.println("여기가 어레이를 만듭니다" +dto);
			} 
			System.out.println("흠흠");
		} catch (Exception e) {}
	}
	
	public ArrayList<DepositsDTO> list(){
		ArrayList<DepositsDTO> res = new ArrayList<DepositsDTO>();
		
		sql = "select * from deposits";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Deposit(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public void insert(DepositsDTO dto){
		sql = 	"insert into deposits (" +
				"account_number, id, product, preferential, interest, type) values ("+
				"	 	?  ,	  ?,	?,	       ?,  		 ? 	   ,   ? )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getAccount_number());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getPrduct());
			pstmt.setString(4, dto.getPreferential());
			pstmt.setFloat(5, dto.getInterest());
			pstmt.setString(6, dto.getType());

			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	
	///////////////////////////////////////////////
	public ArrayList<DepositsDTO> UnTrsAccount(String id){
		ArrayList<DepositsDTO> res = new ArrayList<DepositsDTO>();
		
		sql = "select * from deposits where id = '"+id+"' and type= '정기'";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println(id + " 아이디에요");
		
			Deposit(rs, res);	
			System.out.println(res+"  dao 안이에요");
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	
	
	
	///////////////////////////////////////////
	void close() {
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(pstmt!=null) try {pstmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
		sql = null;
	}
}
