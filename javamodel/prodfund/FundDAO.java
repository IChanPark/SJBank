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

public class FundDAO {

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String sql;
	
	public FundDAO() {
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
	
	private static class Holder {
        public static final FundDAO DAO = new FundDAO();
    }
	
	public static FundDAO getInstance() {
        return Holder.DAO;
    }

	private FundDTO Fund(ResultSet rs, FundDTO dto) {
		try {
			if(rs.next()) {
				dto = new FundDTO();
				dto.setAccount_number(rs.getString("account_number"));
				dto.setId(rs.getString("id"));
				dto.setProduct(rs.getString("product"));
				dto.setFluctuation(rs.getFloat("fluctuation"));
				dto.setAmount(rs.getFloat("amount"));
				dto.setNowmoney(rs.getFloat("nowmoney"));
				dto.setExchange(rs.getFloat("exchange"));
				dto.setBuynum(rs.getInt("buynum"));
				dto.setRest(rs.getFloat("rest"));
			
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Fund(ResultSet rs, ArrayList<FundDTO> res) {
		try {
			while (rs.next()) {
				FundDTO dto = new FundDTO();
				dto = new FundDTO();
				dto.setAccount_number(rs.getString("account_number"));
				dto.setId(rs.getString("id"));
				dto.setProduct(rs.getString("product"));
				dto.setFluctuation(rs.getFloat("fluctuation"));
				dto.setAmount(rs.getFloat("amount"));
				dto.setNowmoney(rs.getFloat("nowmoney"));
				dto.setExchange(rs.getFloat("exchange"));
				dto.setBuynum(rs.getInt("buynum"));
				dto.setRest(rs.getFloat("rest"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<FundDTO> list(){
		ArrayList<FundDTO> res = new ArrayList<FundDTO>();
		
		sql = "select * from fund";
		try {
		
			rs = stmt.executeQuery(sql);
			
			Fund(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public ArrayList<FundDTO> selectID(String id){
		ArrayList<FundDTO> res = new ArrayList<FundDTO>();
		
		sql = "select * from fund where id = '"+id+"'";
		System.out.println(sql);
		try {
			rs = stmt.executeQuery(sql);
			
			Fund(rs, res);	
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public FundDTO selectAccount(String acc){
		FundDTO dto = null;
		
		sql = "select * from fund where account_number = '"+acc+"'";
		System.out.println(sql);
		try {
			
			rs = stmt.executeQuery(sql);
			
			dto = Fund(rs, dto);
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return dto;
	}
	
	public void insert(FundDTO dto){
		sql = 	"insert into fund (" +
				"account_number, id, product,fluctuation) "
				+ "values ('"+dto.getAccount_number()+"','"+dto.getId()+"','"+
				dto.getProduct()+"',"+dto.getFluctuation()+")";
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	public void updatetoday(Float fluctuation,Float nowmoney,Float exchange,Float price_modify,String account_number){
		sql = 	"update fund set " +
				"fluctuation = "+fluctuation+
				"nowmoney = "+nowmoney+
				"exchange = "+exchange+
				"price_modify = "+price_modify+
				" where account_number = '"+account_number+"'";
		System.out.println(sql);
		try {
			
			stmt.executeUpdate(sql); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	public void updateprice_modify(String price_modify, String product){
		sql = 	"update fund set " +
				"price_modify = "+price_modify+
				" where product = '"+product+"'";
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
