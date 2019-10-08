package jdbc.Loan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class Loan_typeDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private Loan_typeDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final Loan_typeDAO DAO = new Loan_typeDAO();
    }
	
	public static Loan_typeDAO getInstance() {
        return Holder.DAO;
    }

	private Loan_typeDTO Fund(ResultSet rs, Loan_typeDTO dto) {
		try {
			if(rs.next()) {
				dto = new Loan_typeDTO();
				
				dto.setName(rs.getString("name"));
		
			
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Fund(ResultSet rs, ArrayList<Loan_typeDTO> res) {
		try {
			while (rs.next()) {
				Loan_typeDTO dto = new Loan_typeDTO();
				dto = new Loan_typeDTO();
				dto.setName(rs.getString("name"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<Loan_typeDTO> list(){
		ArrayList<Loan_typeDTO> res = new ArrayList<Loan_typeDTO>();
		
		sql = "select * from loan_type";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Fund(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	
	
	public void insert(Loan_typeDTO dto){
		sql = 	"insert into loan_type (" +
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
