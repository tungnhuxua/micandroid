package team1.videoplay.category.dao.impl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import team1.videoplay.category.dao.VideoTypeDao;
import team1.videoplay.category.model.VideoType;
import team1.videoplay.utils.DBUtils;
public class CategoryDao4MySqlImpl implements VideoTypeDao{
     /*
      * 添加类别
      */
	public int addVideoType(VideoType type){
		int result=-1;
		Connection conn=DBUtils.getConn();
		String sql="insert into t_videoType(type_id,type_name,type_createtime,type_updatetime,type_desc) values(null,?,now(),now(),?)";
		PreparedStatement pstmt=DBUtils.getPstmt(conn, sql);
		try {
			pstmt.setString(1, type.getType_name());
			pstmt.setString(2, type.getType_desc());
			result=DBUtils.getExecuteUpdate(pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.close(conn);
			DBUtils.close(pstmt);
		}
		return result;
	}
	/*
	 * 修改类别
	 */
	public int updateVideoType(VideoType type){
		int result=-1;
		Connection conn=DBUtils.getConn();
		String sql="update t_videoType set type_name=?,type_updatetime=now(),type_desc=? where type_id=?";
		PreparedStatement pstmt=DBUtils.getPstmt(conn, sql);
		try {
			pstmt.setString(1, type.getType_name());
			pstmt.setString(2, type.getType_desc());
			pstmt.setInt(3, type.getType_id());
			result=DBUtils.getExecuteUpdate(pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.close(conn);
			DBUtils.close(pstmt);
		}
		return result;
	}
	/*
	 * 删除视频
	 */
	public void deleteVideoType(VideoType type){
		 Connection conn=DBUtils.getConn();
	    	String sql="delete from t_videoType where type_id=?";
	    	PreparedStatement pstmt=DBUtils.getPstmt(conn, sql);
			try {
				pstmt.setInt(1, type.getType_id());
				DBUtils.getExecuteUpdate(pstmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBUtils.close(conn);
				DBUtils.close(pstmt);
			}
	}
	/*
	 * 根据ID号查询
	 */
	public VideoType findVideoTypeById(int id){
		VideoType type=null;
		Connection conn=DBUtils.getConn();
		PreparedStatement pstmt=DBUtils.getPstmt(conn, "select * from t_videoType where type_id=?");
		try {
			pstmt.setInt(1, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs=DBUtils.getRs(pstmt);
		try {
			if(rs.next()){
				type=new VideoType();
				type.setType_id(id);
				type.setType_name(rs.getString(2));
				type.setType_createtime(rs.getTimestamp(3));
				type.setType_updatetime(rs.getTimestamp(4));
				type.setType_desc(rs.getString(5));
				type.setType_creater(rs.getInt(6));
				type.setType_updater(rs.getInt(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			type=null;
		}
		return type;
	}
	/*
	 * 分页查询
	 */
	public List<VideoType> searchVideoTypePage(int page,int pageSize){
		List<VideoType> list=new ArrayList<VideoType>();
		VideoType type=null;
		Connection conn=DBUtils.getConn();
		String sql="select * from t_videoType limit " + (page-1) * pageSize + "," + page*pageSize;
		PreparedStatement pstmt=DBUtils.getPstmt(conn, sql);
		ResultSet rs=DBUtils.getRs(pstmt);
		try {
			while(rs.next()){
				type=new VideoType();
				type.setType_id(rs.getInt(1));
				type.setType_name(rs.getString(2));
				type.setType_createtime(rs.getTimestamp(3));
				type.setType_updatetime(rs.getTimestamp(4));
				type.setType_desc(rs.getString(5));
				type.setType_creater(rs.getInt(6));
				type.setType_updater(rs.getInt(7));
				list.add(type);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.close(conn);
			DBUtils.close(pstmt);
		}
		return list;
	}
	
	public List<VideoType> searchVideoTypePageByKeyWord(int page,int pageSize,String KeyWord){
		List<VideoType> list=new ArrayList<VideoType>();
		VideoType type=null;
		Connection conn=DBUtils.getConn();
		String sql="select * from t_videoType where type_name like '%"+KeyWord+"%' limit " + (page-1) * pageSize + "," + page*pageSize;
		PreparedStatement pstmt=DBUtils.getPstmt(conn, sql);
		ResultSet rs=DBUtils.getRs(pstmt);
		try {
			while(rs.next()){
				type=new VideoType();
				type.setType_id(rs.getInt(1));
				type.setType_name(rs.getString(2));
				type.setType_createtime(rs.getTimestamp(3));
				type.setType_updatetime(rs.getTimestamp(4));
				type.setType_desc(rs.getString(5));
				type.setType_creater(rs.getInt(6));
				type.setType_updater(rs.getInt(7));
				list.add(type);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.close(conn);
			DBUtils.close(pstmt);
		}
		return list;
	}
	
	public int getVideoTypeCount(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from t_videoType";
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
	
	
	public int getVideoTypeCountByKeyWord(String KeyWord){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from t_videoType where type_name like '%"+KeyWord+"%'";
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
	public ArrayList<VideoType> getAllVideoType() {
		ArrayList<VideoType> list=new ArrayList<VideoType>();
		
		Connection conn=DBUtils.getConn();
		String sql="select * from t_videoType";
		PreparedStatement pstmt=DBUtils.getPstmt(conn, sql);
		ResultSet rs=DBUtils.getRs(pstmt);
		try {
			while(rs.next()){
				VideoType type=new VideoType();
				type.setType_id(rs.getInt(1));
				type.setType_name(rs.getString(2));
				type.setType_createtime(rs.getTimestamp(3));
				type.setType_updatetime(rs.getTimestamp(4));
				type.setType_desc(rs.getString(5));
				type.setType_creater(rs.getInt(6));
				type.setType_updater(rs.getInt(7));
				list.add(type);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.close(conn);
			DBUtils.close(pstmt);
		}
		return list;
	}
}
