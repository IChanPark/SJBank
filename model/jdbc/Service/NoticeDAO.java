package jdbc.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class NoticeDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private NoticeDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final NoticeDAO DAO = new NoticeDAO();
    }
	
	public static NoticeDAO getInstance() {
        return Holder.DAO;
    }

	private NoticeDTO Notice(ResultSet rs, NoticeDTO dto) {
		try {
			if(rs.next()) {
				dto = new NoticeDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setId(rs.getString("id"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
			
			}			
		} catch (Exception e) {}
		return dto;
	}
	
	private void Notice(ResultSet rs, ArrayList<NoticeDTO> res) {
		try {
			while (rs.next()) {
				NoticeDTO dto = new NoticeDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setId(rs.getString("id"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
			
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<NoticeDTO> list(){
		ArrayList<NoticeDTO> res = new ArrayList<NoticeDTO>();
		
		sql = "select * from notice";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Notice(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public ArrayList<NoticeDTO> selectID(String id){
		ArrayList<NoticeDTO> res = new ArrayList<NoticeDTO>();
		
		sql = "select * from notice where id = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			Notice(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	
	public NoticeDTO selectSeq(int seq){
		NoticeDTO dto = null;
		
		sql = "select * from notice where seq = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, seq);
			
			rs = pstmt.executeQuery();
			
			dto = Notice(rs, dto);
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return dto;
	}
	
	public void insert(NoticeDTO dto){
		sql = 	"insert into notice (" +
				"id, title, content, status, register_date) "+
				"values ("+
				"? ,    ? ,	   ?   ,   ?   , 	  now() )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getStatus());
			
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	public void update(NoticeDTO dto){
		sql = 	"update notice set " +
				"id = ? , title = ? , content = ? , status = ? register_date = now() " +
				"where seq = ? ";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getStatus());
			
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
