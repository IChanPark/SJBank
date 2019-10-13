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
import jdbc.Deposit.Deposits_infoDTO;
import jdbc.Saving.Saving_infoDTO;

public class Saving_infoDAO {

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String sql;

	private Saving_infoDAO() {
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
		public static final Saving_infoDAO DAO = new Saving_infoDAO();
	}

	public static Saving_infoDAO getInstance() {
		return Holder.DAO;
	}

	private Saving_infoDTO Saving_info(ResultSet rs, Saving_infoDTO dto) {
		try {
			if(rs.next()) {
				dto = new Saving_infoDTO();

				dto.setProduct(rs.getString("product"));
				dto.setProduct_info(rs.getString("product_info"));
				dto.setMin_interest(rs.getFloat("min_interest"));
				dto.setMax_interest(rs.getFloat("max_interest"));
				//dto.setMonth(rs.getInt("month"));
				dto.setType(rs.getString("type"));
				dto.setInterest_type(rs.getString("interest_type"));
				dto.setTax(rs.getString("tax"));
				dto.setPreferential(rs.getString("preferential"));
				dto.setPrf_content(rs.getString("prf_content"));
				dto.setPrf_interest(rs.getString("prf_interest"));
				dto.setPartialization(rs.getString("partialization"));
				dto.setRetention(rs.getString("retention"));
				dto.setStatus(rs.getString("status"));
				dto.setMin_sum(rs.getInt("min_sum"));
				dto.setMax_sum(rs.getInt("max_sum"));
				dto.setRegister_date(rs.getDate("register_date"));
				dto.setEnd_date(rs.getDate("end_date"));
			} 
		} catch (Exception e) {}
		return dto;
	}

	private void Saving_info(ResultSet rs, ArrayList<Saving_infoDTO> res) {
		try {
			while (rs.next()) {
				Saving_infoDTO dto = new Saving_infoDTO();

				dto.setProduct(rs.getString("product"));
				dto.setProduct_info(rs.getString("product_info"));
				dto.setMin_interest(rs.getFloat("min_interest"));
				dto.setMax_interest(rs.getFloat("max_interest"));
				//dto.setMonth(rs.getInt("month"));
				dto.setType(rs.getString("type"));
				dto.setInterest_type(rs.getString("interest_type"));
				dto.setTax(rs.getString("tax"));
				dto.setPreferential(rs.getString("preferential"));
				dto.setPrf_content(rs.getString("prf_content"));
				dto.setPrf_interest(rs.getString("prf_interest"));
				dto.setPartialization(rs.getString("partialization"));
				dto.setRetention(rs.getString("retention"));
				dto.setStatus(rs.getString("status"));
				dto.setMin_sum(rs.getInt("min_sum"));
				dto.setMax_sum(rs.getInt("max_sum"));
				dto.setRegister_date(rs.getDate("register_date"));
				dto.setEnd_date(rs.getDate("end_date"));

				res.add(dto);
			} 
		} catch (Exception e) {}
	}

	public Saving_infoDTO selectProUse(Saving_infoDTO dto){
		Saving_infoDTO SetDTO = null;

		sql =	"select * from saving_info where product = '"+dto.getProduct()+
				"' and status = '활성'";

		try {
			rs = stmt.executeQuery(sql);

			SetDTO = Saving_info(rs, dto);			
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return SetDTO;
	}

	public ArrayList<Saving_infoDTO> list(){
		ArrayList<Saving_infoDTO> res = new ArrayList<Saving_infoDTO>();

		sql = "select * from saving_info";

		try {
			rs = stmt.executeQuery(sql);

			Saving_info(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}

	public ArrayList<Saving_infoDTO> selectType(Saving_infoDTO dto){
		ArrayList<Saving_infoDTO> res = new ArrayList<Saving_infoDTO>();

		sql = "select * from saving_info where type = '"+dto.getType()+"'";

		try {

			rs = stmt.executeQuery(sql);

			Saving_info(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}

	public ArrayList<Saving_infoDTO> selectLikePro(Saving_infoDTO dto){
		ArrayList<Saving_infoDTO> res = new ArrayList<Saving_infoDTO>();

		sql = "select * from saving_info where product like '%"+dto.getProduct()+"%'";

		try {
			rs = stmt.executeQuery(sql);

			Saving_info(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}

	public ArrayList<Saving_infoDTO> selectLikeAnd(Saving_infoDTO dto){
		ArrayList<Saving_infoDTO> res = new ArrayList<Saving_infoDTO>();

		sql = 	"select * from saving_info where product like '%"+dto.getProduct()+"%' "+
				"AND type = '"+dto.getType()+"'";

		try {
			rs = stmt.executeQuery(sql);

			Saving_info(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}

	public void insert(Saving_infoDTO dto){
		sql = 	"insert into saving_info (" +
				"product, product_info, min_interest, max_interest, month, type, interest_type, tax, preferential,"+ 
				"prf_content, prf_Interest, min_sum, max_sum, partialization, retention, status, register_date, end_date) values ('"+
				dto.getProduct()+"','"+dto.getProduct_info()+"',"+ dto.getMin_interest()+","+
				dto.getMax_interest()+","+dto.getMonth()+",'"+dto.getType()+"','"+dto.getInterest_type()+"','"+
				dto.getTax()+"','"+dto.getPreferential()+"','"+dto.getPrf_content()+"','"+dto.getPrf_interest()+"',"+
				dto.getMin_sum()+","+dto.getMax_sum()+",'"+dto.getPartialization()+"','"+dto.getRetention()+"','활성',now(),null)";
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
