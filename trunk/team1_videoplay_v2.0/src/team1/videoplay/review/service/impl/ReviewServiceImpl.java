package team1.videoplay.review.service.impl;

import java.util.ArrayList;
import java.util.Properties;

import team1.videoplay.review.dao.ReviewDao;
import team1.videoplay.review.factory.ReviewDaoFactory;
import team1.videoplay.review.model.Review;
import team1.videoplay.review.service.ReviewService;
import team1.videoplay.utils.PropUtils;

public class ReviewServiceImpl implements ReviewService {
	private static ReviewDao rd;
	private ReviewServiceImpl(){};
	private static ReviewService rs=new ReviewServiceImpl();
	public static ReviewService getInstance(){
		return rs;
	}
	static {
		Properties prop = PropUtils.loadProp(ReviewService.class, "/file.properties");//读取文件
		String name = prop.getProperty("reviewdaoname");				//得到文件里的PayUserdaoname
		rd = ReviewDaoFactory.getInstance(name);						//通过工厂得到一个AppealDao对象
	}
	public int addReview(Review review) {
		return rd.addReview(review);
	}

	public int deleteReview(int review_id) {
		return rd.deleteReview(review_id);
	}


	public Review getReview(int review_id) {
		return rd.getReview(review_id);
	}
	public ArrayList<Review> getReviewByVideoID(int videoID) {
		
		return rd.getReviewByVideoID(videoID);
	}

	public int getReviewCountByvideoID(int videoID) {
		// TODO Auto-generated method stub
		return rd.getReviewCountByVideoID(videoID);
	}
	//亮
	public ArrayList<Review> getAllReviewByVideoID(int videoID) {
		return rd.getAllReviewByVideoID(videoID);
	}
	
	//亮
	public ArrayList<Review> getAllReviewByUserID(int userID){
		return rd.getAllReviewByUserID(userID);
	}

}
