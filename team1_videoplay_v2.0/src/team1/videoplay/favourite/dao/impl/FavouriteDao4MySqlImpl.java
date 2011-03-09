package team1.videoplay.favourite.dao.impl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import team1.videoplay.favourite.dao.FavouriteDao;
import team1.videoplay.favourite.model.Favourite;
import team1.videoplay.utils.DBUtils;



public class FavouriteDao4MySqlImpl implements FavouriteDao{

	public int addFavourite(Favourite favourite) {
			int number = -1;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "insert into t_favourite values(null,?,?)";
			try {
				conn = DBUtils.getConn();
				conn.setAutoCommit(false);
				pstmt = DBUtils.getPstmt(conn, sql);
				pstmt.setInt(1, favourite.getUser_id());
				pstmt.setInt(2, favourite.getVideo_id());
				number = pstmt.executeUpdate();
				conn.commit();
			} catch (SQLException e) {
				try {
					conn.rollback();
					conn.setAutoCommit(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}finally {
				DBUtils.close(pstmt);
				DBUtils.close(conn);
			}
			return number;
		}

	public int deleteFavourite(int favourateID) {
		int number = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from t_favourite where favourite_id="+favourateID;
		try {
			conn = DBUtils.getConn();
			conn.setAutoCommit(false);
			pstmt = DBUtils.getPstmt(conn, sql);
			number = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			DBUtils.close(pstmt);
			DBUtils.close(conn);
		}
		return number;
		
	}

	public ArrayList<Favourite> getAllFavourite(int page, int pageSize) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from t_favourite limit "+(page-1)*pageSize+","+pageSize*page; 
		ArrayList<Favourite> list = new ArrayList<Favourite>();
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			//id name content 
			while(rs.next()){
				Favourite favourite = new Favourite();
				favourite.setFavourite_id(rs.getInt(1));
				favourite.setUser_id(rs.getInt(2));
				favourite.setVideo_id(rs.getInt(3));
				list.add(favourite);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
			DBUtils.close(conn);
		}
		return list;
	}
	public int getFavouriteCount(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from t_favourite";
		int count = -1;
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			//id name content 
			while(rs.next()){
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
			DBUtils.close(conn);
		}
		return count;
	}
	//¡¡
	public ArrayList<Favourite> getAllFavouriteByVideoID(int videoID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from t_favourite where video_id=?"; 
		ArrayList<Favourite> list = new ArrayList<Favourite>();
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			pstmt.setInt(1, videoID);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			//id name content 
			while(rs.next()){
				Favourite favourite = new Favourite();
				favourite.setFavourite_id(rs.getInt(1));
				favourite.setUser_id(rs.getInt(2));
				favourite.setVideo_id(rs.getInt(3));
				list.add(favourite);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
			DBUtils.close(conn);
		}
		return list;
	}
	
	//¡¡
	public ArrayList<Favourite> getAllFavouriteByUserID(int userID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from t_favourite where user_id=?"; 
		ArrayList<Favourite> list = new ArrayList<Favourite>();
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			pstmt.setInt(1, userID);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			//id name content 
			while(rs.next()){
				Favourite favourite = new Favourite();
				favourite.setFavourite_id(rs.getInt(1));
				favourite.setUser_id(rs.getInt(2));
				favourite.setVideo_id(rs.getInt(3));
				list.add(favourite);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
			DBUtils.close(conn);
		}
		return list;
	}
}
	

