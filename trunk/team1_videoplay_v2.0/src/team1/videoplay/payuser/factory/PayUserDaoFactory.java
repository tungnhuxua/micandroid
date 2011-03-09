package team1.videoplay.payuser.factory;

import team1.videoplay.payuser.dao.PayUserDao;

public class PayUserDaoFactory {
	public static PayUserDao getInstance(String name){
		PayUserDao pd=null;
		try {
			pd = (PayUserDao)Class.forName(name).newInstance();

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
		return pd;
	}
}
