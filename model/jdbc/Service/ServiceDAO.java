package jdbc.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;

public class ServiceDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private ServiceDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final ServiceDAO DAO = new ServiceDAO();
    }
	
	public static ServiceDAO getInstance() {
        return Holder.DAO;
    }

	private ServiceDTO Service(ResultSet rs, ServiceDTO dto) {
		try {
			if(rs.next()) {
				dto = new ServiceDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setType(rs.getString("type"));
				dto.setStatus(rs.getString("status"));
				dto.setOption(rs.getString("option"));
				dto.setRegister_date(rs.getDate("register_date"));
			
			}			
		} catch (Exception e) {}
		return dto;
	}
	
	private void Service(ResultSet rs, ArrayList<ServiceDTO> res) {
		try {
			while (rs.next()) {
				ServiceDTO dto = new ServiceDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setType(rs.getString("type"));
				dto.setStatus(rs.getString("status"));
				dto.setOption(rs.getString("option"));
				dto.setRegister_date(rs.getDate("register_date"));
			
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<ServiceDTO> list(){
		ArrayList<ServiceDTO> res = new ArrayList<ServiceDTO>();
		
		sql = "select * from Service";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Service(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public ArrayList<ServiceDTO> selectID(String id){
		ArrayList<ServiceDTO> res = new ArrayList<ServiceDTO>();
		
		sql = "select * from service where id = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			Service(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	
	public ServiceDTO selectType(String type){
		ServiceDTO dto = null;
		
		sql = "select * from service where type = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, type);
			
			rs = pstmt.executeQuery();
			
			dto = Service(rs, dto);
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return dto;
	}
	
	public void insert(ServiceDTO dto){
		sql = 	"insert into service (" +
				"id, name, type, status,option, register_date) "+
				"values "
			  +"(? ,    ? ,	   ?   ,  ?  ,   ?   , 	  now() )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getType());
			pstmt.setString(4, dto.getStatus());
			pstmt.setString(5, dto.getOption());
			
			
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	public void update(ServiceDTO dto){
		sql = 	"update service set " +
				"status = ? register_date = now() " +
				"where type = ? and id = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getStatus());
			pstmt.setString(2, dto.getId() );
			
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	
	public void update(String id, String type,String status){
		sql = 	"update service set " +
				"status = ? , register_date = now() " +
				"where type = ? and id = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, status);
			pstmt.setString(2, type);
			pstmt.setString(3, id);
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	public void update(String id, String type,String status,String option){
		sql = 	"update service set " +
				"status = ? , register_date = now()  , option = ? " +
				"where type = ? and id = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, status);
			pstmt.setString(2, option);
			pstmt.setString(3, type);
			pstmt.setString(4, id);
		
			
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
