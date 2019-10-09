package jdbc.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class FaqDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private FaqDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final FaqDAO DAO = new FaqDAO();
    }
	
	public static FaqDAO getInstance() {
        return Holder.DAO;
    }

	private FaqDTO Faq(ResultSet rs, FaqDTO dto) {
		try {
			if(rs.next()) {
				dto = new FaqDTO();
				
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
	
	private void Faq(ResultSet rs, ArrayList<FaqDTO> res) {
		try {
			while (rs.next()) {
				FaqDTO dto = new FaqDTO();
				
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
	
	public ArrayList<FaqDTO> list(){
		ArrayList<FaqDTO> res = new ArrayList<FaqDTO>();
		
		sql = "select * from faq";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Faq(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public ArrayList<FaqDTO> selectID(String id){
		ArrayList<FaqDTO> res = new ArrayList<FaqDTO>();
		
		sql = "select * from faq where id = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			Faq(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	
	public ArrayList<FaqDTO> selectType(FaqDTO dto){
		ArrayList<FaqDTO> res = new ArrayList<FaqDTO>();
		
		sql = "select * from faq where type = ?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getType());
			
			rs = pstmt.executeQuery();
			
			Faq(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public FaqDTO selectTitle(String title){
		FaqDTO dto = null;
		
		sql = "select * from faq where title = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, title);
			
			rs = pstmt.executeQuery();
			
			dto = Faq(rs, dto);
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return dto;
	}
	
	public ArrayList<FaqDTO> selectLikePro(FaqDTO dto){
		ArrayList<FaqDTO> res = new ArrayList<FaqDTO>();
		
		sql = "select * from faq where title like ?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, "%"+dto.getTitle()+"%");
			
			rs = pstmt.executeQuery();
			
			Faq(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public ArrayList<FaqDTO> selectLikeAnd(FaqDTO dto){
		ArrayList<FaqDTO> res = new ArrayList<FaqDTO>();
		
		sql = 	"select * from faq where title like ?"+
				"AND type = ?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, "%"+dto.getTitle()+"%");
			pstmt.setString(2, dto.getType());
			
			rs = pstmt.executeQuery();
			
			Faq(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public void insert(FaqDTO dto){
		sql = 	"insert into faq (" +
				"id, title, content, type, status, register_date) "+
				"values ("+
				"? ,    ? ,	   ?   ,  ?  ,   ?   , 	  now() )";
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
	
	public void update(FaqDTO dto){
		sql = 	"update faq set " +
				"id = ? , title = ? , content = ? , type = ? , status = ? register_date = now() " +
				"where seq = ? ";
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
