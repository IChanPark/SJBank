package jmodels;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import jdbc.Menu.MenuDTO;
import jdbc.Transfer.Transfer_delayDTO;
import server.DBAccess_IP;

public class Transfer_delayDAO implements Serializable{


	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String sql;
	
	private Transfer_delayDAO() {
		
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
		
		sql = "select * from transfer_delay";
		try {
			rs = stmt.executeQuery(sql);
			
			Transfer_delay(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public Transfer_delayDTO selectSeq(String seq){
		Transfer_delayDTO dto = null;
		
		sql = 	"select * from transfer_delay where seq = "+seq;
		System.out.println(sql);
		try {
			rs = stmt.executeQuery(sql);
			dto = Transfer_delay(rs, dto);
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return dto;
	}
	
	public ArrayList<Transfer_delayDTO> selectAccount(String account){
		ArrayList<Transfer_delayDTO> res = new ArrayList<Transfer_delayDTO>();
		
		sql = 	"select * from transfer_delay where "+
				"account_number =  '"+account+"'";
//		System.out.println(sql);
		try {	
			rs = stmt.executeQuery(sql);
			Transfer_delay(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	
	public void insert(Transfer_delayDTO dto){
		sql = 	"insert into transfer_delay (" +
				"account_number, to_account_number, sum,memo,to_memo,"+
				"status,register_date,trs_time) "+
				"values ('"+dto.getAccount_number()+"','"+dto.getTo_account_number()+"',"+dto.getSum()+
				",'"+dto.getMemo()+"','"+dto.getTo_memo()+"','"+dto.getStatus()+
				"', now()"+",'"+dto.gettrs_timeStr()+"')";
		System.out.println(sql);
		try {
			stmt.executeUpdate(sql); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	public void updateTransfer_delay(Transfer_delayDTO dto){
		sql = 	"update transfer_delay set " +
				"account_number = '"+dto.getAccount_number()+"' , to_account_number = '"+dto.getTo_account_number()+"', sum = "+dto.getSum()+", " +
				"memo = '"+dto.getMemo()+"' , to_memo = '"+dto.getTo_memo()+"'  , status = "+dto.getStatus()
				+" , register_date = '"+dto.getRegister_dateStr()+"' ,trs_time = '"	+dto.gettrs_timeStr()+"' where seq = "+dto.getSeq();
		System.out.println(sql);
		try {
			
			stmt.executeUpdate(sql); 
			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	
	
//////////////오후 6:37 2019-10-03 추가 1. 조회 상태 계좌번호로 부터	
	
	public ArrayList<Transfer_delayDTO> Search(String acc,String status){
		ArrayList<Transfer_delayDTO> res = new ArrayList<Transfer_delayDTO>();
		
		String str="";
		if(status.equals(""))
		{
			
		}
		else
		{
			str= " and status = '"+status+"' ";
		}
		
		sql = 	"select * from transfer_delay where "+
				"account_number = '"+acc+"' "+ str;
//		System.out.println(sql);
		try {
			rs = stmt.executeQuery(sql);
			Transfer_delay(rs, res);	
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
