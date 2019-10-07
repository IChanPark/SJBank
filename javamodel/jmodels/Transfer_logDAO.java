package jmodels;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;
import jdbc.Transfer.Transfer_logDTO;

public class Transfer_logDAO {

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String sql;
	
	private Transfer_logDAO() {

		try {
			String url ="jdbc:mariadb://192.168.1.14:3306/bank";
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
        public static final Transfer_logDAO DAO = new Transfer_logDAO();
    }
	
	public static Transfer_logDAO getInstance() {
        return Holder.DAO;
    }

	private void Transfer_log(ResultSet rs, Transfer_logDTO dto) {
		try {
			if(rs.next()) {
				dto = new Transfer_logDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setAccount_number(rs.getString("account_number"));
				dto.setSelf(rs.getString("self"));
				dto.setTarget(rs.getString("target"));
				dto.setTo_account_number("to_account_number");
				dto.setReceived(rs.getString("received"));
				dto.setSum(rs.getInt("sum"));
				dto.setFee(rs.getInt("fee"));
				dto.setCms(rs.getString("cms"));
				dto.setMemo(rs.getString("memo"));
				dto.setTo_memo(rs.getString("to_memo"));
				dto.setStatus(rs.getString("status"));
			} 
		} catch (Exception e) {}
	}
	
	private void Transfer_log(ResultSet rs, ArrayList<Transfer_logDTO> res) {
		try {
			while (rs.next()) {
				Transfer_logDTO dto = new Transfer_logDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setAccount_number(rs.getString("account_number"));
				dto.setSelf(rs.getString("self"));
				dto.setTarget(rs.getString("target"));
				dto.setTo_account_number("to_account_number");
				dto.setReceived(rs.getString("received"));
				dto.setSum(rs.getInt("sum"));
				dto.setFee(rs.getInt("fee"));
				dto.setCms(rs.getString("cms"));
				dto.setMemo(rs.getString("memo"));
				dto.setTo_memo(rs.getString("to_memo"));
				dto.setStatus(rs.getString("status"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<Transfer_logDTO> list(){
		ArrayList<Transfer_logDTO> res = new ArrayList<Transfer_logDTO>();
		
		sql = "select * from transfer_log";
		try {
			
			rs = stmt.executeQuery(sql);
			
			Transfer_log(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	public ArrayList<Transfer_logDTO> selectAN(String account_number){
		ArrayList<Transfer_logDTO> res = new ArrayList<Transfer_logDTO>();
		
		sql = "select * from transfer_log where account_number = '"+account_number+"'";
		System.out.println(sql);
		try {
			
			rs = stmt.executeQuery(sql);
			
			Transfer_log(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	
	public void insert(Transfer_logDTO dto){
		sql = 	"insert into transfer_log (" +
				"account_number,self,target,to_account_number,received,"+ 
				"sum,fee,cms,memo,to_memo,status,register_date) "+ 
				"values ('"+dto.getAccount_number()+"','"+dto.getSelf()+"','"+dto.getTarget()+"','"+dto.getTo_account_number()+"','"+dto.getReceived()
				+"',"+dto.getSum()+","+dto.getFee()+",'"+dto.getCms()+"','"+dto.getMemo()+"','"+dto.getTo_memo()+"','"+dto.getStatus()+", now() )";
		System.out.println(sql);
		try {
			
			rs = stmt.executeQuery(sql);
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	
	void close() {
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(stmt!=null) try {stmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
		sql = null;
	}
}
