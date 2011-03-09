package team1.videoplay.favourite.factory;


import team1.videoplay.favourite.dao.FavouriteDao;


public class FavouriteFactory {
	 public static FavouriteDao getInstance(String name){
		    FavouriteDao fd=null;
	    	 try {
				fd=(FavouriteDao)Class.forName(name).newInstance();
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
	    	return fd;
	    }
}
