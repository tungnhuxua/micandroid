package team1.videoplay.video.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.category.model.VideoType;
import team1.videoplay.category.service.VideoTypeService;
import team1.videoplay.category.service.impl.VideoTypeServiceImpl;
import team1.videoplay.user.model.User;
import team1.videoplay.user.service.UserService;
import team1.videoplay.user.service.impl.UserServiceImpl;
import team1.videoplay.video.model.Video;
import team1.videoplay.video.service.VideoService;
import team1.videoplay.video.service.impl.VideoServiceImpl;

public class CheckVideoAgoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int videoID = Integer.parseInt(request.getParameter("videoID"));
		VideoService videoService = VideoServiceImpl.getInstance();
		VideoTypeService videoTypeService = VideoTypeServiceImpl.getInstance();
		UserService userService = UserServiceImpl.getInstance();
		Video video = videoService.findVideoID(videoID);
		VideoType videoType =null;
		try {
			 videoType = videoTypeService.findVideoTypeById(video.getType_id());
		} catch (Exception e) {
			e.printStackTrace();
		}
		User user = userService.getUser(video.getUser_id());
		request.setAttribute("video", video);
		request.setAttribute("videoType", videoType);
		request.setAttribute("user", user);
		request.getRequestDispatcher("manage/videoCheck.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doGet(request, response);
	}

}
