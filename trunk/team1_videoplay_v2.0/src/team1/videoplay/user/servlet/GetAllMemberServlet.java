package team1.videoplay.user.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.user.model.User;
import team1.videoplay.user.service.impl.UserServiceImpl;
import team1.videoplay.utils.FenYe;

public class GetAllMemberServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		FenYe fenye = UserServiceImpl.getInstance().getAllUser(page);
		request.setAttribute("fenYe", fenye);
		request.getRequestDispatcher("manage/memberManage.jsp").forward(request, response);
	}
}
