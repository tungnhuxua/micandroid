package team1.videoplay.video.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.user.model.User;
import team1.videoplay.user.service.impl.UserServiceImpl;
import team1.videoplay.video.model.Video;
import team1.videoplay.video.service.VideoService;
import team1.videoplay.video.service.impl.VideoServiceImpl;

public class AuditingVideoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int videoID = Integer.parseInt(request.getParameter("videoID"));
		String order = request.getParameter("order");
		VideoService videoService = VideoServiceImpl.getInstance();
		Video video = videoService.findVideoID(videoID);
		if("yes".equals(order)){
			video.setVideo_checkstate(1);

			int userID = VideoServiceImpl.getInstance().getUserIDByVideoID(videoID);
	
			User user = UserServiceImpl.getInstance().getUser(userID);
			long userPoint = user.getUser_point()+10;
			UserServiceImpl.getInstance().userScoreModify(userPoint, userID);
		}else if("no".equals(order))
		video.setVideo_checkstate(2);
		videoService.updateVideo(video);
		response.sendRedirect("manage/videoCheckList.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
this.doGet(request, response);
	}

}
