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
	
	public QnaDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final QnaDAO DAO = new QnaDAO();
    }
	
	public static QnaDAO getInstance() {
        return Holder.DAO;
    }

	public int total(){
		int res =0;
		
		try {
			sql = "select count(*) from center";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			res = rs.getInt(1);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		
		return res;
	}
	
	private QnaDTO Qna(ResultSet rs, QnaDTO dto) {
		try {
			if(rs.next()) {
				dto = new QnaDTO();
				
				dto.setLev(rs.getInt("lev"));
				dto.setRseq(rs.getInt("rseq"));
				dto.setGid(rs.getInt("gid"));
				dto.setSeq(rs.getInt("seq"));
				dto.setType(rs.getString("type"));
				dto.setTitle(rs.getString("title"));
				dto.setName(rs.getString("name"));
				dto.setContent(rs.getString("content"));				
				dto.setStatus(rs.getString("status"));				
				dto.setRegister_date(rs.getDate("register_date"));
			
			}			
		} catch (Exception e) {}
		return dto;
	}
	
	private void Qna(ResultSet rs, ArrayList<QnaDTO> res) {
		try {
			while (rs.next()) {
				QnaDTO dto = new QnaDTO();
				
				dto.setLev(rs.getInt("lev"));
				dto.setRseq(rs.getInt("rseq"));
				dto.setGid(rs.getInt("gid"));
				dto.setSeq(rs.getInt("seq"));
				dto.setType(rs.getString("type"));
				dto.setTitle(rs.getString("title"));
				dto.setName(rs.getString("name"));
				dto.setContent(rs.getString("content"));				
				dto.setStatus(rs.getString("status"));				
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
	
	public ArrayList<QnaDTO> selectID(String id){
		ArrayList<QnaDTO> res = new ArrayList<QnaDTO>();
		
		sql = "select * from qna where name = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
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
		
		sql = "select * from qna where rseq = ? ";
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
		
	try {	
		// seq 의 가장 큰값 가져와 +1하여 seq 구하기
		sql = "select max(seq) from qna";
		
		con = ds.getConnection();
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		rs.next();
		dto.setSeq(rs.getInt(1)+1);
		
		// 저장
		sql = 	"insert into qna (" +
				"seq, gid, lev, rseq, type, title, name, content,  status, register_date) "+
		"values ("+	"?, ? , 0,   0  ,   ?   , ?  ,  ?   ,    ?,      ?    ,    now() )";
		System.out.println(sql);
		con = ds.getConnection();
		pstmt = con.prepareStatement(sql);
		
			pstmt.setInt(1, dto.getSeq());
			pstmt.setInt(2, dto.getSeq());
			
			pstmt.setString(3, dto.getType());
			pstmt.setString(4, dto.getTitle());
			pstmt.setString(5, dto.getName());
			pstmt.setString(6, dto.getContent());
			pstmt.setString(7, dto.getStatus());			
			
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { 
			close(); 
		}
	}
	
public void reply(QnaDTO dto){
		 
		
		try {
			
			
			////기존글 업데이트
			sql = "update qna set seq = seq +1 where "
			         + " gid = ? and seq > ? ";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getGid());
			pstmt.setInt(2, dto.getSeq());
			pstmt.executeUpdate();
			
			
			///새글
			sql = "insert into center (gid,lev,rseq,  pname,  content, title, regdate  , cate) "
					         + "values(?    ,?  ,? ,    ?  ,     ? ,    ?,    sysdate(),'notice' ) ";
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setInt(1, dto.getGid());
			pstmt.setInt(2, dto.getLev()+1);
			pstmt.setInt(3, dto.getRseq()+1);
			pstmt.setString(4, dto.getName());			
			pstmt.setString(5, dto.getContent());
			pstmt.setString(6, dto.getTitle());
			pstmt.executeUpdate();

			//현재 id 구하기
			sql = "select max(id) from center";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			rs.next();
			dto.setSeq(rs.getInt(1));
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		
	}
	
	
	
	public void update(QnaDTO dto){
		sql = 	"update qna set " +
				"type = ? , title = ? , name = ? , content = ? , status = ? , register_date = now() " +
				"where seq = ? ";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getType());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getContent());			
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
