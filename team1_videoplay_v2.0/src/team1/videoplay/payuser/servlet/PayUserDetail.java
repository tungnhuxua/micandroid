package team1.videoplay.payuser.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.payuser.model.PayUser;
import team1.videoplay.payuser.service.impl.PayUserServiceImpl;
import team1.videoplay.user.model.User;
import team1.videoplay.user.service.impl.UserServiceImpl;

public class PayUserDetail extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			int payUserId = Integer.parseInt(request.getParameter("id"));
			PayUser payUser = PayUserServiceImpl.getInstance().getPayUser(payUserId);
			User user = UserServiceImpl.getInstance().getUser(payUser.getUserId());
			request.setAttribute("payUser", payUser);
			request.setAttribute("user", user);
			request.getRequestDispatcher("manage/paying.jsp").forward(request, response);
	}
}
