package team1.videoplay.userPay.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import team1.videoplay.userPay.dao.UserPayDao;
import team1.videoplay.userPay.model.UserPay;
import team1.videoplay.utils.DBUtils;


public class UserPayDao4MySqlImpl implements UserPayDao{

	public int addUserPay(UserPay userpay) {
		int number = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into t_userPay values(null,?,now(),?,?)";
		try {
			conn = DBUtils.getConn();
			conn.setAutoCommit(false);
			pstmt = DBUtils.getPstmt(conn, sql);
			pstmt.setInt(1, userpay.getUser_id());
			pstmt.setInt(2, userpay.getUserpay_type());
			pstmt.setFloat(3, userpay.getUserpay_money());
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

	public int deleteUserPay(int userpayId) {
		int number = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from t_userPay where userpay_id="+userpayId;
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

	public ArrayList<UserPay> getAllUserPay(int page, int pageSize) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from t_userPay limit "+(page-1)*pageSize+","+pageSize*page; 
		ArrayList<UserPay> list = new ArrayList<UserPay>();
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			//id name content 
			while(rs.next()){
				UserPay userpay = new UserPay();
				userpay.setUserpay_id(rs.getInt(1));
				userpay.setUser_id(rs.getInt(2));
				userpay.setUserpay_date(rs.getTimestamp(3));
				userpay.setUserpay_type(rs.getInt(4));
				userpay.setUserpay_money(rs.getFloat(5));
				list.add(userpay);
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

	public UserPay getUserPay(int userpayId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserPay userpay = null;
		String sql = "select * from t_userPay where userpay_id="+userpayId;
		
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			//id name content 
			while(rs.next()){
				userpay = new UserPay();
				userpay.setUserpay_id(rs.getInt(1));
				userpay.setUser_id(rs.getInt(2));
				userpay.setUserpay_date(rs.getTimestamp(3));
				userpay.setUserpay_type(rs.getInt(4));
				userpay.setUserpay_money(rs.getFloat(5));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
			DBUtils.close(conn);
		}
		return userpay;
	}

	public int getUserPayCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from t_userpay";
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
	public ArrayList<UserPay> getAllUserPayByUserID(int userID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from t_userPay where user_id=?"; 
		ArrayList<UserPay> list = new ArrayList<UserPay>();
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			pstmt.setInt(1, userID);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			//id name content 
			while(rs.next()){
				UserPay userpay = new UserPay();
				userpay.setUserpay_id(rs.getInt(1));
				userpay.setUser_id(rs.getInt(2));
				userpay.setUserpay_date(rs.getTimestamp(3));
				userpay.setUserpay_type(rs.getInt(4));
				userpay.setUserpay_money(rs.getFloat(5));
				list.add(userpay);
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
