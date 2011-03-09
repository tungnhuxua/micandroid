package team1.videoplay.review.model;

import java.util.Date;

public class Review {
	private int review_id;            
	private int user_id;
	private int video_id;
	private Date review_time;
	private String review_content;
	private int review_score;
	
	
	public int getReview_id() {
		return review_id;
	}
	
	public void setReview_id(int reviewId) {
		review_id = reviewId;
	}
	
	
	public int getUser_id() {
		return user_id;
	}
	
	public void setUser_id(int userId) {
		user_id = userId;
	}
	
	
	public int getVideo_id() {
		return video_id;
	}
	
	public void setVideo_id(int videoId) {
		video_id = videoId;
	}
	
	
	public Date getReview_time() {
		return review_time;
	}
	
	public void setReview_time(Date reviewTime) {
		review_time = reviewTime;
	}
	
	
	public String getReview_content() {
		return review_content;
	}
	
	public void setReview_content(String reviewContent) {
		review_content = reviewContent;
	}
	
	
	public int getReview_score() {
		return review_score;
	}
	
	public void setReview_score(int reviewScore) {
		review_score = reviewScore;
	}
	  
}
