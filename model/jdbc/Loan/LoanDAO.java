package jdbc.Loan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class LoanDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private LoanDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final LoanDAO DAO = new LoanDAO();
    }
	
	public static LoanDAO getInstance() {
        return Holder.DAO;
    }

	private LoanDTO Loan(ResultSet rs, LoanDTO dto) {
		try {
			if(rs.next()) {
				dto = new LoanDTO();
				
				dto.setAccount_number(rs.getString("account_number"));
				dto.setId(rs.getString("id"));
				dto.setProduct(rs.getString("product"));
				dto.setPreferential(rs.getString("preferential"));
				dto.setInterest(rs.getFloat("interest"));
				dto.setType(rs.getString("type"));
				dto.setSum(rs.getInt("sum"));
				dto.setPeriod(rs.getString("period"));
				dto.setEnd_date(rs.getDate("end_date"));
				
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Loan(ResultSet rs, ArrayList<LoanDTO> res) {
		try {
			while (rs.next()) {
				LoanDTO dto = new LoanDTO();
				dto = new LoanDTO();
				
				dto.setAccount_number(rs.getString("account_number"));
				dto.setId(rs.getString("id"));
				dto.setProduct(rs.getString("product"));
				dto.setPreferential(rs.getString("preferential"));
				dto.setInterest(rs.getFloat("interest"));
				dto.setType(rs.getString("type"));
				dto.setSum(rs.getInt("sum"));
				dto.setPeriod(rs.getString("period"));
				dto.setEnd_date(rs.getDate("end_date"));
				
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<LoanDTO> list(){
		ArrayList<LoanDTO> res = new ArrayList<LoanDTO>();
		
		sql = "select * from Loan";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Loan(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public ArrayList<LoanDTO> selectID(String id){
		ArrayList<LoanDTO> res = new ArrayList<LoanDTO>();
		
		sql = "select * from Loan where id = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			Loan(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	
	public void insert(LoanDTO dto){
		sql = 	"insert into Loan (" +
				"account_number,id,product,preferential,interest,type,sum,period,end_date"+ 
				"values "
				+ "(?	       ,? ,  ?	  ,       ?    ,  ?     , ?  , ? , ?	, 	?	   )";
		
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
			pstmt.setInt(7, dto.getSum());
			pstmt.setString(8, dto.getPeriod());
			pstmt.setString(9, dto.getEnd_dateStr());
			
			
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
