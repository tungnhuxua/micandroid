package team1.videoplay.review.factory;

import team1.videoplay.review.dao.ReviewDao;

public class ReviewDaoFactory {
	public static ReviewDao getInstance(String name){
		ReviewDao rd=null;
		try {
			rd = (ReviewDao)Class.forName(name).newInstance();

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
		return rd;
	}
}
