package jdbc.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class QnaDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private QnaDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final QnaDAO DAO = new QnaDAO();
    }
	
	public static QnaDAO getInstance() {
        return Holder.DAO;
    }

	private QnaDTO Qna(ResultSet rs, QnaDTO dto) {
		try {
			if(rs.next()) {
				dto = new QnaDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setType(rs.getString("type"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setEmail(rs.getString("email"));
				dto.setStatus(rs.getString("status"));
				dto.setReply(rs.getInt("reply"));
				dto.setFile(rs.getString("file"));
				dto.setRegister_date(rs.getDate("register_date"));
			
			}			
		} catch (Exception e) {}
		return dto;
	}
	
	private void Qna(ResultSet rs, ArrayList<QnaDTO> res) {
		try {
			while (rs.next()) {
				QnaDTO dto = new QnaDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setType(rs.getString("type"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setEmail(rs.getString("email"));
				dto.setStatus(rs.getString("status"));
				dto.setReply(rs.getInt("reply"));
				dto.setFile(rs.getString("file"));
				dto.setRegister_date(rs.getDate("register_date"));
				
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<QnaDTO> list(){
		ArrayList<QnaDTO> res = new ArrayList<QnaDTO>();
		
		sql = "select * from qna";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Qna(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public ArrayList<QnaDTO> selectType(String type){
		ArrayList<QnaDTO> res = new ArrayList<QnaDTO>();
		
		sql = "select * from qna where type = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, type);
			
			rs = pstmt.executeQuery();
			
			Qna(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	
	public QnaDTO selectSeq(int seq){
		QnaDTO dto = null;
		
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
	
	public void insert(QnaDTO dto){
		sql = 	"insert into qna (" +
				"type, title, content, email, status, reply, file,  register_date) "+
				"values ("+
				"?   ,    ? ,	   ? ,  ?  ,   ?   , 	?  , ?  ,  now() )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getType());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getStatus());
			pstmt.setInt(6, dto.getReply());
			pstmt.setString(7, dto.getFile());			
			
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	public void update(QnaDTO dto){
		sql = 	"update qna set " +
				"type = ? , title = ? , content = ? , email = ? , status = ? , reply = ? , file = ? , register_date = now() " +
				"where seq = ? ";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getType());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getStatus());
			pstmt.setInt(6, dto.getReply());
			pstmt.setString(7, dto.getFile());
			
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
