package team1.videoplay.video.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.playrecord.model.PlayRecord;
import team1.videoplay.playrecord.service.impl.PlayRecordServiceImpl;
import team1.videoplay.review.model.Review;
import team1.videoplay.review.service.impl.ReviewServiceImpl;
import team1.videoplay.user.model.User;
import team1.videoplay.user.service.impl.UserServiceImpl;
import team1.videoplay.video.model.Video;
import team1.videoplay.video.service.impl.VideoServiceImpl;

public class VideoPlayServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int videoID = Integer.parseInt(request.getParameter("videoID"));
		float videoPlayRequirdMoney = Float.parseFloat(request.getParameter("videoPlayRequirdMoney"));
		if(request.getSession().getAttribute("userInfo")!=null){
			User user = (User)request.getSession().getAttribute("userInfo");
			if(videoPlayRequirdMoney>0){
				float money1=user.getUser_money();
				float money = money1-videoPlayRequirdMoney;
				UserServiceImpl.getInstance().userMoneyModify(money, user.getUser_id());
				request.getSession().removeAttribute("userInfo");
				request.getSession().setAttribute("userInfo", UserServiceImpl.getInstance().getUser(user.getUser_id()));
			}
			PlayRecord record = new PlayRecord();
			record.setUser_id(user.getUser_id());
			record.setVideo_id(videoID);
			PlayRecordServiceImpl.getInstance().addPlayRecord(record);
		}
		
		ArrayList<Review> reviews = ReviewServiceImpl.getInstance().getAllReviewByVideoID(videoID);
		ArrayList<User> userList = new ArrayList<User>();
		for(Review review:reviews){
			User user = UserServiceImpl.getInstance().getUser(review.getUser_id());
			userList.add(user);
		}
		Video video = VideoServiceImpl.getInstance().findVideoID(videoID);
		long point = video.getVideo_point();
		int playCount = video.getVideo_playcount();
		point = point + 20;
		playCount = playCount + 1;
		video.setVideo_point(point);
		video.setVideo_playcount(playCount);
		VideoServiceImpl.getInstance().updateVideo(video);
		request.setAttribute("reviews", reviews);
		request.setAttribute("userList", userList);
		request.setAttribute("videourl", video.getVideo_url());
		request.setAttribute("videoID", videoID);
		request.getRequestDispatcher("videoPlay.jsp").forward(request, response);
	}


}

