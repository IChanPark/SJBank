package proddepo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;
import jdbc.Deposit.DepositsDTO;


public class DepositsDAO {
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String sql;
	
	private DepositsDAO() {
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
				dto.setPrduct(rs.getString("prduct"));
				dto.setPreferential(rs.getString("preferential"));
				dto.setInterest(rs.getFloat("interest"));
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Deposit(ResultSet rs, ArrayList<DepositsDTO> res) {
		try {
			while (rs.next()) {
				DepositsDTO dto = new DepositsDTO();
				dto.setAccount_number(rs.getString("account_number"));
				dto.setId(rs.getString("id"));
				dto.setPrduct(rs.getString("prduct"));
				dto.setPreferential(rs.getString("preferential"));
				dto.setInterest(rs.getFloat("interest"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<DepositsDTO> list(){
		ArrayList<DepositsDTO> res = new ArrayList<DepositsDTO>();
		
		sql = "select * from deposits";
		try {
			
			rs = stmt.executeQuery(sql);
			
			Deposit(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public void insert(DepositsDTO dto){
		sql = 	"insert into deposits (" +
				"account_number, id, prduct, preferential, interest) "
				+ "values "
				+ "('"+dto.getAccount_number()+"','"+dto.getId()+"','"+dto.getPrduct()
				+"','"+dto.getPreferential()+"',"+dto.getInterest() +")";
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
