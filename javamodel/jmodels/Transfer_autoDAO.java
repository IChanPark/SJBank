package jmodels;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;
import jdbc.Menu.MenuDTO;
import jdbc.Transfer.Transfer_autoDTO;
import server.Access_IP;
import server.DBAccess_IP;

public class Transfer_autoDAO implements Serializable {

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String sql;
	
	private Transfer_autoDAO() {
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
        public static final Transfer_autoDAO DAO = new Transfer_autoDAO();
    }
	
	public static Transfer_autoDAO getInstance() {
        return Holder.DAO;
    }

	private Transfer_autoDTO  Transfer_auto(ResultSet rs, Transfer_autoDTO dto) {
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
			
			stmt.executeQuery(sql);
			
			Transfer_auto(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public Transfer_autoDTO selectSeq(String seq){
		Transfer_autoDTO dto = null;
		
		sql = 	"select * from transfer_auto where seq = '"+seq+"'";
		System.out.println(sql);
		try {
			
			
			rs = stmt.executeQuery(sql);
			dto = Transfer_auto(rs, dto);
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return dto;
	}
	
	public ArrayList<Transfer_autoDTO> selectAccount(String account){
		ArrayList<Transfer_autoDTO> res = new ArrayList<Transfer_autoDTO>();
		
		sql = 	"select * from transfer_auto where "+
				"account_number = '"+account +
				"'  ORDER BY finish_date desc";
//		System.out.println(sql);
		try {
			
			rs = stmt.executeQuery(sql);
			Transfer_auto(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	
	public void insert(Transfer_autoDTO dto){
		sql = 	"insert into transfer_auto (" +
				"account_number, to_account_number, sum, period, start_date, finish_date, last_day,memo,to_memo,"+
				"status,register_date,end_date) "+
				"values ('"+dto.getAccount_number()+"','"+dto.getTo_account_number()+"',"+
				dto.getSum()+",'"+dto.getPeriod()+"','"+dto.getStart_dateStr()+"','"+dto.getLast_day()+"','"+dto.getMemo()+"','"+dto.getTo_memo()+"','"+
				dto.getStatus()+"', now() ,'"+dto.getend_dateStr()+"'";
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	
	
	public int reSeqInsert(Transfer_autoDTO dto){
		
		int res = -1;
		
		sql = 	"insert into transfer_auto (" +
				"account_number, to_account_number, sum, period, start_date, finish_date, last_day,memo,to_memo,"+
				"status,register_date,end_date) "+
				"values ('"+dto.getAccount_number()+"','"+dto.getTo_account_number()+"',"+
				dto.getSum()+",'"+dto.getPeriod()+"','"+dto.getStart_dateStr()+"','"+dto.getLast_day()+"','"+dto.getMemo()+"','"+dto.getTo_memo()+"','"+
				dto.getStatus()+"', now() ,'"+dto.getend_dateStr()+"'";
		
		System.out.println(sql);
		try {
			
			rs = stmt.executeQuery(sql);
			
			sql = "select max(seq) from transfer_auto";
			
			stmt = con.prepareStatement(sql);
			
			rs = stmt.executeQuery(sql);//안에 아무것도 안들어감	
			
			rs.next();
			res = rs.getInt(1);
			
			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	
	
	
	
	
	
	public void updateTransfer_auto(Transfer_autoDTO dto){
		sql = 	"update Transfer_auto set " +
				"account_number = '"+dto.getAccount_number()+"' , to_account_number = '"+dto.getTo_account_number()+"' , sum = "+dto.getSum()+", period = '"+dto.getPeriod()+"' , start_date = '"+dto.getStart_dateStr()+"' , " +
				"finish_date = '"+dto.getFinish_dateStr()+"' , last_day = '"+dto.getLast_day()+"'  memo = '"+dto.getMemo()+"' , to_memo = '"+dto.getTo_memo()+"'  , status = '"+dto.getStatus()+"' , register_date = '"+dto.getRegister_dateStr()+"' , end_date = '"+dto.getend_dateStr()+"' "	+		
				"where seq = "+dto.getSeq();
		System.out.println(sql);
		try {
			
			stmt.executeUpdate(sql); 
			
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
			str= "  and status = '"+status+"' ";
		}
		
		sql = 	"select * from transfer_auto where "+
				"account_number = "+acc+  str+
				"ORDER BY finish_date desc";
//		System.out.println(sql);
		try {
			
			rs = stmt.executeQuery(sql);
			Transfer_auto(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	
	
	
	//////////////////////////////////////////////////////////
	void close() {
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(stmt!=null) try {stmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
		sql = null;
	}
}
