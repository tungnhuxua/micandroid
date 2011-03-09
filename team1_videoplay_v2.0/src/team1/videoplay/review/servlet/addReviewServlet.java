package team1.videoplay.review.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.review.model.Review;
import team1.videoplay.review.service.impl.ReviewServiceImpl;
import team1.videoplay.user.model.User;

public class addReviewServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int videoID = Integer.parseInt(request.getParameter("videoID"));
		User user = (User)request.getSession().getAttribute("userInfo");
		String reviewContent = request.getParameter("reviewContent");
		int reviewScore  = Integer.parseInt(request.getParameter("reviewScore"));
		
		Review review = new Review();
		review.setReview_content(reviewContent);
		review.setReview_score(reviewScore);
		review.setReview_time(new Date());
		review.setUser_id(user.getUser_id());
		review.setVideo_id(videoID);
		
		ReviewServiceImpl.getInstance().addReview(review);
	//	ArrayList<Review> list = ReviewServiceImpl.getInstance().getReviewByVideoID(videoID);
	//	request.setAttribute("videoID", videoID);
	//	request.setAttribute("reviewList",list);
		response.sendRedirect("videoPlayServlet.do?videoID="+videoID);
	}

}
