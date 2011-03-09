package team1.videoplay.review.dao.impl;

import org.junit.Test;

import team1.videoplay.review.dao.impl.ReviewDao4MySqlImpl;
import team1.videoplay.review.model.Review;



public class ReviewDao4MySqlImplTest {
	@Test
	public void testDeletReview(){
		Review r = new Review();
		r.setReview_content("ssssss");
		new ReviewDao4MySqlImpl().deleteReview(1);
	}
	@Test
	public void testGetAllReview(){
		Review r = new Review();
		r.setReview_content("ssssss");
		new ReviewDao4MySqlImpl().getAllReview(2, 3);
	}
	@Test
	public void testGetReview(){
		Review r = new Review();
		r.setReview_content("ssssss");
		new ReviewDao4MySqlImpl().getReview(2);
	}
	
	@Test
	public void testAddReview(){
		Review r = new Review();
		r.setReview_content("ssssss");
		new ReviewDao4MySqlImpl().addReview(r);
	}

}
