package jdbc.Deposit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class Deposits_typeDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private Deposits_typeDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final Deposits_typeDAO DAO = new Deposits_typeDAO();
    }
	
	public static Deposits_typeDAO getInstance() {
        return Holder.DAO;
    }
	
	private void Deposits_type(ResultSet rs, ArrayList<Deposits_typeDTO> res) {
		try {
			while (rs.next()) {
				Deposits_typeDTO dto = new Deposits_typeDTO();
				dto.setName(rs.getString("name"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<Deposits_typeDTO> list(){
		ArrayList<Deposits_typeDTO> res = new ArrayList<Deposits_typeDTO>();
		
		sql = "select * from deposits_type";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Deposits_type(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	void close() {
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(pstmt!=null) try {pstmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
		sql = null;
	}
}
