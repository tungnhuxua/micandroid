package team1.videoplay.video.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.user.model.User;
import team1.videoplay.utils.FenYe;
import team1.videoplay.video.service.impl.VideoServiceImpl;

public class MyVideoManageServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		User user = (User)request.getSession().getAttribute("userInfo");
		FenYe fenye = VideoServiceImpl.getInstance().getVideoByUserID(page, user.getUser_id());
		request.setAttribute("fenYe", fenye);
		request.getRequestDispatcher("myVideoManage.jsp").forward(request, response);
	}
}
