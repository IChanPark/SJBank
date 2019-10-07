package prodsaving;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;
import jdbc.Saving.SavingDTO;


public class SavingDAO {
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String sql;
	
	private SavingDAO() {
		try {
			String url ="jdbc:mariadb://192.168.1.14:3306/bank";
			String id = "bank";
			String pw = "1234";

			Class.forName("org.mariadb.jdbc.Driver");

			con = DriverManager.getConnection(url,id,pw);
			stmt = con.createStatement();
		} catch (Exception e) {
			// TODO: handle exception
		}
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
				dto.setPrduct(rs.getString("prduct"));
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
				dto.setPrduct(rs.getString("prduct"));
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
			
			rs = stmt.executeQuery(sql);
			
			Saving(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public void insert(SavingDTO dto){
		sql = 	"insert into saving " +
				"(account_number, id, prduct, preferential, interest , type, end_date) "
				+ "values "+
				"('"+dto.getAccount_number()+"','"+dto.getId()+"','"+dto.getPrduct()+"','"+
				 dto.getPreferential()+"',"+dto.getInterest()+",'"+dto.getType()+"','"+dto.getEnd_dateStr()+"')";
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	void close() {
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(stmt!=null) try {stmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
		sql = null;
	}
}
