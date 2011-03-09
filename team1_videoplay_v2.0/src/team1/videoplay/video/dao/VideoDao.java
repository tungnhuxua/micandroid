package team1.videoplay.video.dao;



import java.util.ArrayList;
import java.util.List;

import team1.videoplay.video.model.Video;

public interface VideoDao {
	public int addVideo(Video video);
	public int updateVideo(Video video);
	public void deleteVideo(Video video);
	public Video findVideoID(int id);
	public List<Video> searchVideoPage(int page,int pageSize);
	public int getVideoCount();
	public List<Video> searchVideoPageByKeyword(int page,int pageSize,String keyword);
	public int getVideoCountByKeyword(String keyword);
	public List<Video> searchCheckVideoPage(int page,int pageSize);
	public int getCheckVideoCount();
	public List<Video> searchCheckVideoPageByKeyword(int page,int pageSize,String keyword);
	public int getCheckVideoCountByKeyword(String keyword);
	 
	public ArrayList<Video> getVideoOrderByTime();//最新上传
	public ArrayList<Video> getVideoOrderByPlaycount();//最热门视频
	public ArrayList<Video> getVideoOrderByPoint();//今日推荐
	public ArrayList<Video> getVideoByVideoTypeID(int videoTypeID,int page,int pageSize);
	public int getVideoCountByTypeID(int typeid);
	//public ArrayList<Video> getVideoRecord(int userID);
	//public ArrayList<Video> getTodayVideo();
	//亮
	public ArrayList<Video> getVideoByUserID(int userID,int page,int pageSize) ;
	public int getUserIDByVideoID(int videoID);
	public int getVideoCountByUserID(int userID);
	public ArrayList<Video> getVideoByUserID1(int userID);
	public ArrayList<Video> getSearchVideo(int page,int pageSize,String[]keywords);
	public int getVideoCountByKeyWords(String[]keywords);
}
