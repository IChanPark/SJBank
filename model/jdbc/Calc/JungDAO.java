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
				System.out.println("도냐");
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

	public ArrayList<JungDTO> month(){
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
				//"GROUP BY CONCAT(YEAR(tl.register_date),'-',MONTH(tl.register_date)), CONCAT(ac.type,':',tl.feetype)"+
				"GROUP BY CONCAT(YEAR(tl.register_date),'-',MONTH(tl.register_date)), CONCAT(ac.type)"+
				"ORDER BY CONCAT(YEAR(tl.register_date),'-',MONTH(tl.register_date))";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
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
