package team1.videoplay.video.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.video.model.Video;
import team1.videoplay.video.service.impl.VideoServiceImpl;

public class VideoUpdateServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String desc = request.getParameter("desc");
		int videoID = Integer.parseInt(request.getParameter("videoID"));
		Video video = VideoServiceImpl.getInstance().findVideoID(videoID);
		video.setVideo_title(title);
		video.setVideo_desc(desc);
		VideoServiceImpl.getInstance().updateVideo(video);
		request.setAttribute("result", "ÐÞ¸Ä³É¹¦!");
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}
}
