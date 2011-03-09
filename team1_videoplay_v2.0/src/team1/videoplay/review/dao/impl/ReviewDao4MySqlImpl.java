package team1.videoplay.review.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import team1.videoplay.review.dao.ReviewDao;
import team1.videoplay.review.model.Review;
import team1.videoplay.utils.DBUtils;



public class ReviewDao4MySqlImpl implements ReviewDao{

	public int addReview(Review review) {
		int number = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into t_review values(null,?,?,now(),?,?)";
		try {
			conn = DBUtils.getConn();
			conn.setAutoCommit(false);
			pstmt = DBUtils.getPstmt(conn, sql);
			pstmt.setInt(1, review.getUser_id());
			pstmt.setInt(2, review.getVideo_id());
			pstmt.setString(3, review.getReview_content());
			pstmt.setInt(4, review.getReview_score());
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

	public int deleteReview(int reviewId) {
		int number = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from t_review where review_id="+reviewId;
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

	public ArrayList<Review> getAllReview(int page, int pageSize) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from t_review limit "+(page-1)*pageSize+","+pageSize*page; 
		ArrayList<Review> list = new ArrayList<Review>();
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			//id name content 
			while(rs.next()){
				Review review = new Review();
				review.setReview_id(rs.getInt(1));
				review.setUser_id(rs.getInt(2));
				review.setVideo_id(rs.getInt(3));
				review.setReview_time(rs.getTimestamp(4));
				review.setReview_content(rs.getString(5));
				review.setReview_score(rs.getInt(6));
				list.add(review);
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

	public Review getReview(int reviewId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Review review = null;
		String sql = "select * from t_review where review_id="+reviewId;
		
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			//id name content 
			while(rs.next()){
				review = new Review();
				review.setReview_id(rs.getInt(1));
				review.setUser_id(rs.getInt(2));
				review.setVideo_id(rs.getInt(3));
				review.setReview_time(rs.getTimestamp(4));
				review.setReview_content(rs.getString(5));
				review.setReview_score(rs.getInt(6));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
			DBUtils.close(conn);
		}
		return review;

	}


	public int getReviewCountByVideoID(int videoID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from t_review where video_id="+videoID;
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

	public ArrayList<Review> getReviewByVideoID(int videoID,int page,int pageSize) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from t_review where video_id=? limit "+(page-1)*pageSize+","+pageSize*page; 
		ArrayList<Review> list = new ArrayList<Review>();
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			pstmt.setInt(1, videoID);
			rs = pstmt.executeQuery();
			//id name content 
			while(rs.next()){
				Review review = new Review();
				review.setReview_id(rs.getInt(1));
				review.setUser_id(rs.getInt(2));
				review.setVideo_id(rs.getInt(3));
				review.setReview_time(rs.getTimestamp(4));
				review.setReview_content(rs.getString(5));
				review.setReview_score(rs.getInt(6));
				list.add(review);
			}
			
		} catch (SQLException e) {
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
	public ArrayList<Review> getAllReviewByVideoID(int videoID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from t_review where video_id=? "; 
		ArrayList<Review> list = new ArrayList<Review>();
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			pstmt.setInt(1, videoID);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			//id name content 
			while(rs.next()){
				Review review = new Review();
				review.setReview_id(rs.getInt(1));
				review.setUser_id(rs.getInt(2));
				review.setVideo_id(rs.getInt(3));
				review.setReview_time(rs.getTimestamp(4));
				review.setReview_content(rs.getString(5));
				review.setReview_score(rs.getInt(6));
				list.add(review);
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
	public ArrayList<Review> getAllReviewByUserID(int userID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from t_review where user_id=? "; 
		ArrayList<Review> list = new ArrayList<Review>();
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			pstmt.setInt(1, userID);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			//id name content 
			while(rs.next()){
				Review review = new Review();
				review.setReview_id(rs.getInt(1));
				review.setUser_id(rs.getInt(2));
				review.setVideo_id(rs.getInt(3));
				review.setReview_time(rs.getTimestamp(4));
				review.setReview_content(rs.getString(5));
				review.setReview_score(rs.getInt(6));
				list.add(review);
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

	public ArrayList<Review> getReviewByVideoID(int videoID) {
		// TODO Auto-generated method stub
		return null;
	}
}
