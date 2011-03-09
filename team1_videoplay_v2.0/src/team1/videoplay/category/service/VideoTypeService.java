package team1.videoplay.category.service;
import java.util.ArrayList;

import team1.videoplay.category.model.VideoType;
import team1.videoplay.utils.FenYe;
import team1.videoplay.utils.MyException;

public interface VideoTypeService {
	public int addVideoType(VideoType type) throws MyException;
	public int updateVideoType(VideoType type) throws MyException;
	public void deleteVideoType(VideoType type)  throws MyException;
	public VideoType findVideoTypeById(int id) throws MyException;
	public  FenYe searchVideoTypePage(int page);
	public FenYe searchVideoTypePageByKeyWord(int page,String KeyWord);
	public ArrayList<VideoType> getAllVideoType();
}
