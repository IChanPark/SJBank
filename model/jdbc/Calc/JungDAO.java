package jdbc.Calc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class JungDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;

	private JungDAO() {
		ds = Data_Source.getInstance().getDs();
	}

	private static class Holder {
		public static final JungDAO DAO = new JungDAO();
	}

	public static JungDAO getInstance() {
		return Holder.DAO;
	}

	private JungDTO Jung(ResultSet rs, JungDTO dto) {
		try {
			if(rs.next()) {
				dto = new JungDTO();
				dto.setDateStr(rs.getString("date"));
				dto.setCount(rs.getLong("count"));
				dto.setProduct(rs.getString("product"));
				dto.setType(rs.getString("type"));
				dto.setSum(rs.getLong("sum"));
			} 
		} catch (Exception e) {}
		return dto;
	}

	private void Jung(ResultSet rs, ArrayList<JungDTO> res) {
		try {
			while (rs.next()) {
				JungDTO dto = new JungDTO();
				dto.setDateStr(rs.getString("date"));
				dto.setCount(rs.getLong("count"));
				dto.setProduct(rs.getString("product"));
				dto.setType(rs.getString("type"));
				dto.setSum(rs.getLong("sum"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}

	public ArrayList<JungDTO> month(String start, String end){
		ArrayList<JungDTO> res = new ArrayList<JungDTO>();
		
		sql = 	"SELECT "+
				"CONCAT(YEAR(tl.register_date),'-',MONTH(tl.register_date)) AS date, "+
				"COUNT(tl.seq) AS count, "+
				"ac.type AS product, "+
				"tl.feetype AS type, "+
				"SUM(tl.fee) AS sum "+
				"FROM transfer_log tl "+
				"INNER JOIN ACCOUNT ac "+
				"ON tl.account_number = ac.account_number "+
				"WHERE DATE_FORMAT(tl.register_date, '%Y-%m-%d') >= STR_TO_DATE(?, '%Y-%m-01')"+
				"AND DATE_FORMAT(tl.register_date, '%Y-%m-%d') <= last_day(STR_TO_DATE(?,'%Y-%m'))"+
				"GROUP BY CONCAT(YEAR(tl.register_date),'-',MONTH(tl.register_date))"+
				//"GROUP BY CONCAT(YEAR(tl.register_date),'-',MONTH(tl.register_date))"+
				"ORDER BY CONCAT(YEAR(tl.register_date),'-',MONTH(tl.register_date))";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, start);
			pstmt.setString(2, end);
			
			rs = pstmt.executeQuery();

			Jung(rs, res);
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public ArrayList<JungDTO> year(String start, String end){
		ArrayList<JungDTO> res = new ArrayList<JungDTO>();

		sql = 	"SELECT "+
				"DATE_FORMAT(tl.register_date, '%Y') AS date, "+ 
				"COUNT(tl.seq) AS count, "+
				"ac.type AS product, "+
				"tl.feetype AS type, "+
				"SUM(tl.fee) AS sum " +
				"FROM transfer_log tl "+
				"INNER JOIN ACCOUNT ac " +
				"ON tl.account_number = ac.account_number "+ 
				"WHERE  YEAR(tl.register_date) >= YEAR(STR_TO_DATE(?, '%Y')) "+		
				"AND YEAR(tl.register_date) <= YEAR(STR_TO_DATE(?, '%Y')) "+
				"GROUP BY DATE_FORMAT(tl.register_date, '%Y') "+
				"ORDER BY DATE_FORMAT(tl.register_date, '%Y')";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, start);
			pstmt.setString(2, end);
			
			rs = pstmt.executeQuery();

			Jung(rs, res);
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public ArrayList<JungDTO> day(String start, String end){
		ArrayList<JungDTO> res = new ArrayList<JungDTO>();
		
		sql	=	"SELECT "+
				"DATE_FORMAT(tl.register_date, '%Y-%m-%d') AS date, "+ 
				"COUNT(tl.seq) AS count, " +
				"ac.type AS product, "+
				"tl.feetype AS type, "+
				"SUM(tl.fee) AS sum "+
				"FROM transfer_log tl "+
				"INNER JOIN ACCOUNT ac "+
				"ON tl.account_number = ac.account_number  "+
				"WHERE tl.register_date >= STR_TO_DATE(?, '%Y-%m-%d')"+
				"AND tl.register_date <= STR_TO_DATE(?,'%Y-%m-%d')"+
				"GROUP BY DATE_FORMAT(tl.register_date, '%Y-%m-%d')"+
				"ORDER BY DATE_FORMAT(tl.register_date, '%Y-%m-%d')";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, start);
			pstmt.setString(2, end);
			
			rs = pstmt.executeQuery();

			Jung(rs, res);
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