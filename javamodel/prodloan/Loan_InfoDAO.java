package prodloan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;
import jdbc.Loan.Loan_InfoDTO;

public class Loan_InfoDAO {

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String sql;

	private Loan_InfoDAO() {
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
			rs = stmt.executeQuery(sql);

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

		sql = "select * from loan_info where product = '"+product+"'";
		System.out.println(sql);
		try {

			rs = stmt.executeQuery(sql);

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
				+ "product ,product_info, min_interest , max_interest ,month , type , loanlimit , preferential,  prf_content , prf_interest , status, register_date, modify_date ,end_date) "
				+ "values ('"+dto.getProduct()+"','"+ dto.getProduct_info()+
				"',"+dto.getMin_interest()+","+ dto.getMax_interest()+","+dto.getMonth() 
				+",'"+ dto.getType()+"',"+ dto.getloanlimit()+",'"+ dto.getPreferential()+"','"+
				dto.getPrf_content()+"','"+dto.getPrf_interest()+"','"+ dto.getStatus()+"', now , null,null)";                                     

		System.out.println(sql);
		try {
			stmt.executeUpdate(sql);
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
		if (stmt != null)
			try {
				stmt.close();
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
