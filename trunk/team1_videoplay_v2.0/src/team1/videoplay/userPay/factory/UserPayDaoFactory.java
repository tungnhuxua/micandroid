package team1.videoplay.userPay.factory;

import team1.videoplay.userPay.dao.UserPayDao;

public class UserPayDaoFactory {
	public static UserPayDao getInstance(String name){
		UserPayDao upd=null;
		try {
			upd = (UserPayDao)Class.forName(name).newInstance();

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
		return upd;
	}
}
