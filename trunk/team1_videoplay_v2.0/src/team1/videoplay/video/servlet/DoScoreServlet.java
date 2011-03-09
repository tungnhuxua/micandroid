package team1.videoplay.video.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.review.model.Review;
import team1.videoplay.review.service.impl.ReviewServiceImpl;
import team1.videoplay.user.model.User;
import team1.videoplay.user.service.impl.UserServiceImpl;
import team1.videoplay.video.model.Video;
import team1.videoplay.video.service.impl.VideoServiceImpl;

public class DoScoreServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int videoID	= Integer.parseInt(request.getParameter("videoID"));
		User user = (User)request.getSession().getAttribute("userInfo");
		String reviewContent = request.getParameter("content");
		int score = Integer.parseInt(request.getParameter("reviewScore"));
		Video video = VideoServiceImpl.getInstance().findVideoID(videoID);
		long point = video.getVideo_point();
		long scorePoint = point+score;
		video.setVideo_point(scorePoint);
		VideoServiceImpl.getInstance().updateVideo(video);
		Review review = new Review();
		review.setUser_id(user.getUser_id());
		review.setVideo_id(videoID);
		review.setReview_content(reviewContent);
		review.setReview_score(score);
		ReviewServiceImpl.getInstance().addReview(review);
		
		ArrayList<Review> reviews = ReviewServiceImpl.getInstance().getAllReviewByVideoID(videoID);
		ArrayList<User> userList = new ArrayList<User>();
		for(Review review1:reviews){
			User user1 = UserServiceImpl.getInstance().getUser(review.getUser_id());
			userList.add(user1);
		}
		Video video1 = VideoServiceImpl.getInstance().findVideoID(videoID);
		request.setAttribute("reviews", reviews);
		request.setAttribute("userList", userList);
		request.setAttribute("videourl", video1.getVideo_url());
		request.setAttribute("videoID", videoID);
		request.getRequestDispatcher("videoPlay.jsp").forward(request, response);
	}
}
