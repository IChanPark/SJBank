package jdbc.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class Qna_replyDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private Qna_replyDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final Qna_replyDAO DAO = new Qna_replyDAO();
    }
	
	public static Qna_replyDAO getInstance() {
        return Holder.DAO;
    }

	private Qna_replyDTO Qna(ResultSet rs, Qna_replyDTO dto) {
		try {
			if(rs.next()) {
				dto = new Qna_replyDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setId(rs.getString("id"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setRegister_date(rs.getDate("register_date"));
			
			}			
		} catch (Exception e) {}
		return dto;
	}
	
	private void Qna_reply(ResultSet rs, ArrayList<Qna_replyDTO> res) {
		try {
			while (rs.next()) {
				Qna_replyDTO dto = new Qna_replyDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setId(rs.getString("id"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setRegister_date(rs.getDate("register_date"));
			
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<Qna_replyDTO> list(){
		ArrayList<Qna_replyDTO> res = new ArrayList<Qna_replyDTO>();
		
		sql = "select * from qna_reply";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Qna_reply(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public ArrayList<Qna_replyDTO> selectType(String id){
		ArrayList<Qna_replyDTO> res = new ArrayList<Qna_replyDTO>();
		
		sql = "select * from Qna_reply where id = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			Qna_reply(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	
	public Qna_replyDTO selectSeq(int seq){
		Qna_replyDTO dto = null;
		
		sql = "select * from qna where seq = ? ";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, seq);
			
			rs = pstmt.executeQuery();
			
			dto = Qna(rs, dto);
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return dto;
	}
	
	public void insert(Qna_replyDTO dto){
		sql = 	"insert into qna_reply (" +
				"id, title, content,   register_date) "+
				"values ("+
				"? ,  ?   , 	?     ,   now() )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getRegister_dateStr());
			
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	public void update(QnaDTO dto){
		sql = 	"update qna set " +
				"title = ? , content = ?  " +
				"where seq = ? ";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			
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
