package jdbc.Saving;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class Saving_typeDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private Saving_typeDAO() {
		ds = Data_Source.getInstance().getDs();
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
