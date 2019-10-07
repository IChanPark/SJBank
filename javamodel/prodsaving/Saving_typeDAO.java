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
import jdbc.Saving.Saving_typeDTO;

public class Saving_typeDAO {
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String sql;
	
	
	private Saving_typeDAO() {
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
        public static final Saving_typeDAO DAO = new Saving_typeDAO();
    }
	
	public static Saving_typeDAO getInstance() {
        return Holder.DAO;
    }
	
	private void Deposits_type(ResultSet rs, ArrayList<Saving_typeDTO> res) {
		try {
			while (rs.next()) {
				Saving_typeDTO dto = new Saving_typeDTO();
				dto.setName(rs.getString("name"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<Saving_typeDTO> list(){
		ArrayList<Saving_typeDTO> res = new ArrayList<Saving_typeDTO>();
		
		sql = "select * from saving_type";
		
		try {
			
			rs = stmt.executeQuery(sql);
			
			Deposits_type(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	void close() {
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(stmt!=null) try {stmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
		sql = null;
	}
}
