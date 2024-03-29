package jdbc.Transfer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class Transfer_logDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;

	private Transfer_logDAO() {
		ds = Data_Source.getInstance().getDs();
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
				dto.setFeetype(rs.getString("feetype"));
				dto.setTarget(rs.getString("target"));
				dto.setTo_account_number("to_account_number");
				dto.setReceived(rs.getString("received"));
				dto.setSum(rs.getLong("sum"));
				dto.setFee(rs.getInt("fee"));
				dto.setCms(rs.getString("cms"));
				dto.setMemo(rs.getString("memo"));
				dto.setTo_memo(rs.getString("to_memo"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
			} 
		} catch (Exception e) {}
	}

	private void Transfer_log(ResultSet rs, ArrayList<Transfer_logDTO> res) {
		try {
			while (rs.next()) {
				Transfer_logDTO dto = new Transfer_logDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setAccount_number(rs.getString("account_number"));
				dto.setFeetype(rs.getString("feetype"));
				dto.setTarget(rs.getString("target"));
				dto.setTo_account_number(rs.getString("to_account_number"));
				dto.setReceived(rs.getString("received"));
				dto.setSum(rs.getLong("sum"));
				dto.setFee(rs.getInt("fee"));
				dto.setCms(rs.getString("cms"));
				dto.setMemo(rs.getString("memo"));
				dto.setTo_memo(rs.getString("to_memo"));
				dto.setStatus(rs.getString("status"));
				dto.setRegister_date(rs.getDate("register_date"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}

	public ArrayList<Transfer_logDTO> list(){
		ArrayList<Transfer_logDTO> res = new ArrayList<Transfer_logDTO>();

		sql = "select * from transfer_log";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			Transfer_log(rs, res);			
		} catch (Exception e) { e.printStackTrace(); }
		finally { close(); }
		return res;
	}

	public ArrayList<Transfer_logDTO> selectAN(String account_number){
		ArrayList<Transfer_logDTO> res = new ArrayList<Transfer_logDTO>();

		sql = "select * from transfer_log where account_number = ?  ";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, account_number);

			rs = pstmt.executeQuery();

			Transfer_log(rs, res);	


		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		System.out.println("리턴하자???" + res);
		return res;
	}

	//////////////////////// 1009 받는 경우


	public ArrayList<Transfer_logDTO> selectToAN(String account_number){
		ArrayList<Transfer_logDTO> res = new ArrayList<Transfer_logDTO>();

		sql = "select * from transfer_log where to_account_number =  ?  ";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, account_number);

			rs = pstmt.executeQuery();

			Transfer_log(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}

	/////////////////////////////////////

	////////////////////////1009 받는 경우


	public ArrayList<Transfer_logDTO> selectANbyDay(String account_number,String startday,String endday){
		ArrayList<Transfer_logDTO> res = new ArrayList<Transfer_logDTO>();

		sql = "select * from transfer_log where (to_account_number =  ? or account_number = ? )and ('"+startday+" 00:00:00' < register_date and register_date < '"+endday+" 23:59:59')" ;
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, account_number);
			pstmt.setString(2, account_number);

			rs = pstmt.executeQuery();

			Transfer_log(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}

	/////////////////////////////////////









	public ArrayList<Transfer_logDTO> selectIOAN(String account_number){
		ArrayList<Transfer_logDTO> res = new ArrayList<Transfer_logDTO>();

		sql = "select * from transfer_log where to_account_number = ? or account_number = ? ";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, account_number);
			pstmt.setString(2, account_number);

			rs = pstmt.executeQuery();

			Transfer_log(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}





	////////////////////////
	public void insert(Transfer_logDTO dto){
		sql = 	"insert into transfer_log (" +
				"account_number,feetype,target,to_account_number,received,"+ 
				"sum,fee,cms,memo,to_memo,status,register_date) "+ 
				"values ( "+
				"		?	   ,  ? ,	?  ,		?		,	?	  ,"+ 
				" ? , ? , ? , ?  ,  ?	,    ?  , 	now()	  )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getAccount_number());
			pstmt.setString(2, dto.getFeetype());
			pstmt.setString(3, dto.getTarget());
			pstmt.setString(4, dto.getTo_account_number());
			pstmt.setString(5, dto.getReceived());
			pstmt.setLong(6, dto.getSum());
			pstmt.setInt(7, dto.getFee());
			pstmt.setString(8, dto.getCms());
			pstmt.setString(9, dto.getMemo());
			pstmt.setString(10, dto.getTo_memo());
			pstmt.setString(11, dto.getStatus());

			rs = pstmt.executeQuery();
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	public void insert(String account_number, String feetype, String target, String to_account_number
			, String receive, long sum, int fee, String register_date){
		sql = 	"insert into transfer_log (" +
				"account_number,feetype,target,to_account_number,received,"+ 
				"sum,fee,cms,memo,to_memo,status,register_date) "+ 
				"values ( "+
				"		?	   ,  ? ,	?  ,		?		,	?	  ,"+ 
				" ? , ? , '' , ''  ,  ''	,    '활성'  , 	?	  )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, account_number);
			pstmt.setString(2, feetype);
			pstmt.setString(3, target);
			pstmt.setString(4, to_account_number);
			pstmt.setString(5, receive);
			pstmt.setLong(6, sum);
			pstmt.setInt(7, fee);
			pstmt.setString(8, register_date);

			rs = pstmt.executeQuery();
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
