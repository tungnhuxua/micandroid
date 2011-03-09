package team1.videoplay.video.factory;


import team1.videoplay.video.dao.VideoDao;

public class VideoDaoFactory {
	public static VideoDao getInstance(String name){
    	VideoDao vd=null;
    	 try {
			vd=(VideoDao)Class.forName(name).newInstance();
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
    	return vd;
    }
}
