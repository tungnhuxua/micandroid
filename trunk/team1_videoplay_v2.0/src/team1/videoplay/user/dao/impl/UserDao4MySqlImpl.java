package team1.videoplay.user.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import team1.videoplay.user.dao.UserDao;
import team1.videoplay.user.model.User;
import team1.videoplay.utils.DBUtils;



public class UserDao4MySqlImpl implements UserDao {

	public int addUser(User user) {
		int number = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into t_user values(null,?,?,now(),null,0,?,?,?,0,0,?,?)";
		try {
			conn = DBUtils.getConn();
			conn.setAutoCommit(false);
			pstmt = DBUtils.getPstmt(conn, sql);
			pstmt.setString(1, user.getUser_name());
			pstmt.setString(2, user.getUser_password());
			pstmt.setString(3, user.getUser_account());
			pstmt.setString(4, user.getUser_email());
			pstmt.setString(5, user.getUser_telephone());
			pstmt.setString(6, user.getQuestion());
			pstmt.setString(7, user.getAnswer());
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

	public int deleteUser(int user_id) {
		int number = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from t_user where user_id="+user_id;
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

	public ArrayList<User> getAllUser(int page,int pageSize) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from t_user limit "+(page-1)*pageSize+","+pageSize*page; 
		ArrayList<User> list = new ArrayList<User>();
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			//id name content 
			while(rs.next()){
			User user=new User();
				user.setUser_id(rs.getInt(1));
				user.setUser_name(rs.getString(2));
				user.setUser_password(rs.getString(3));
				user.setUser_registedate(rs.getTimestamp(4));
				user.setUser_lastlogindate(rs.getTimestamp(5));
				user.setUser_point(rs.getLong(6));
				user.setUser_account(rs.getString(7));
				user.setUser_email(rs.getString(8));
				user.setUser_telephone(rs.getString(9));
				user.setUser_money(rs.getFloat(10));
				user.setUserStatus(rs.getInt(11));
				user.setQuestion(rs.getString(12));
				user.setAnswer(rs.getString(13));
				list.add(user);
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

	public User getUser(int user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		String sql = "select * from t_user where user_id=?";
		
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			pstmt.setInt(1, user_id);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			//id name content 
			while(rs.next()){
				user = new User();
				user.setUser_id(user_id);
				user.setUser_name(rs.getString(2));
				user.setUser_password(rs.getString(3));
				user.setUser_registedate(rs.getTimestamp(4));
				user.setUser_lastlogindate(rs.getTimestamp(5));
				user.setUser_point(rs.getLong(6));
				user.setUser_account(rs.getString(7));
				user.setUser_email(rs.getString(8));
				user.setUser_telephone(rs.getString(9));
				user.setUser_money(rs.getFloat(10));
				user.setUserStatus(rs.getInt(11));
				user.setQuestion(rs.getString(12));
				user.setAnswer(rs.getString(13));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
			DBUtils.close(conn);
		}
		return user;
	}

	public int updateUser(User user) {
		int number = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update t_user set user_name=?,user_password=?,user_account =?,user_email=?,user_telephone=?,user_question=?,user_answer=? where user_id=?";
		try {
			conn = DBUtils.getConn();
			conn.setAutoCommit(false);
			pstmt = DBUtils.getPstmt(conn, sql);	
			
			pstmt.setString(1, user.getUser_name());
			pstmt.setString(2, user.getUser_password());
			pstmt.setString(3, user.getUser_account());
			pstmt.setString(4, user.getUser_email());
			pstmt.setString(5, user.getUser_telephone());
			pstmt.setString(6, user.getQuestion());
			pstmt.setString(7, user.getAnswer());
			pstmt.setInt(8, user.getUser_id());
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


	public int updateUserStatus(User user) {
		int number = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update t_user set user_state=? where user_id=?";
		try {
			conn = DBUtils.getConn();
			conn.setAutoCommit(false);
			pstmt = DBUtils.getPstmt(conn, sql);	
			pstmt.setInt(1, user.getUserStatus());
			pstmt.setInt(2, user.getUser_id());
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
	public int getUserCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from t_user";
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

	public User getUserByUserName(String username) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		String sql = "select * from t_user where user_name=?";
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			//id name content 
			while(rs.next()){
				user = new User();
				user.setUser_id(rs.getInt(1));
				user.setUser_name(rs.getString(2));
				user.setUser_password(rs.getString(3));
				user.setUser_registedate(rs.getTimestamp(4));
				user.setUser_lastlogindate(rs.getTimestamp(5));
				user.setUser_point(rs.getLong(6));
				user.setUser_account(rs.getString(7));
				user.setUser_email(rs.getString(8));
				user.setUser_telephone(rs.getString(9));
				user.setUser_money(rs.getFloat(10));
				user.setUserStatus(rs.getInt(11));
				user.setQuestion(rs.getString(12));
				user.setAnswer(rs.getString(13));
			}
		} catch (SQLException e) {
			user = null;
			e.printStackTrace();
		}finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
			DBUtils.close(conn);
		}
		return user;
	}

	public int userMoneyModify(float money,int userId) {
		int number = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update t_user set user_money=? where user_id=?";
		try {
			conn = DBUtils.getConn();
			conn.setAutoCommit(false);
			pstmt = DBUtils.getPstmt(conn, sql);	
			pstmt.setFloat(1, money);
			pstmt.setInt(2, userId);
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

	public int userScoreModify(long userPoint, int userId) {
		int number = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update t_user set user_point=? where user_id=?";
		try {
			conn = DBUtils.getConn();
			conn.setAutoCommit(false);
			pstmt = DBUtils.getPstmt(conn, sql);	
			pstmt.setLong(1, userPoint);
			pstmt.setInt(2, userId);
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

	public User getUserByEmail(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		String sql = "select * from t_user where user_email=?";
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			//id name content 
			while(rs.next()){
				user = new User();
				user.setUser_id(rs.getInt(1));
				user.setUser_name(rs.getString(2));
				user.setUser_password(rs.getString(3));
				user.setUser_registedate(rs.getTimestamp(4));
				user.setUser_lastlogindate(rs.getTimestamp(5));
				user.setUser_point(rs.getLong(6));
				user.setUser_account(rs.getString(7));
				user.setUser_email(rs.getString(8));
				user.setUser_telephone(rs.getString(9));
				user.setUser_money(rs.getFloat(10));
				user.setUserStatus(rs.getInt(11));
				user.setQuestion(rs.getString(12));
				user.setAnswer(rs.getString(13));
			}
		} catch (SQLException e) {
			user = null;
			e.printStackTrace();
		}finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
			DBUtils.close(conn);
		}
		return user;
	}
	
}
