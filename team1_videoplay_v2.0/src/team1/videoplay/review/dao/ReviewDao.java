package team1.videoplay.review.dao;

import java.util.ArrayList;

import team1.videoplay.review.model.Review;


public interface ReviewDao {
	public int addReview(Review review);
	public int deleteReview(int review_id);
	public ArrayList<Review> getReviewByVideoID(int videoID);
	public Review getReview(int review_id);
	public int getReviewCountByVideoID(int videoID);
	//¡¡
	public ArrayList<Review> getAllReviewByVideoID(int videoID) ;
	
	//¡¡
	public ArrayList<Review> getAllReviewByUserID(int userID);
}
