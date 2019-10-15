package jmodels;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sql.DataSource;

import control.Data_Source;
import jdbc.Menu.MenuDTO;
import jdbc.Transfer.Transfer_reserveDTO;
import server.DBAccess_IP;

public class Transfer_reserveDAO implements Serializable{

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String sql;

	private Transfer_reserveDAO() {
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
		public static final Transfer_reserveDAO DAO = new Transfer_reserveDAO();
	}

	public static Transfer_reserveDAO getInstance() {
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
				dto.setTime(rs.getDate("time"));
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
				dto.setTime(rs.getDate("time"));
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

			rs = stmt.executeQuery(sql);

			Transfer_reserve(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}

	////////////////////////


	///////////////////////////




	public Transfer_reserveDTO selectSeq(String seq){
		Transfer_reserveDTO dto = null;

		sql = 	"select * from transfer_reserve where seq = '"+seq+"'";
		System.out.println(sql);
		try {

			rs = stmt.executeQuery(sql);
			dto = Transfer_reserve(rs, dto);
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return dto;
	}

	public ArrayList<Transfer_reserveDTO> selectAccount(String account){
		ArrayList<Transfer_reserveDTO> res = new ArrayList<Transfer_reserveDTO>();

		sql = 	"select * from transfer_reserve where "+
				"account_number = '"+account+"' "+
				"ORDER BY scheduled_date desc";
		System.out.println(sql);
		try {	
			rs = stmt.executeQuery(sql);
			Transfer_reserve(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}

	public void insert(Transfer_reserveDTO dto){
		sql = 	"insert into transfer_reserve (" +
				"account_number, to_account_number, sum,"
				+ " time, memo, to_memo, "+ 
				"cms,status,scheduled_date, register_date) "+
				"values ('"+dto.getAccount_number()+"','"+dto.getTo_account_number()+
				"',"+dto.getSum()+",'"+dto.getTime()+"','"+dto.getMemo()+"','"+dto.getTo_memo()+
				"','"+dto.getCms()+"','"+dto.getStatus()+"','"+dto.getScheduled_date()+
				"', now() )";

		System.out.println(sql);
		try {

			stmt.executeUpdate(sql); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}



	public int reSeqInsert(Transfer_reserveDTO dto){
		int res = -1;

		sql = 	"insert into transfer_reserve (" +
				"account_number, to_account_number, sum,"
				+ " time, memo, to_memo, "+ 
				"cms,status,scheduled_date, register_date) "+
				"values ('"+dto.getAccount_number()+"','"+dto.getTo_account_number()+
				"',"+dto.getSum()+",'"+dto.getTime()+"','"+dto.getMemo()+"','"+dto.getTo_memo()+
				"','"+dto.getCms()+"','"+dto.getStatus()+"','"+dto.getScheduled_date()+
				"', now() )";

		System.out.println(sql);
		try {

			rs = stmt.executeQuery(sql);

			sql = "select max(seq) from transfer_reserve";

			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery(sql);//안에 아무것도 안들어감	

			rs.next();
			res = rs.getInt(1);


		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}






	public void updateTransfer_reserve(Transfer_reserveDTO dto){
		sql = 	"update transfer_reserve set " +
				"account_number = '"+dto.getAccount_number()+"' , to_account_number = '"+dto.getTo_account_number()+"', sum = "+dto.getSum()+", time = '"+dto.getTime()+"', memo = '"+dto.getMemo()+"' " +
				"to_memo = '"+dto.getTo_memo()+"'  cms = '"+dto.getCms()+"'  status = '"+dto.getStatus()+"' scheduled_date = '"+dto.getScheduled_date()+"'"+			
				"where seq = " +dto.getSeq();
		System.out.println(sql);
		try {stmt.executeUpdate(sql); 

		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	
	//////// 1015
	
	
	public void updateStatusBySeq(String seq,String status){
		sql = 	"update transfer_reserve set " +
				"status = '"+status +"' where seq = "+ seq;
		System.out.println(sql);
		try {stmt.executeUpdate(sql); 

		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	
	
	///////

	public ArrayList<Transfer_reserveDTO> SearchDate(String start,String end, String order){
		ArrayList<Transfer_reserveDTO> res = new ArrayList<Transfer_reserveDTO>();

		sql = 	"select * from transfer_reserve where "+
				"scheduled_date BETWEEN '"+start+"' and  '"+end+"' "+
				"ORDER BY scheduled_date ";
		sql+=order; 

		System.out.println(sql);
		try {
			rs = stmt.executeQuery(sql);

			Transfer_reserve(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}





	void close() {
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(stmt!=null) try {stmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
		sql = null;
	}
}
