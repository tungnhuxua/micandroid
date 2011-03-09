package team1.videoplay.video.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import team1.videoplay.utils.DBUtils;
import team1.videoplay.video.dao.VideoDao;
import team1.videoplay.video.model.Video;

public class VideoDao4MySqlImpl implements VideoDao {
	/*
	 * 添加视频
	 */
	public int addVideo(Video video) {
		int result = -1;
		Connection conn = DBUtils.getConn();
		String sql = "insert into t_video(video_id,type_id,user_id,video_title,video_desc,video_uploadtime,video_url,video_size,video_keywords,video_checkstate, video_money,video_picture,video_requirdMoney,video_point,video_playcount) values(null,?,?,?,?,now(),?,?,?,?,0,?,?,0,0)";
		PreparedStatement pstmt = DBUtils.getPstmt(conn, sql);

		try {
			pstmt.setInt(1, video.getType_id());
			pstmt.setInt(2, video.getUser_id());
			pstmt.setString(3, video.getVideo_title());
			pstmt.setString(4, video.getVideo_desc());
			pstmt.setString(5, video.getVideo_url());
			pstmt.setInt(6, video.getVideo_size());
			pstmt.setString(7, video.getVideo_keywords());
			pstmt.setInt(8, video.getVideo_checkstate());
			pstmt.setString(9, video.getVideo_pictureUrl());
			pstmt.setFloat(10, video.getVideo_requirdMoney());
			result = DBUtils.getExecuteUpdate(pstmt);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.close(conn);
			DBUtils.close(pstmt);
		}
		return result;
	}

	/*
	 * 修改视频where limit (page-1)* pagesize, page* pagesize
	 */
	public int updateVideo(Video video) {
		int result = -1;
		Connection conn = DBUtils.getConn();
		String sql = "update t_video set type_id=?,user_id=?,video_title=?,video_desc=?,video_url=?,video_size=?,video_keywords=?,video_checkstate =?,video_picture=?,video_requirdMoney=?,video_point=?,video_playcount=? where video_id=?";
		PreparedStatement pstmt = DBUtils.getPstmt(conn, sql);

		try {
			pstmt.setInt(1, video.getType_id());
			pstmt.setInt(2, video.getUser_id());
			pstmt.setString(3, video.getVideo_title());
			pstmt.setString(4, video.getVideo_desc());
			pstmt.setString(5, video.getVideo_url());
			pstmt.setInt(6, video.getVideo_size());
			pstmt.setString(7, video.getVideo_keywords());
			pstmt.setInt(8, video.getVideo_checkstate());
			pstmt.setString(9, video.getVideo_pictureUrl());
			pstmt.setFloat(10, video.getVideo_requirdMoney());
			pstmt.setLong(11, video.getVideo_point());
			pstmt.setInt(12, video.getVideo_playcount());
			pstmt.setInt(13, video.getVideo_id());
			result = DBUtils.getExecuteUpdate(pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.close(conn);
			DBUtils.close(pstmt);
		}
		return result;
	}

	/*
	 * 删除视频
	 */
	public void deleteVideo(Video video) {
		Connection conn = DBUtils.getConn();
		String sql = "delete from t_video where video_id=?";
		PreparedStatement pstmt = DBUtils.getPstmt(conn, sql);
		try {
			pstmt.setInt(1, video.getVideo_id());
			DBUtils.getExecuteUpdate(pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.close(conn);
			DBUtils.close(pstmt);
		}
	}

	/*
	 * 根据ID号查询视频
	 */
	public Video findVideoID(int id) {
		Video video = null;
		Connection conn = DBUtils.getConn();
		PreparedStatement pstmt = DBUtils.getPstmt(conn,
				"select * from t_video where video_id=?");
		try {
			pstmt.setInt(1, id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = DBUtils.getRs(pstmt);
		try {
			if (rs.next()) {
				video = new Video();
				video.setVideo_id(id);
				video.setType_id(rs.getInt(2));
				video.setUser_id(rs.getInt(3));
				video.setVideo_title(rs.getString(4));
				video.setVideo_desc(rs.getString(5));
				video.setVideo_point(rs.getLong(6));
				video.setVideo_playcount(rs.getInt(7));
				video.setVideo_uploadtime(rs.getDate(8));
				video.setVideo_lastplaytime(rs.getDate(9));
				video.setVideo_url(rs.getString(10));
				video.setVideo_size(rs.getInt(11));
				video.setVideo_keywords(rs.getString(12));
				video.setVideo_checkstate(rs.getInt(13));
				video.setVideo_money(rs.getFloat(14));
				video.setVideo_pictureUrl(rs.getString(15));
				video.setVideo_requirdMoney(rs.getFloat(16));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			video = null;
		} finally {
			DBUtils.close(conn);
			DBUtils.close(rs);
			DBUtils.close(pstmt);
		}
		return video;
	}

	/*
	 * 分页查询
	 */
	public List<Video> searchVideoPage(int page, int pageSize) {
		List<Video> list = new ArrayList<Video>();
		Video video = null;
		Connection conn = DBUtils.getConn();
		String sql = "select * from t_video limit " + (page - 1) * pageSize
				+ "," + page * pageSize;
		PreparedStatement pstmt = DBUtils.getPstmt(conn, sql);
		ResultSet rs = DBUtils.getRs(pstmt);
		try {
			while (rs.next()) {
				video = new Video();
				video.setVideo_id(rs.getInt(1));
				video.setType_id(rs.getInt(2));
				video.setUser_id(rs.getInt(3));
				video.setVideo_title(rs.getString(4));
				video.setVideo_desc(rs.getString(5));
				video.setVideo_point(rs.getLong(6));
				video.setVideo_playcount(rs.getInt(7));
				video.setVideo_uploadtime(rs.getDate(8));
				video.setVideo_lastplaytime(rs.getDate(9));
				video.setVideo_url(rs.getString(10));
				video.setVideo_size(rs.getInt(11));
				video.setVideo_keywords(rs.getString(12));
				video.setVideo_checkstate(rs.getInt(13));
				video.setVideo_money(rs.getFloat(14));
				video.setVideo_pictureUrl(rs.getString(15));
				video.setVideo_requirdMoney(rs.getFloat(16));
				list.add(video);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			video = null;
		} finally {
			DBUtils.close(conn);
			DBUtils.close(pstmt);
			DBUtils.close(rs);
		}
		return list;
	}

	public List<Video> searchVideoPageByKeyword(int page, int pageSize,
			String keyword) {
		List<Video> list = new ArrayList<Video>();
		Video video = null;
		Connection conn = DBUtils.getConn();
		String sql = "select * from t_video where video_title like '%"
				+ keyword + "%'limit " + (page - 1) * pageSize + "," + page
				* pageSize;
		PreparedStatement pstmt = DBUtils.getPstmt(conn, sql);
		ResultSet rs = DBUtils.getRs(pstmt);
		try {
			while (rs.next()) {
				video = new Video();
				video.setVideo_id(rs.getInt(1));
				video.setType_id(rs.getInt(2));
				video.setUser_id(rs.getInt(3));
				video.setVideo_title(rs.getString(4));
				video.setVideo_desc(rs.getString(5));
				video.setVideo_point(rs.getLong(6));
				video.setVideo_playcount(rs.getInt(7));
				video.setVideo_uploadtime(rs.getDate(8));
				video.setVideo_lastplaytime(rs.getDate(9));
				video.setVideo_url(rs.getString(10));
				video.setVideo_size(rs.getInt(11));
				video.setVideo_keywords(rs.getString(12));
				video.setVideo_checkstate(rs.getInt(13));
				video.setVideo_money(rs.getFloat(14));
				video.setVideo_pictureUrl(rs.getString(15));
				video.setVideo_requirdMoney(rs.getFloat(16));
				list.add(video);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			video = null;
		} finally {
			DBUtils.close(conn);
			DBUtils.close(pstmt);
			DBUtils.close(rs);
		}
		return list;
	}

	public int getVideoCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from t_video";
		int count = -1;
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			// id name content
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
			DBUtils.close(conn);
		}
		return count;
	}

	public int getVideoCountByKeyword(String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from t_video where video_title like '%"
				+ keyword + "%'";
		int count = -1;
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			// id name content
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
			DBUtils.close(conn);
		}
		return count;
	}

	public List<Video> searchCheckVideoPage(int page, int pageSize) {
		List<Video> list = new ArrayList<Video>();
		Video video = null;
		Connection conn = DBUtils.getConn();
		String sql = "select * from t_video where video_checkstate=0 limit "
				+ (page - 1) * pageSize + "," + page * pageSize;
		PreparedStatement pstmt = DBUtils.getPstmt(conn, sql);
		ResultSet rs = DBUtils.getRs(pstmt);
		try {
			while (rs.next()) {
				video = new Video();
				video.setVideo_id(rs.getInt(1));
				video.setType_id(rs.getInt(2));
				video.setUser_id(rs.getInt(3));
				video.setVideo_title(rs.getString(4));
				video.setVideo_desc(rs.getString(5));
				video.setVideo_point(rs.getLong(6));
				video.setVideo_playcount(rs.getInt(7));
				video.setVideo_uploadtime(rs.getDate(8));
				video.setVideo_lastplaytime(rs.getDate(9));
				video.setVideo_url(rs.getString(10));
				video.setVideo_size(rs.getInt(11));
				video.setVideo_keywords(rs.getString(12));
				video.setVideo_checkstate(rs.getInt(13));
				video.setVideo_money(rs.getFloat(14));
				video.setVideo_pictureUrl(rs.getString(15));
				video.setVideo_requirdMoney(rs.getFloat(16));
				list.add(video);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			video = null;
		} finally {
			DBUtils.close(conn);
			DBUtils.close(pstmt);
			DBUtils.close(rs);
		}
		return list;
	}

	public int getCheckVideoCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from t_video where video_checkstate=0";
		int count = -1;
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			// id name content
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
			DBUtils.close(conn);
		}
		return count;
	}

	public List<Video> searchCheckVideoPageByKeyword(int page, int pageSize,
			String keyword) {
		List<Video> list = new ArrayList<Video>();
		Video video = null;
		Connection conn = DBUtils.getConn();
		String sql = "select * from t_video where video_checkstate=0 and video_title like '%"
				+ keyword
				+ "%'limit "
				+ (page - 1)
				* pageSize
				+ ","
				+ page
				* pageSize;
		PreparedStatement pstmt = DBUtils.getPstmt(conn, sql);
		ResultSet rs = DBUtils.getRs(pstmt);
		try {
			while (rs.next()) {
				video = new Video();
				video.setVideo_id(rs.getInt(1));
				video.setType_id(rs.getInt(2));
				video.setUser_id(rs.getInt(3));
				video.setVideo_title(rs.getString(4));
				video.setVideo_desc(rs.getString(5));
				video.setVideo_point(rs.getLong(6));
				video.setVideo_playcount(rs.getInt(7));
				video.setVideo_uploadtime(rs.getDate(8));
				video.setVideo_lastplaytime(rs.getDate(9));
				video.setVideo_url(rs.getString(10));
				video.setVideo_size(rs.getInt(11));
				video.setVideo_keywords(rs.getString(12));
				video.setVideo_checkstate(rs.getInt(13));
				video.setVideo_money(rs.getFloat(14));
				video.setVideo_pictureUrl(rs.getString(15));
				video.setVideo_requirdMoney(rs.getFloat(16));
				list.add(video);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			video = null;
		} finally {
			DBUtils.close(conn);
			DBUtils.close(pstmt);
			DBUtils.close(rs);
		}
		return list;
	}

	public int getCheckVideoCountByKeyword(String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from t_video where video_checkstate=0 and video_title like '%"
				+ keyword + "%'";
		int count = -1;
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			// id name content
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
			DBUtils.close(conn);
		}
		return count;
	}

	public ArrayList<Video> getVideoOrderByPlaycount() {
		ArrayList<Video> list = new ArrayList<Video>();
		Connection conn = DBUtils.getConn();
		String sql = "select * from t_video where video_checkstate=1 order by video_playcount desc";
		PreparedStatement pstmt = DBUtils.getPstmt(conn, sql);
		ResultSet rs = DBUtils.getRs(pstmt);
		try {
			while (rs.next()) {
				Video video = new Video();
				video.setVideo_id(rs.getInt(1));
				video.setType_id(rs.getInt(2));
				video.setUser_id(rs.getInt(3));
				video.setVideo_title(rs.getString(4));
				video.setVideo_desc(rs.getString(5));
				video.setVideo_point(rs.getLong(6));
				video.setVideo_playcount(rs.getInt(7));
				video.setVideo_uploadtime(rs.getDate(8));
				video.setVideo_lastplaytime(rs.getDate(9));
				video.setVideo_url(rs.getString(10));
				video.setVideo_size(rs.getInt(11));
				video.setVideo_keywords(rs.getString(12));
				video.setVideo_checkstate(rs.getInt(13));
				video.setVideo_money(rs.getFloat(14));
				video.setVideo_pictureUrl(rs.getString(15));
				video.setVideo_requirdMoney(rs.getFloat(16));
				list.add(video);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			list = null;
		} finally {
			DBUtils.close(conn);
			DBUtils.close(pstmt);
			DBUtils.close(rs);
		}
		return list;
	}

	public ArrayList<Video> getVideoOrderByPoint() {
		ArrayList<Video> list = new ArrayList<Video>();
		Connection conn = DBUtils.getConn();
		String sql = "select * from t_video where video_checkstate=1 order by video_point desc";
		PreparedStatement pstmt = DBUtils.getPstmt(conn, sql);
		ResultSet rs = DBUtils.getRs(pstmt);
		try {
			while (rs.next()) {
				Video video = new Video();
				video.setVideo_id(rs.getInt(1));
				video.setType_id(rs.getInt(2));
				video.setUser_id(rs.getInt(3));
				video.setVideo_title(rs.getString(4));
				video.setVideo_desc(rs.getString(5));
				video.setVideo_point(rs.getLong(6));
				video.setVideo_playcount(rs.getInt(7));
				video.setVideo_uploadtime(rs.getDate(8));
				video.setVideo_lastplaytime(rs.getDate(9));
				video.setVideo_url(rs.getString(10));
				video.setVideo_size(rs.getInt(11));
				video.setVideo_keywords(rs.getString(12));
				video.setVideo_checkstate(rs.getInt(13));
				video.setVideo_money(rs.getFloat(14));
				video.setVideo_pictureUrl(rs.getString(15));
				video.setVideo_requirdMoney(rs.getFloat(16));
				list.add(video);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			list = null;
		} finally {
			DBUtils.close(conn);
			DBUtils.close(pstmt);
			DBUtils.close(rs);
		}
		return list;
	}

	public ArrayList<Video> getVideoOrderByTime() {
		ArrayList<Video> list = new ArrayList<Video>();
		Connection conn = DBUtils.getConn();
		String sql = "select * from t_video where video_checkstate=1 order by video_uploadtime desc";
		PreparedStatement pstmt = DBUtils.getPstmt(conn, sql);
		ResultSet rs = DBUtils.getRs(pstmt);
		try {
			while (rs.next()) {
				Video video = new Video();
				video.setVideo_id(rs.getInt(1));
				video.setType_id(rs.getInt(2));
				video.setUser_id(rs.getInt(3));
				video.setVideo_title(rs.getString(4));
				video.setVideo_desc(rs.getString(5));
				video.setVideo_point(rs.getLong(6));
				video.setVideo_playcount(rs.getInt(7));
				video.setVideo_uploadtime(rs.getDate(8));
				video.setVideo_lastplaytime(rs.getDate(9));
				video.setVideo_url(rs.getString(10));
				video.setVideo_size(rs.getInt(11));
				video.setVideo_keywords(rs.getString(12));
				video.setVideo_checkstate(rs.getInt(13));
				video.setVideo_money(rs.getFloat(14));
				video.setVideo_pictureUrl(rs.getString(15));
				video.setVideo_requirdMoney(rs.getFloat(16));
				list.add(video);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			list = null;
		} finally {
			DBUtils.close(conn);
			DBUtils.close(pstmt);
			DBUtils.close(rs);
		}
		return list;
	}

	// 亮
	public ArrayList<Video> getVideoByVideoTypeID(int videoTypeID, int page,
			int pageSize) {
		ArrayList<Video> list = new ArrayList<Video>();
		Connection conn = DBUtils.getConn();
		String sql = null;
		if (page != 0)
			sql = "select * from t_video where type_id=? limit " + (page - 1)
					* pageSize + "," + page * pageSize;
		else
			sql = "select * from t_video where type_id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DBUtils.getPstmt(conn, sql);
			pstmt.setInt(1, videoTypeID);
			rs = DBUtils.getRs(pstmt);
			while (rs.next()) {
				Video video = new Video();
				video.setVideo_id(rs.getInt(1));
				video.setType_id(rs.getInt(2));
				video.setUser_id(rs.getInt(3));
				video.setVideo_title(rs.getString(4));
				video.setVideo_desc(rs.getString(5));
				video.setVideo_point(rs.getLong(6));
				video.setVideo_playcount(rs.getInt(7));
				video.setVideo_uploadtime(rs.getDate(8));
				video.setVideo_lastplaytime(rs.getDate(9));
				video.setVideo_url(rs.getString(10));
				video.setVideo_size(rs.getInt(11));
				video.setVideo_keywords(rs.getString(12));
				video.setVideo_checkstate(rs.getInt(13));
				video.setVideo_money(rs.getFloat(14));
				video.setVideo_pictureUrl(rs.getString(15));
				video.setVideo_requirdMoney(rs.getFloat(16));
				list.add(video);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			list = null;
		} finally {
			DBUtils.close(conn);
			DBUtils.close(pstmt);
			DBUtils.close(rs);
		}
		return list;
	}

	public int getVideoCountByTypeID(int typeid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from t_video where type_id=" + typeid;
		int count = -1;
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			// id name content
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
			DBUtils.close(conn);
		}
		return count;
	}

	// public ArrayList<Video> getTodayVideo() {
	// ArrayList<Video> list=new ArrayList<Video>();
	// Connection conn=DBUtils.getConn();
	// Calendar calendar = Calendar.getInstance();
	// String
	// sql="select * from t_video where video_uploadtime=? order by video_playcount desc";
	// PreparedStatement pstmt=null;
	// ResultSet rs=null;
	// try {
	// pstmt = DBUtils.getPstmt(conn, sql);
	// pstmt.setDate(1, new Date());
	// rs = DBUtils.getRs(pstmt);
	// while(rs.next()){
	// Video video=new Video();
	// video.setVideo_id(rs.getInt(1));
	// video.setType_id(rs.getInt(2));
	// video.setUser_id(rs.getInt(3));
	// video.setVideo_title(rs.getString(4));
	// video.setVideo_desc(rs.getString(5));
	// video.setVideo_point(rs.getLong(6));
	// video.setVideo_playcount(rs.getInt(7));
	// video.setVideo_uploadtime(rs.getDate(8));
	// video.setVideo_lastplaytime(rs.getDate(9));
	// video.setVideo_url(rs.getString(10));
	// video.setVideo_size(rs.getInt(11));
	// video.setVideo_keywords(rs.getString(12));
	// video.setVideo_checkstate(rs.getInt(13));
	// video.setVideo_money(rs.getFloat(14));
	// video.setVideo_pictureUrl(rs.getString(15));
	// list.add(video);
	// }
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// list=null;
	// }finally{
	// DBUtils.close(conn);
	// DBUtils.close(pstmt);
	// DBUtils.close(rs);
	// }
	// return list;
	// }
	// 亮
	public ArrayList<Video> getVideoByUserID(int userID, int page, int pageSize) {
		ArrayList<Video> list = new ArrayList<Video>();
		Connection conn = DBUtils.getConn();
		String sql = "select * from t_video where user_id=? limit "
				+ (page - 1) * pageSize + "," + page * pageSize;
		PreparedStatement pstmt = DBUtils.getPstmt(conn, sql);
		try {
			pstmt.setInt(1, userID);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ResultSet rs = DBUtils.getRs(pstmt);
		try {
			while (rs.next()) {
				Video video = new Video();
				video.setVideo_id(rs.getInt(1));
				video.setType_id(rs.getInt(2));
				video.setUser_id(rs.getInt(3));
				video.setVideo_title(rs.getString(4));
				video.setVideo_desc(rs.getString(5));
				video.setVideo_point(rs.getLong(6));
				video.setVideo_playcount(rs.getInt(7));
				video.setVideo_uploadtime(rs.getDate(8));
				video.setVideo_lastplaytime(rs.getDate(9));
				video.setVideo_url(rs.getString(10));
				video.setVideo_size(rs.getInt(11));
				video.setVideo_keywords(rs.getString(12));
				video.setVideo_checkstate(rs.getInt(13));
				video.setVideo_money(rs.getFloat(14));
				video.setVideo_pictureUrl(rs.getString(15));
				video.setVideo_requirdMoney(rs.getFloat(16));
				list.add(video);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			list = null;
		} finally {
			DBUtils.close(conn);
			DBUtils.close(pstmt);
			DBUtils.close(rs);
		}
		return list;
	}

	public int getUserIDByVideoID(int videoID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select user_id from t_video where video_id=" + videoID;
		int userID = -1;
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			// id name content
			while (rs.next()) {
				userID = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
			DBUtils.close(conn);
		}
		return userID;
	}

	public int getVideoCountByUserID(int userID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from t_video where user_id=" + userID;
		int count = -1;
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			// id name content
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
			DBUtils.close(conn);
		}
		return count;
	}

	public ArrayList<Video> getVideoByUserID1(int userID) {
		ArrayList<Video> list = new ArrayList<Video>();
		Connection conn = DBUtils.getConn();
		String sql = "select * from t_video where user_id=?";
		PreparedStatement pstmt = DBUtils.getPstmt(conn, sql);
		try {
			pstmt.setInt(1, userID);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ResultSet rs = DBUtils.getRs(pstmt);
		try {
			while (rs.next()) {
				Video video = new Video();
				video.setVideo_id(rs.getInt(1));
				video.setType_id(rs.getInt(2));
				video.setUser_id(rs.getInt(3));
				video.setVideo_title(rs.getString(4));
				video.setVideo_desc(rs.getString(5));
				video.setVideo_point(rs.getLong(6));
				video.setVideo_playcount(rs.getInt(7));
				video.setVideo_uploadtime(rs.getDate(8));
				video.setVideo_lastplaytime(rs.getDate(9));
				video.setVideo_url(rs.getString(10));
				video.setVideo_size(rs.getInt(11));
				video.setVideo_keywords(rs.getString(12));
				video.setVideo_checkstate(rs.getInt(13));
				video.setVideo_money(rs.getFloat(14));
				video.setVideo_pictureUrl(rs.getString(15));
				video.setVideo_requirdMoney(rs.getFloat(16));
				list.add(video);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			list = null;
		} finally {
			DBUtils.close(conn);
			DBUtils.close(pstmt);
			DBUtils.close(rs);
		}
		return list;
	}

	public ArrayList<Video> getSearchVideo(int page, int pageSize,
			String[] keywords) {
		ArrayList<Video> list = new ArrayList<Video>();
		Connection conn = DBUtils.getConn();
		
		StringBuffer str = new StringBuffer("select * from t_video where");
		for (int i = 0; i < keywords.length; i++) {
			str.append(" video_keywords like '" + "%" + keywords[i] + "%"
					+ "' or");
		}
		str.deleteCharAt(str.lastIndexOf("o"));
		str.deleteCharAt(str.lastIndexOf("r"));
		//str.append("limit" + (page - 1) * pageSize + "," + page * pageSize);
		String sql = str.toString();
		
		PreparedStatement pstmt = DBUtils.getPstmt(conn, sql);
		ResultSet rs = DBUtils.getRs(pstmt);
		try {
			while (rs.next()) {
				Video video = new Video();
				video.setVideo_id(rs.getInt(1));
				video.setType_id(rs.getInt(2));
				video.setUser_id(rs.getInt(3));
				video.setVideo_title(rs.getString(4));
				video.setVideo_desc(rs.getString(5));
				video.setVideo_point(rs.getLong(6));
				video.setVideo_playcount(rs.getInt(7));
				video.setVideo_uploadtime(rs.getDate(8));
				video.setVideo_lastplaytime(rs.getDate(9));
				video.setVideo_url(rs.getString(10));
				video.setVideo_size(rs.getInt(11));
				video.setVideo_keywords(rs.getString(12));
				video.setVideo_checkstate(rs.getInt(13));
				video.setVideo_money(rs.getFloat(14));
				video.setVideo_pictureUrl(rs.getString(15));
				video.setVideo_requirdMoney(rs.getFloat(16));
				list.add(video);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			list = null;
		} finally {
			DBUtils.close(conn);
			DBUtils.close(pstmt);
			DBUtils.close(rs);
		}
		return list;
	}

	public int getVideoCountByKeyWords(String[]keyWords) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer str = new StringBuffer("select count(*) from t_video where ");
		for (int i = 0; i < keyWords.length; i++) {
			str.append("video_keywords like '" + "%" + keyWords[i] + "%"
					+ "' or ");
		}
		str.deleteCharAt(str.lastIndexOf("o"));
		str.deleteCharAt(str.lastIndexOf("r"));
		String sql = str.toString();
		
		int count = -1;
		try {
			conn = DBUtils.getConn();
			pstmt = DBUtils.getPstmt(conn, sql);
			rs = DBUtils.getRs(pstmt);
			rs = pstmt.executeQuery();
			// id name content
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.close(rs);
			DBUtils.close(pstmt);
			DBUtils.close(conn);
		}
		return count;
	}
}
