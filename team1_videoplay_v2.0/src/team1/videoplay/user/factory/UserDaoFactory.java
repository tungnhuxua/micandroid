package team1.videoplay.user.factory;

import team1.videoplay.user.dao.UserDao;



public class UserDaoFactory {

	
	public static UserDao getInstance(String name){
		UserDao ad=null;
		try {
			ad = (UserDao)Class.forName(name).newInstance();

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
		return ad;
	}
	
	
	
	
}
