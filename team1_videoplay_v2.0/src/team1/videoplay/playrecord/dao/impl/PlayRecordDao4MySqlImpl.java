package team1.videoplay.playrecord.dao.impl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import team1.videoplay.playrecord.dao.PlayRecordDao;
import team1.videoplay.playrecord.model.PlayRecord;
import team1.videoplay.utils.DBUtils;




public class PlayRecordDao4MySqlImpl implements PlayRecordDao {

	public int addPlayRecord(PlayRecord playRecord) {
		int number = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into t_playRecord values(null,?,?,now())";
		try {
			conn = DBUtils.getConn();
			conn.setAutoCommit(false);
			pstmt = DBUtils.getPstmt(conn, sql);
			pstmt.setInt(1, playRecord.getVideo_id());
			pstmt.setInt(2, playRecord.getUser_id());
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

	public int deletePlayRecord(int PlayRecordID) {
		int number = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from t_playRecord where play_id=" + PlayRecordID;
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

	public ArrayList<PlayRecord> getAllPlayRecord(int page, int pageSize) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from t_playRecord limit "+(page-1)*pageSize+","+pageSize*page; 
		ArrayList<PlayRecord> list = new ArrayList<PlayRecord>();
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			rs = pstmt.executeQuery();
			//id name content 
			while(rs.next()){
				PlayRecord playRecord = new PlayRecord();
				playRecord.setPlay_id(rs.getInt(1));
				playRecord.setVideo_id(rs.getInt(2));
				playRecord.setUser_id(rs.getInt(3));
				playRecord.setPlay_time(rs.getDate(4));
				list.add(playRecord);
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

	public PlayRecord getPlayRecord(int PlayRecordID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from t_playRecord where play_id="+PlayRecordID;
		PlayRecord playrecord =null;
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			rs = pstmt.executeQuery();
			
			//id name content 
			while(rs.next()){
				playrecord = new PlayRecord();
				playrecord.setPlay_id(rs.getInt(1));
				playrecord.setVideo_id(rs.getInt(2));
				playrecord.setUser_id(rs.getInt(3));
				playrecord.setPlay_time(rs.getDate(4));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
			DBUtils.close(conn);
		}
		return playrecord;
	}

	public int getPlayRecordCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from t_playrecord";
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

	public ArrayList<PlayRecord> getPlayRecordByUserID(int userID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from t_playRecord where user_id=?";
		ArrayList<PlayRecord> list  = new ArrayList<PlayRecord>();
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			pstmt.setInt(1, userID);
			rs = pstmt.executeQuery();
			
			//id name content 
			while(rs.next()){
				PlayRecord playrecord = new PlayRecord();
				playrecord.setPlay_id(rs.getInt(1));
				playrecord.setVideo_id(rs.getInt(2));
				playrecord.setUser_id(rs.getInt(3));
				playrecord.setPlay_time(rs.getDate(4));
				list.add(playrecord);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			list = null;
			e.printStackTrace();
		}finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
			DBUtils.close(conn);
		}
		return list;
	}
	
	//¡¡
	public ArrayList<PlayRecord> getAllPlayRecordByVideoID(int videoID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from t_playRecord where video_id=?"; 
		ArrayList<PlayRecord> list = new ArrayList<PlayRecord>();
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			pstmt.setInt(1, videoID);
			rs = pstmt.executeQuery();
			//id name content 
			while(rs.next()){
				PlayRecord playRecord = new PlayRecord();
				playRecord.setPlay_id(rs.getInt(1));
				playRecord.setVideo_id(rs.getInt(2));
				playRecord.setUser_id(rs.getInt(3));
				playRecord.setPlay_time(rs.getDate(4));
				list.add(playRecord);
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
	public ArrayList<PlayRecord> getAllPlayRecordByUserID(int userID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from t_playRecord where user_id=?"; 
		ArrayList<PlayRecord> list = new ArrayList<PlayRecord>();
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			pstmt.setInt(1, userID);
			rs = pstmt.executeQuery();
			//id name content 
			while(rs.next()){
				PlayRecord playRecord = new PlayRecord();
				playRecord.setPlay_id(rs.getInt(1));
				playRecord.setVideo_id(rs.getInt(2));
				playRecord.setUser_id(rs.getInt(3));
				playRecord.setPlay_time(rs.getDate(4));
				list.add(playRecord);
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
