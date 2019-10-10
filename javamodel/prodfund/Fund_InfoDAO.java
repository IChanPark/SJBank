package prodfund;

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
import jdbc.Fund.Fund_InfoDTO;
import server.DBAccess_IP;

public class Fund_InfoDAO {


	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String sql;
	
	public Fund_InfoDAO() {
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


	private Fund_InfoDTO Fund_Info(ResultSet rs, Fund_InfoDTO dto) {
		try {
			if(rs.next()) {
				dto = new Fund_InfoDTO();
				dto.setProduct(rs.getString("product"));
				dto.setProduct_info(rs.getString("product_info"));
				dto.setType(rs.getString("type"));
				dto.setArea(rs.getString("area"));
				dto.setProperty(rs.getString("property"));
				dto.setManagement(rs.getString("management"));
				dto.setSector(rs.getString("sector"));
				dto.setStatus(rs.getString("status"));
				dto.setTax(rs.getString("tax"));
				
				dto.setPrice(rs.getFloat("price"));
				dto.setPrice_modify(rs.getFloat("price_modify"));
				dto.setFirst_fee(rs.getFloat("first_fee"));
				dto.setFee(rs.getFloat("fee"));
			
				dto.setRegister_date(rs.getDate("register_date"));
				dto.setEnd_date(rs.getDate("end_date"));
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Fund_Info(ResultSet rs, ArrayList<Fund_InfoDTO> res) {
		try {
			while (rs.next()) {
				Fund_InfoDTO dto = new Fund_InfoDTO();
				dto = new Fund_InfoDTO();
				dto.setProduct(rs.getString("product"));
				dto.setProduct_info(rs.getString("product_info"));
				dto.setType(rs.getString("type"));
				dto.setArea(rs.getString("area"));
				dto.setProperty(rs.getString("property"));
				dto.setManagement(rs.getString("management"));
				dto.setSector(rs.getString("sector"));
				dto.setStatus(rs.getString("status"));
				dto.setTax(rs.getString("tax"));
				
				dto.setPrice(rs.getFloat("price"));
				dto.setPrice_modify(rs.getFloat("price_modify"));
				dto.setFirst_fee(rs.getFloat("first_fee"));
				dto.setFee(rs.getFloat("fee"));

				dto.setRegister_date(rs.getDate("register_date"));
				dto.setEnd_date(rs.getDate("end_date"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<Fund_InfoDTO> list(){
		ArrayList<Fund_InfoDTO> res = new ArrayList<Fund_InfoDTO>();
		
		sql = "select * from fund_info";
		try {
		
			rs = stmt.executeQuery(sql);
			
			Fund_Info(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public ArrayList<Fund_InfoDTO> selectID(String product){
		ArrayList<Fund_InfoDTO> res = new ArrayList<Fund_InfoDTO>();
		
		sql = "select * from fund_info where product = '"+product+"'";
		System.out.println(sql);
		try {
			
			rs = stmt.executeQuery(sql);
			
			Fund_Info(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	
	public void insert(Fund_InfoDTO dto){
		sql = 	"insert into fund_info (" +
				"product,product_info,price,price_modify,"
				+ "type,area,property,first_fee,"+ 
				"fee,management,sector,status,tax,"
				+ "register_date, end_date) "+
				"values ('"+dto.getProduct()+"','"+dto.getProduct_info()+"',"+
				dto.getPrice()+","+dto.getPrice_modify()+",'"+dto.getType()+"','"+
				dto.getArea()+"','"+dto.getProperty()+"',"+ dto.getFirst_fee()+","+
				dto.getFee()+",'"+ dto.getManagement()+"','"+dto.getSector()+"','"+
				dto.getStatus()+"','"+dto.getTax()+"',now(), '2020-10-05')";
		System.out.println(sql);
		try {
			
			
			stmt.executeUpdate(sql); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	public void updateprice_modify(String Price_modify,String Product ){
		sql = 	"update fund_info set " +
				"price_modify = "+Price_modify+
				" where product = '"+Product+"'";
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
