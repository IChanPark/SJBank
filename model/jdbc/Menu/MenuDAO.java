package jdbc.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import control.Data_Source;
import jdbc.Menu.MenuDTO;

public class MenuDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	private String sql;
	
	private MenuDAO() {
		ds = Data_Source.getInstance().getDs();
	}
	
	private static class Holder {
        public static final MenuDAO DAO = new MenuDAO();
    }
	
	public static MenuDAO getInstance() {
        return Holder.DAO;
    }

	private MenuDTO Menu(ResultSet rs, MenuDTO dto) {
		try {
			if(rs.next()) {
				dto = new MenuDTO();
				dto.setType(rs.getString("type"));
				dto.setName(rs.getString("name"));
				dto.setKor_name(rs.getString("kor_name"));
				dto.setPrnts_name(rs.getString("prnts_name"));
				dto.setStatus(rs.getString("status"));
				dto.setDepth(rs.getInt("depth"));
				dto.setSort(rs.getInt("sort"));
			} 
		} catch (Exception e) {}
		return dto;
	}
	
	private void Menu(ResultSet rs, ArrayList<MenuDTO> res) {
		try {
			while (rs.next()) {
				MenuDTO dto = new MenuDTO();
				dto.setType(rs.getString("type"));
				dto.setName(rs.getString("name"));
				dto.setKor_name(rs.getString("kor_name"));
				dto.setPrnts_name(rs.getString("prnts_name"));
				dto.setStatus(rs.getString("status"));
				dto.setDepth(rs.getInt("depth"));
				dto.setSort(rs.getInt("sort"));
				res.add(dto);
			} 
		} catch (Exception e) {}
	}
	
	public ArrayList<MenuDTO> list(){
		ArrayList<MenuDTO> res = new ArrayList<MenuDTO>();
		
		sql = "select * from menu";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			Menu(rs, res);			
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
		return res;
	}
	
	public MenuDTO selectName(String name){
		MenuDTO dto = null;
		
		sql = 	"select * from menu where name = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			dto = Menu(rs, dto);
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return dto;
	}
	
	public ArrayList<MenuDTO> selectPrnts(String name, int depth){
		ArrayList<MenuDTO> res = new ArrayList<MenuDTO>();
		
		sql = 	"select * from menu where "+
				"prnts_name = ? and depth = ? "+
				"ORDER BY sort asc";
//		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
		
			pstmt.setString(1, name);
			pstmt.setInt(2, depth);
			
			rs = pstmt.executeQuery();
			
			Menu(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	
	public ArrayList<MenuDTO> selectPrnts(String type, String name, int depth){
		ArrayList<MenuDTO> res = new ArrayList<MenuDTO>();
		
		sql = 	"select * from menu where "+
				"type = ? and prnts_name = ? and depth = ? "+
				"ORDER BY sort asc";
//		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, type);
			pstmt.setString(2, name);
			pstmt.setInt(3, depth);
			
			rs = pstmt.executeQuery();
			
			Menu(rs, res);	
		} catch (Exception e) { e.printStackTrace(); 
		} finally { close(); }
		return res;
	}
	
	public void insert(MenuDTO dto){
		sql = 	"insert into menu (" +
				"type, name, kor_name, prnts_name, status, depth, sort) "+
				"values ("+
				"	?,	?  ,	 ?	 ,		?	 ,  'y'	 ,  ?	, 	? )";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getType());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getKor_name());
			pstmt.setString(4, dto.getPrnts_name());
			pstmt.setString(5, dto.getStatus());
			pstmt.setInt(6, dto.getDepth());
			pstmt.setInt(7, dto.getSort());
			
			pstmt.executeUpdate(); 
		} catch (Exception e) { e.printStackTrace();
		} finally { close(); }
	}
	
	public void updateMoney(MenuDTO dto){
		sql = 	"update menu set " +
				"kor_name = ? , prnts_name = ?, status = ?, depth = ?, sort = ? " +
				"where account_number = ?";
		System.out.println(sql);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getKor_name());
			pstmt.setString(2, dto.getPrnts_name());
			pstmt.setString(3, dto.getStatus());
			pstmt.setInt(4, dto.getDepth());
			pstmt.setInt(5, dto.getSort());
			
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
