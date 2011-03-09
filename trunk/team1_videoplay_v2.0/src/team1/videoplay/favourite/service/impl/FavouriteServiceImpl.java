package team1.videoplay.favourite.service.impl;

import java.util.ArrayList;
import java.util.Properties;

import team1.videoplay.favourite.dao.FavouriteDao;
import team1.videoplay.favourite.factory.FavouriteFactory;
import team1.videoplay.favourite.model.Favourite;
import team1.videoplay.favourite.service.FavouriteService;
import team1.videoplay.utils.FenYe;
import team1.videoplay.utils.PropUtils;

public class FavouriteServiceImpl implements FavouriteService{
	 private static FavouriteDao fd;
	    private FavouriteServiceImpl(){}
	    private static FavouriteService fs=new FavouriteServiceImpl();
	    public static FavouriteService getInstance(){
	    	return fs;
	    }
	    static{
	    	Properties prop = PropUtils.loadProp(FavouriteService.class, "/file.properties");//读取文件
			String name = prop.getProperty("favouritedaoname");				//得到文件里的appealdaoname
			fd= FavouriteFactory.getInstance(name);						//通过工厂得到一个AppealDao对象
	    }
		public int addFavourite(Favourite favourite) {
			// TODO Auto-generated method stub
			return fd.addFavourite(favourite);
		}
		public int deleteFavourite(int favourateID) {
			// TODO Auto-generated method stub
			return fd.deleteFavourite(favourateID);
		}
		public FenYe getAllFavourite(int page) {
			// TODO Auto-generated method stub
			FenYe fenye = new FenYe();
			fenye.setPage(page);
			int favouriteCount = fd.getFavouriteCount();//dao里面新加入查找所有记录数的方法
			int pageSize = FenYe.pageSize;//pageSize是在FenYe类里定义的常量
			fenye.setPagecount((int)Math.ceil((float)favouriteCount/pageSize));//得到总页数
			fenye.setList(fd.getAllFavourite(page, pageSize));
			return fenye;
		}
		public int getFavouriteCount() {
			// TODO Auto-generated method stub
			return fd.getFavouriteCount();
		}
		//亮
		public ArrayList<Favourite> getAllFavouriteByVideoID(int videoID){
			return fd.getAllFavouriteByVideoID(videoID);
		}
		
		//亮
		public ArrayList<Favourite> getAllFavouriteByUserID(int userID){
			return fd.getAllFavouriteByUserID(userID);
		}
}
