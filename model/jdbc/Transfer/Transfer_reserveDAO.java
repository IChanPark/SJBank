package jdbc.Transfer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;
import jdbc.Menu.MenuDTO;

public class Transfer_reserveDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private Transfer_reserveDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final Transfer_reserveDTO DAO = new Transfer_reserveDTO();
    }
	
	public static Transfer_reserveDTO getInstance() {
        return Holder.DAO;
    }

	private Transfer_reserveDTO Transfer_reserve(ResultSet rs, Transfer_reserveDTO dto) {
		try {
			if(rs.next()) {
				dto = new Transfer_reserveDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setAccount_number(rs.getString("account_number"));
				dto.setTo_account_number(rs.getString("to_account_number"));
				dto.setSum(rs.getString("sum"));
				dto.setTime(rs.getString("time"));
				dto.setMemo(rs.getString("memo"));
				dto.setTo_memo(rs.getString("to_memo"));
				dto.setCms(rs.getString("cms"));
				dto.setStatus(rs.getString("status"));
				dto.setScheduled_date(rs.getString("scheduled_date"));
				dto.setRegister_date(rs.getDate("register_date"));
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Transfer_reserve(ResultSet rs, ArrayList<Transfer_reserveDTO> res) {
		try {
			while (rs.next()) {
				Transfer_reserveDTO dto = new Transfer_reserveDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setAccount_number(rs.getString("account_number"));
				dto.setTo_account_number(rs.getString("to_account_number"));
				dto.setSum(rs.getString("sum"));
				dto.setTime(rs.getString("time"));
				dto.setMemo(rs.getString("memo"));
				dto.setTo_memo(rs.getString("to_memo"));
				dto.setCms(rs.getString("cms"));
				dto.setStatus(rs.getString("status"));
				dto.setScheduled_date(rs.getString("scheduled_date"));
				dto.setRegister_date(rs.getDate("register_date"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<Transfer_reserveDTO> list(){
		ArrayList<Transfer_reserveDTO> res = new ArrayList<Transfer_reserveDTO>();
		
		sql = "select * from transfer_reserve";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Transfer_reserve(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public Transfer_reserveDTO selectSeq(String seq){
		Transfer_reserveDTO dto = null;
		
		sql = 	"select * from transfer_reserve where seq = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, seq);
			
			rs = pstmt.executeQuery();
			dto = Transfer_reserve(rs, dto);
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return dto;
	}
	
	public ArrayList<Transfer_reserveDTO> selectAccount(String account){
		ArrayList<Transfer_reserveDTO> res = new ArrayList<Transfer_reserveDTO>();
		
		sql = 	"select * from transfer_reserve where "+
				"account_number = ? "+
				"ORDER BY scheduled_date desc";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
		
			pstmt.setString(1, account);
			
			rs = pstmt.executeQuery();
			Transfer_reserve(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	
	public void insert(Transfer_reserveDTO dto){
		sql = 	"insert into transfer_reserve (" +
				"account_number, to_account_number, sum, time, memo, to_memo, "+ 
				"cms,status,scheduled_date, register_date) "+
				"values ("+
				"	?         ,       	?         ,	 ?	 ,?	 ,   ?,     ?	,"+ 
				" ? ,  ?   , 		 ?    ,		now()	 )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getAccount_number());
			pstmt.setString(2, dto.getTo_account_number());
			pstmt.setString(3, dto.getSum());
			pstmt.setString(4, dto.getTime());
			pstmt.setString(5, dto.getMemo());
			pstmt.setString(6, dto.getTo_memo());
			pstmt.setString(7, dto.getCms());
			pstmt.setString(8, dto.getStatus());
			pstmt.setString(9, dto.getScheduled_date());
			
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	public void updateTransfer_reserve(Transfer_reserveDTO dto){
		sql = 	"update transfer_reserve set " +
				"account_number = ? , to_account_number = ?, sum = ?, time = ?, memo = ? " +
				"to_memo = ?  cms = ?  status = ? scheduled_date = ?"		+		
				"where seq = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getAccount_number());
			pstmt.setString(2, dto.getTo_account_number());
			pstmt.setString(3, dto.getSum());
			pstmt.setString(4, dto.getTime());
			pstmt.setString(5, dto.getMemo());
			pstmt.setString(6, dto.getTo_memo());
			pstmt.setString(7, dto.getCms());
			pstmt.setString(8, dto.getStatus());
			pstmt.setString(9, dto.getScheduled_date());
			pstmt.setInt(10, dto.getSeq());
			
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
