package jdbc.Transfer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;
import jdbc.Menu.MenuDTO;

public class Transfer_autoDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private Transfer_autoDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final Transfer_autoDAO DAO = new Transfer_autoDAO();
    }
	
	public static Transfer_autoDAO getInstance() {
        return Holder.DAO;
    }

	private Transfer_autoDTO Transfer_auto(ResultSet rs, Transfer_autoDTO dto) {
		try {
			if(rs.next()) {
				dto = new Transfer_autoDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setAccount_number(rs.getString("account_number"));
				dto.setTo_account_number(rs.getString("to_account_number"));
				dto.setSum(rs.getInt("sum"));
				dto.setPeriod(rs.getString("period"));
				dto.setStart_date(rs.getDate("start_date"));
				dto.setFinish_date(rs.getDate("finish_date"));
				dto.setLast_day(rs.getString("last_day"));
				dto.setMemo(rs.getString("memo"));
				dto.setTo_memo(rs.getString("to_memo"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
				dto.setEnd_date(rs.getDate("end_date"));
				
				
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Transfer_auto(ResultSet rs, ArrayList<Transfer_autoDTO> res) {
		try {
			while (rs.next()) {
				Transfer_autoDTO dto = new Transfer_autoDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setAccount_number(rs.getString("account_number"));
				dto.setTo_account_number(rs.getString("to_account_number"));
				dto.setSum(rs.getInt("sum"));
				dto.setPeriod(rs.getString("period"));
				dto.setStart_date(rs.getDate("start_date"));
				dto.setFinish_date(rs.getDate("finish_date"));
				dto.setLast_day(rs.getString("last_day"));
				dto.setMemo(rs.getString("memo"));
				dto.setTo_memo(rs.getString("to_memo"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
				dto.setEnd_date(rs.getDate("end_date"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<Transfer_autoDTO> list(){
		ArrayList<Transfer_autoDTO> res = new ArrayList<Transfer_autoDTO>();
		
		sql = "select * from transfer_auto";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Transfer_auto(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public Transfer_autoDTO selectSeq(String seq){
		Transfer_autoDTO dto = null;
		
		sql = 	"select * from Transfer_auto where seq = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, seq);
			
			rs = pstmt.executeQuery();
			dto = Transfer_auto(rs, dto);
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return dto;
	}
	
	public ArrayList<Transfer_autoDTO> selectAccount(String account){
		ArrayList<Transfer_autoDTO> res = new ArrayList<Transfer_autoDTO>();
		
		sql = 	"select * from transfer_auto where "+
				"account_number = ? "+
				"ORDER BY finish_date desc";
//		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
		
			pstmt.setString(1, account);
			
			rs = pstmt.executeQuery();
			Transfer_auto(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	
	public void insert(Transfer_autoDTO dto){
		sql = 	"insert into transfer_auto (" +
				"account_number, to_account_number, sum, period, start_date, finish_date, last_day,memo,to_memo,"+
				"status,register_date,end_date) "+
				"values ("+
				"	?          ,       	?         ,	 ? ,  ?    ,   ?       ,     ?	    ,      ?  ,  ?  ,  ?   ,"+
				"   ?  ,  now()      ,   ?	)";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getAccount_number());
			pstmt.setString(2, dto.getTo_account_number());
			pstmt.setInt(3, dto.getSum());
			pstmt.setString(4, dto.getPeriod());
			pstmt.setString(5, dto.getStart_dateStr());
			pstmt.setString(6, dto.getFinish_dateStr());
			pstmt.setString(7, dto.getLast_day());
			pstmt.setString(8, dto.getMemo());
			pstmt.setString(9, dto.getTo_memo());
			pstmt.setString(10, dto.getStatus());
			pstmt.setString(11, dto.getend_dateStr());
			
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	public void updateTransfer_auto(Transfer_autoDTO dto){
		sql = 	"update Transfer_auto set " +
				"account_number = ? , to_account_number = ?, sum = ?, period = ?, start_date = ? ," +
				"finish_date = ? , last_day = ?  memo = ? , to_memo = ?  , status = ? , register_date = ? , end_date = ? "	+		
				"where seq = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getAccount_number());
			pstmt.setString(2, dto.getTo_account_number());
			pstmt.setInt(3, dto.getSum());
			pstmt.setString(4, dto.getPeriod());
			pstmt.setString(5, dto.getStart_dateStr());
			pstmt.setString(6, dto.getFinish_dateStr());
			pstmt.setString(7, dto.getLast_day());
			pstmt.setString(8, dto.getMemo());
			pstmt.setString(9, dto.getTo_memo());
			pstmt.setString(10, dto.getStatus());
			pstmt.setString(11, dto.getRegister_dateStr());
			pstmt.setString(12, dto.getend_dateStr());
			pstmt.setInt(13, dto.getSeq());
			
			pstmt.executeUpdate(); 
			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	
	
//////////////오후 6:37 2019-10-03 추가 1. 조회 상태 계좌번호로 부터	
	
	public ArrayList<Transfer_autoDTO> Search(String acc,String status){
		ArrayList<Transfer_autoDTO> res = new ArrayList<Transfer_autoDTO>();
		
		String str="";
		if(status.equals(""))
		{
			
		}
		else
		{
			str= " and status = '"+status+"' ";
		}
		
		sql = 	"select * from transfer_auto where "+
				"account_number = ? "+ str+
				"ORDER BY finish_date desc";
//		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
		
			pstmt.setString(1, acc);
			rs = pstmt.executeQuery();
			Transfer_auto(rs, res);	
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
