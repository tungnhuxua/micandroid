package team1.videoplay.video.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.utils.FenYe;
import team1.videoplay.video.service.impl.VideoServiceImpl;

public class VideoListServlet extends HttpServlet {
 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int videoTypeID  = Integer.parseInt(request.getParameter("videoTypeID"));
		FenYe fenye = VideoServiceImpl.getInstance().getVideoByVideoTypeID(videoTypeID, page);
		request.setAttribute("fenYe", fenye);
		request.setAttribute("videoTypeID", videoTypeID);
		request.getRequestDispatcher("videoList.jsp").forward(request, response);
	}
}
