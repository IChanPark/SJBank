package jdbc.Security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;
import jdbc.Loan.LoanDTO;

public class OTPDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private OTPDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final OTPDAO DAO = new OTPDAO();
    }
	
	public static OTPDAO getInstance() {
        return Holder.DAO;
    }

	private OTPDTO OTP(ResultSet rs, OTPDTO dto) {
		try {
			if(rs.next()) {
				dto = new OTPDTO();
				dto.setSerial(rs.getString("serial"));
				dto.setId(rs.getString("id"));
				dto.setType(rs.getString("Type"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
				dto.setEnd_date(rs.getDate("end_date"));
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void OTP(ResultSet rs, ArrayList<OTPDTO> res) {
		try {
			while (rs.next()) {
				OTPDTO dto = new OTPDTO();
				dto.setSerial(rs.getString("serial"));
				dto.setId(rs.getString("id"));
				dto.setType(rs.getString("Type"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
				dto.setEnd_date(rs.getDate("end_date"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<OTPDTO> list(){
		ArrayList<OTPDTO> res = new ArrayList<OTPDTO>();
		
		sql = "select * from security";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			OTP(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}
	
	
	public ArrayList<OTPDTO> selectID(String id){
		ArrayList<OTPDTO> res = new ArrayList<OTPDTO>();
		
		sql = "select * from otp where id = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			OTP(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	
	
	
	
	public void insert(OTPDTO dto){
		sql = 	"insert into otp (" +
				"id, serial, type, status, register_date, end_date) values ("+
				" ?,   ?   ,'인터넷OTP',  '활성',	 now()		,	 adddate(NOW(),INTERVAL 1 year)  )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getSerial());
			
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	
	public void UpdateToInactive(String id){
		sql = 	"update otp set status = '비활성' ";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	
	
	//////////////////////////////////// 09.30 h 추가
	
	public boolean chkSerial(String serial){
		boolean res = false;
		
		sql = "select serial from otp where serial = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, serial);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				res=true;
			
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	
	
	
	
	
	
	
	void close() {
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(pstmt!=null) try {pstmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
		sql = null;
	}
}
