package jdbc.Loan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class Loan_InfoDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;

	private Loan_InfoDAO() {
		ds = Data_Source.getInstance().getDs();
	}

	private static class Holder {
		public static final Loan_InfoDAO DAO = new Loan_InfoDAO();
	}

	public static Loan_InfoDAO getInstance() {
		return Holder.DAO;
	}

	private Loan_InfoDTO Loan_Info(ResultSet rs, Loan_InfoDTO dto) {
		try {
			if (rs.next()) {
				dto = new Loan_InfoDTO();

				dto.setProduct(rs.getString("product"));
				dto.setProduct_info(rs.getString("product_info"));
				dto.setMax_interest(rs.getFloat("max_interest"));
				dto.setMin_interest(rs.getFloat("min_interest"));
				dto.setMonth(rs.getInt("month"));
				dto.setType(rs.getString("type"));
				dto.setloanlimit(rs.getLong("loanlimit"));
				dto.setPreferential(rs.getString("preferential"));
				dto.setPrf_content(rs.getString("prf_content"));
				dto.setPrf_interest(rs.getString("prf_interest"));
				dto.setStatus(rs.getString("status"));

				dto.setRegister_date(rs.getDate("register_date"));
				dto.setEnd_date(rs.getDate("end_date"));
			}
		} catch (Exception e) {
		}
		return dto;
	}

	private void Loan_Info(ResultSet rs, ArrayList<Loan_InfoDTO> res) {
		try {
			while (rs.next()) {
				Loan_InfoDTO dto = new Loan_InfoDTO();
				dto = new Loan_InfoDTO();

				dto.setProduct(rs.getString("product"));
				dto.setProduct_info(rs.getString("product_info"));
				dto.setMax_interest(rs.getFloat("max_interest"));
				dto.setMin_interest(rs.getFloat("min_interest"));
				dto.setMonth(rs.getInt("month"));
				dto.setType(rs.getString("type"));
				dto.setloanlimit(rs.getLong("loanlimit"));
				dto.setPreferential(rs.getString("preferential"));
				dto.setPrf_content(rs.getString("prf_content"));
				dto.setPrf_interest(rs.getString("prf_interest"));
				dto.setStatus(rs.getString("status"));

				dto.setRegister_date(rs.getDate("register_date"));
				dto.setEnd_date(rs.getDate("end_date"));

				res.add(dto);
			}
		} catch (Exception e) {
		}
	}

	public ArrayList<Loan_InfoDTO> list() {
		ArrayList<Loan_InfoDTO> res = new ArrayList<Loan_InfoDTO>();

		sql = "select * from loan_info";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			Loan_Info(rs, res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return res;
	}

	public ArrayList<Loan_InfoDTO> selectProduct(String product) {
		ArrayList<Loan_InfoDTO> res = new ArrayList<Loan_InfoDTO>();

		sql = "select * from loan_info where product = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, product);

			rs = pstmt.executeQuery();

			Loan_Info(rs, res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return res;
	}

	public void insert(Loan_InfoDTO dto) {
		sql = "insert into loan_info ("
				+ "product ,product_info, min_interest , max_interest ,month , type , loanlimit , preferential,  prf_content , prf_interest , status, register_date, end_date) values ("
				+ "	?	,  ? ,     ?       ,  ?	       ,  ?   , ?    ,   ?    ,   ?	    ,       ?	         ,  	?	   ,   ?    ,	sysdate()  	   , null)";

		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

		
			  pstmt.setString(1, dto.getProduct());
			  pstmt.setString(2, dto.getProduct_info());
			  pstmt.setFloat(3,dto.getMin_interest()); 
			  pstmt.setFloat(4, dto.getMax_interest());
			  pstmt.setInt(5, dto.getMonth()); 
			  pstmt.setString(6, dto.getType());
			  pstmt.setLong(7, dto.getloanlimit()); 
			  pstmt.setString(8, dto.getPreferential());
			  pstmt.setString(9, dto.getPrf_content()); 
			  pstmt.setString(10,dto.getPrf_interest()); 
			  pstmt.setString(11, dto.getStatus());
			

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	void close() {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
			}
		sql = null;
	}
}
