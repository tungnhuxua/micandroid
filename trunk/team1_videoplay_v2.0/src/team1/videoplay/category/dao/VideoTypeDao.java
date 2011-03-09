package team1.videoplay.category.dao;



import java.util.ArrayList;
import java.util.List;

import team1.videoplay.category.model.VideoType;
public interface VideoTypeDao {
	public int addVideoType(VideoType type);
	public int updateVideoType(VideoType type);
	public void deleteVideoType(VideoType type);
	public VideoType findVideoTypeById(int id);
	public int getVideoTypeCount();
	public List<VideoType> searchVideoTypePage(int page,int pageSize);
	public List<VideoType> searchVideoTypePageByKeyWord(int page,int pageSize,String KeyWord);
	public int getVideoTypeCountByKeyWord(String KeyWord);
	public ArrayList<VideoType> getAllVideoType();
}
