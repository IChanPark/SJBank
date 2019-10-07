package jdbc.Transfer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;
import jdbc.Menu.MenuDTO;

public class Transfer_delayDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private Transfer_delayDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final Transfer_delayDAO DAO = new Transfer_delayDAO();
    }
	
	public static Transfer_delayDAO getInstance() {
        return Holder.DAO;
    }

	private Transfer_delayDTO Transfer_delay(ResultSet rs, Transfer_delayDTO dto) {
		try {
			if(rs.next()) {
				dto = new Transfer_delayDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setAccount_number(rs.getString("account_number"));
				dto.setTo_account_number(rs.getString("to_account_number"));
				dto.setSum(rs.getInt("sum"));
				dto.setMemo(rs.getString("memo"));
				dto.setTo_memo(rs.getString("to_memo"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
				dto.setTrs_time(rs.getDate("trs_time"));
				
				
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Transfer_delay(ResultSet rs, ArrayList<Transfer_delayDTO> res) {
		try {
			while (rs.next()) {
				Transfer_delayDTO dto = new Transfer_delayDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setAccount_number(rs.getString("account_number"));
				dto.setTo_account_number(rs.getString("to_account_number"));
				dto.setSum(rs.getInt("sum"));
				dto.setMemo(rs.getString("memo"));
				dto.setTo_memo(rs.getString("to_memo"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
				dto.setTrs_time(rs.getDate("trs_time"));
				
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<Transfer_delayDTO> list(){
		ArrayList<Transfer_delayDTO> res = new ArrayList<Transfer_delayDTO>();
		
		sql = "select * from transfer_delayDTO";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Transfer_delay(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public Transfer_delayDTO selectSeq(String seq){
		Transfer_delayDTO dto = null;
		
		sql = 	"select * from transfer_delay where seq = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, seq);
			
			rs = pstmt.executeQuery();
			dto = Transfer_delay(rs, dto);
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return dto;
	}
	
	public ArrayList<Transfer_delayDTO> selectAccount(String account){
		ArrayList<Transfer_delayDTO> res = new ArrayList<Transfer_delayDTO>();
		
		sql = 	"select * from transfer_delay where "+
				"account_number = ? ";
//		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
		
			pstmt.setString(1, account);
			
			rs = pstmt.executeQuery();
			Transfer_delay(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	
	public void insert(Transfer_delayDTO dto){
		sql = 	"insert into transfer_delay (" +
				"account_number, to_account_number, sum,memo,to_memo,"+
				"status,register_date,trs_time) "+
				"values ("+
				"	?          ,       	?         ,	 ? ,  ?    ,   ?,"+
				"   ?  ,  now()      ,   ?	)";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getAccount_number());
			pstmt.setString(2, dto.getTo_account_number());
			pstmt.setInt(3, dto.getSum());
			pstmt.setString(4, dto.getMemo());
			pstmt.setString(5, dto.getTo_memo());
			pstmt.setString(6, dto.getStatus());
			pstmt.setString(7, dto.gettrs_timeStr());
			
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	public void updateTransfer_delay(Transfer_delayDTO dto){
		sql = 	"update transfer_delay set " +
				"account_number = ? , to_account_number = ?, sum = ?, " +
				"memo = ? , to_memo = ?  , status = ? , register_date = ? ,trs_time = ? "	+		
				"where seq = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getAccount_number());
			pstmt.setString(2, dto.getTo_account_number());
			pstmt.setInt(3, dto.getSum());
			pstmt.setString(4, dto.getMemo());
			pstmt.setString(5, dto.getTo_memo());
			pstmt.setString(6, dto.getStatus());
			pstmt.setString(7, dto.getRegister_dateStr());
			pstmt.setString(8, dto.gettrs_timeStr());
			pstmt.setInt(9, dto.getSeq());
			
			pstmt.executeUpdate(); 
			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	
	
//////////////오후 6:37 2019-10-03 추가 1. 조회 상태 계좌번호로 부터	
	
	public ArrayList<Transfer_delayDTO> Search(String acc,String status ){
		ArrayList<Transfer_delayDTO> res = new ArrayList<Transfer_delayDTO>();
		String str="";
		if(!status.equals("전체"))
			str= " and status = '"+status+"'";
		else
			str="";
				
		sql = 	"select * from transfer_delay where "+
				"account_number = ? "+str;
//		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
		
			pstmt.setString(1, acc);
			rs = pstmt.executeQuery();
			Transfer_delay(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	
	
	
	//////////////////////////////////////////////////////////
	void close() {
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(pstmt!=null) try {pstmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
		sql = null;
	}
}
