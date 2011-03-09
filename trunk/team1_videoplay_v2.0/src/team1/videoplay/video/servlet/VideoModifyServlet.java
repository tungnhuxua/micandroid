package team1.videoplay.video.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.video.model.Video;
import team1.videoplay.video.service.impl.VideoServiceImpl;

public class VideoModifyServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int videoID = Integer.parseInt(request.getParameter("videoID"));
		Video video = VideoServiceImpl.getInstance().findVideoID(videoID);
		request.setAttribute("video", video);
		request.getRequestDispatcher("videoUpdate.jsp").forward(request, response);
	}

}
