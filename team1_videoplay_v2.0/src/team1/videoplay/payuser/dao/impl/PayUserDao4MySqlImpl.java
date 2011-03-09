package team1.videoplay.payuser.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import team1.videoplay.payuser.dao.PayUserDao;
import team1.videoplay.payuser.model.PayUser;
import team1.videoplay.utils.DBUtils;




public class PayUserDao4MySqlImpl implements PayUserDao {

	public int addPayUser(PayUser payuser) {
		int number = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into t_payuser values(null,?,now(),now(),?,?,?,0)";
		try {
			conn = DBUtils.getConn();
			conn.setAutoCommit(false);
			pstmt = DBUtils.getPstmt(conn, sql);
			pstmt.setInt(1, payuser.getUserId());
			pstmt.setFloat(2, payuser.getPayuserApplymoney());
			pstmt.setString(3, payuser.getPayuserRemark());
			pstmt.setFloat(4, payuser.getPayuserSupplymoney());
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

	public int deletePayUser(int payuserId) {
		int number = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from t_payuser where payuser_id="+payuserId;
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

	public ArrayList<PayUser> getAllPayUser(int page, int pageSize) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from t_payuser limit "+(page-1)*pageSize+","+pageSize*page; 
		ArrayList<PayUser> list = new ArrayList<PayUser>();
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			//id name content 
			while(rs.next()){
				PayUser payuser = new PayUser();
				payuser.setPayuserId(rs.getInt(1));
				payuser.setUserId(rs.getInt(2));
				payuser.setPayuserApplytime(rs.getDate(3));
				payuser.setPayuserSupplytime(rs.getDate(4));
				payuser.setPayuserApplymoney(rs.getFloat(5));
				payuser.setPayuserRemark(rs.getString(6));
				payuser.setPayuserSupplymoney(rs.getFloat(7));
				payuser.setPayuserState(rs.getInt(8));
				list.add(payuser);
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

	public PayUser getPayUser(int payuserId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PayUser payuser = null;
		String sql = "select * from t_payuser where payuser_id="+payuserId;
		
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			//id name content 
			while(rs.next()){
				payuser = new PayUser();
				payuser.setPayuserId(rs.getInt(1));
				payuser.setUserId(rs.getInt(2));
				payuser.setPayuserApplytime(rs.getDate(3));
				payuser.setPayuserSupplytime(rs.getDate(4));
				payuser.setPayuserApplymoney(rs.getFloat(5));
				payuser.setPayuserRemark(rs.getString(6));
				payuser.setPayuserSupplymoney(rs.getFloat(7));
				payuser.setPayuserState(rs.getInt(8));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
			DBUtils.close(conn);
		}
		return payuser;
	}

	public int updatePayUser(float supplyMoney, int payuserID) {
		int number = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update t_payuser set payuser_supplytime=now(),payuser_supplymoney=?,payuser_state=1 where payuser_id=?";
		try {
			conn = DBUtils.getConn();
			conn.setAutoCommit(false);
			pstmt = DBUtils.getPstmt(conn, sql);	
//			pstmt.setTimestamp(1, (Timestamp) payuser.getPayuserSupplytime());
			pstmt.setFloat(1, supplyMoney);
			pstmt.setInt(2, payuserID);
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

	
	public int getPayUserCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from t_payuser";
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
	public ArrayList<PayUser> getAllPayUserByUserID(int userID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from t_payUser where user_id=?"; 
		ArrayList<PayUser> list = new ArrayList<PayUser>();
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			pstmt.setInt(1, userID);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			//id name content 
			while(rs.next()){
				PayUser payuser = new PayUser();
				payuser.setPayuserId(rs.getInt(1));
				payuser.setUserId(rs.getInt(2));
				payuser.setPayuserApplytime(rs.getDate(3));
				payuser.setPayuserSupplytime(rs.getDate(4));
				payuser.setPayuserApplymoney(rs.getFloat(5));
				payuser.setPayuserRemark(rs.getString(6));
				payuser.setPayuserSupplymoney(rs.getFloat(7));
				payuser.setPayuserState(rs.getInt(8));
				list.add(payuser);
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
