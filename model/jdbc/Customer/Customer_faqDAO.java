package jdbc.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;
import control.Data_Source;

public class Customer_faqDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;

	private Customer_faqDAO() {
		ds = Data_Source.getInstance().getDs();
	}

	private static class Holder {
		public static final Customer_faqDAO DAO = new Customer_faqDAO();
	}

	public static Customer_faqDAO getInstance() {
		return Holder.DAO;
	}
	
	private Customer_faqDTO Customer_faq(ResultSet rs, Customer_faqDTO dto) {
		try {
			if(rs.next()) {
				dto = new Customer_faqDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setId(rs.getString("id"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setType(rs.getString("type"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
				
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Customer_faq(ResultSet rs, ArrayList<Customer_faqDTO> res) {
		try {
			while (rs.next()) {
				Customer_faqDTO dto = new Customer_faqDTO();
				dto = new Customer_faqDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setId(rs.getString("id"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setType(rs.getString("type"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));							
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	

	public ArrayList<Customer_faqDTO> list(){
		ArrayList<Customer_faqDTO> res = new ArrayList<Customer_faqDTO>();
		
		sql = "select * from faq";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Customer_faq(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public Customer_faqDTO selectId(String id){
		Customer_faqDTO dto = null;
		
		sql = 	"select * from faq where id = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			dto = Customer_faq(rs, dto);
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return dto;
	}
	
	public Customer_faqDTO selectSeq(int seq){
		Customer_faqDTO dto = null;
		
		sql = 	"select * from faq where seq = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, seq);
			
			rs = pstmt.executeQuery();
			dto = Customer_faq(rs, dto);
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return dto;
	}
	
	
	
	public void insert(Customer_faqDTO dto){
		sql = 	"insert into faq (" +
				"id, title, content, type, status, register_date) "+
				"values "+
			    "(? ,   ? ,	  ?	 ,	  ?	,    '활성' ,	now() )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getType());
			pstmt.setString(5, dto.getStatus());
						
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}	
	
	void close() {
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(pstmt!=null) try {pstmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
		sql = null;
	}
}
