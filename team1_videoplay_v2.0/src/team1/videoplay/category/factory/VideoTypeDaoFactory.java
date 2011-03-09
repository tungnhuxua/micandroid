package team1.videoplay.category.factory;

import team1.videoplay.category.dao.VideoTypeDao;

public class VideoTypeDaoFactory {
    public static VideoTypeDao getInstance(String name){
    	VideoTypeDao vtd=null;
    	 try {
			vtd=(VideoTypeDao)Class.forName(name).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return vtd;
    }
}
