package team1.videoplay.category.service.impl;

import java.util.ArrayList;
import java.util.Properties;
import team1.videoplay.category.dao.VideoTypeDao;
import team1.videoplay.category.factory.VideoTypeDaoFactory;
import team1.videoplay.category.model.VideoType;
import team1.videoplay.category.service.VideoTypeService;
import team1.videoplay.utils.FenYe;
import team1.videoplay.utils.PropUtils;

public class VideoTypeServiceImpl implements VideoTypeService{
    private static VideoTypeDao vtd;
    private VideoTypeServiceImpl(){}
    private static VideoTypeService vts=new VideoTypeServiceImpl();
    public static VideoTypeService getInstance(){
    	return vts;
    }
    static{
    	Properties prop = PropUtils.loadProp(VideoTypeService.class, "/file.properties");//读取文件
		String name = prop.getProperty("videotypedaoname");				//得到文件里的appealdaoname
		vtd= VideoTypeDaoFactory.getInstance(name);						//通过工厂得到一个AppealDao对象
    }
	public int addVideoType(VideoType type){
		
		return vtd.addVideoType(type);
	}
	public void deleteVideoType(VideoType type){
		// TODO Auto-generated method stub
		vtd.deleteVideoType(type);
	}
	public VideoType findVideoTypeById(int id){
		// TODO Auto-generated method stub
		return vtd.findVideoTypeById(id);
	}
	public FenYe searchVideoTypePage(int page){
		// TODO Auto-generated method stub
		FenYe fenye = new FenYe();
		fenye.setPage(page);
		int typeCount = vtd.getVideoTypeCount();//dao里面新加入查找所有记录数的方法
		int pageSize = FenYe.pageSize;//pageSize是在FenYe类里定义的常量
		fenye.setPagecount((int)Math.ceil((double)typeCount/pageSize));//得到总页数
		fenye.setList(vtd.searchVideoTypePage(page, pageSize));
		return fenye;
	}
	
	public FenYe searchVideoTypePageByKeyWord(int page,String KeyWord){
		// TODO Auto-generated method stub
		FenYe fenye = new FenYe();
		fenye.setPage(page);
		int typeCount = vtd.getVideoTypeCountByKeyWord(KeyWord);//dao里面新加入查找所有记录数的方法
		int pageSize = FenYe.pageSize;//pageSize是在FenYe类里定义的常量
		fenye.setPagecount((int)Math.ceil((double)typeCount/pageSize));//得到总页数
		fenye.setList(vtd.searchVideoTypePageByKeyWord(page, pageSize, KeyWord));
		return fenye;
	}

	public int updateVideoType(VideoType type){
		// TODO Auto-generated method stub
		return vtd.updateVideoType(type);
	}
	public ArrayList<VideoType> getAllVideoType() {
		return vtd.getAllVideoType();
	}

    
}
