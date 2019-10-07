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
import jdbc.Loan.LoanDTO;

public class LoanDAO {

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String sql;

	private LoanDAO() {
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
		public static final LoanDAO DAO = new LoanDAO();
	}

	public static LoanDAO getInstance() {
		return Holder.DAO;
	}

	private LoanDTO Loan(ResultSet rs, LoanDTO dto) {
		try {
			if(rs.next()) {
				dto = new LoanDTO();

				dto.setAccount_number(rs.getString("account_number"));
				dto.setId(rs.getString("id"));
				dto.setProduct(rs.getString("product"));
				dto.setPreferential(rs.getString("preferential"));
				dto.setInterest(rs.getFloat("interest"));
				dto.setType(rs.getString("type"));
				dto.setSum(rs.getInt("sum"));
				dto.setPeriod(rs.getString("period"));
				dto.setEnd_date(rs.getDate("end_date"));

			} 
		} catch (Exception e) {}
		return dto;
	}

	private void Loan(ResultSet rs, ArrayList<LoanDTO> res) {
		try {
			while (rs.next()) {
				LoanDTO dto = new LoanDTO();
				dto = new LoanDTO();

				dto.setAccount_number(rs.getString("account_number"));
				dto.setId(rs.getString("id"));
				dto.setProduct(rs.getString("product"));
				dto.setPreferential(rs.getString("preferential"));
				dto.setInterest(rs.getFloat("interest"));
				dto.setType(rs.getString("type"));
				dto.setSum(rs.getInt("sum"));
				dto.setPeriod(rs.getString("period"));
				dto.setEnd_date(rs.getDate("end_date"));

				res.add(dto);
			} 
		} catch (Exception e) {}
	}

	public ArrayList<LoanDTO> list(){
		ArrayList<LoanDTO> res = new ArrayList<LoanDTO>();

		sql = "select * from Loan";
		try {

			rs = stmt.executeQuery(sql);

			Loan(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}

	public ArrayList<LoanDTO> selectID(String id){
		ArrayList<LoanDTO> res = new ArrayList<LoanDTO>();

		sql = "select * from Loan where id = '"+id+"'";
		System.out.println(sql);
		try {

			rs = stmt.executeQuery(sql);

			Loan(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}

	public void insert(LoanDTO dto){
		sql = 	"insert into Loan (" +
				"account_number,id,product,preferential,interest,type,sum,period,end_date"+ 
				"values "
				+"('"+dto.getAccount_number()+"','"+dto.getId()+"','"+ dto.getProduct()+
				"','"+dto.getPreferential()+"',"+dto.getInterest()+",'"+dto.getType()+"',"+
				dto.getSum()+",'"+ dto.getPeriod()+"','"+ dto.getEnd_dateStr()+"')";

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
