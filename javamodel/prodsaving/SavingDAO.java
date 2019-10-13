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
import jdbc.Fund.FundDTO;
import jdbc.Saving.SavingDTO;
import server.DBAccess_IP;


public class SavingDAO {
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String sql;
	
	public SavingDAO() {
		try {
			String url ="jdbc:mariadb://"+DBAccess_IP.getInstance().getIP()+":3306/bank";
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
				dto.setProduct(rs.getString("product"));
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
				dto.setProduct(rs.getString("product"));
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
	
	public ArrayList<SavingDTO> uplist(){
		ArrayList<SavingDTO> res = new ArrayList<SavingDTO>();
		
		sql = "select s.account_number, s.id, s.product, s.interest, "+ 
				"f.type,  a.status "+
				"FROM saving s "+
				"INNER JOIN account a ON s.account_number = a.account_number "+
				"where a.status ='활성' "+ 
				"and s.account_number not in "+
				"(select account_number from saving_log where "+
				"date_format(now(), '%Y-%m') = date_format(register_date, '%Y-%m') and "+
				"status = '성공') ";
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
				"('"+dto.getAccount_number()+"','"+dto.getId()+"','"+dto.getProduct()+"','"+
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
