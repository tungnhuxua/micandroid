package team1.videoplay.review.service;

import java.util.ArrayList;

import team1.videoplay.review.model.Review;

public interface ReviewService {
	public int addReview(Review review);
	public int deleteReview(int review_id);
	public Review getReview(int review_id);
	public int getReviewCountByvideoID(int videoID);
	public ArrayList<Review> getReviewByVideoID(int videoID);
	//¡¡
	public ArrayList<Review> getAllReviewByVideoID(int videoID) ;
	
	//¡¡
	public ArrayList<Review> getAllReviewByUserID(int userID);
}
