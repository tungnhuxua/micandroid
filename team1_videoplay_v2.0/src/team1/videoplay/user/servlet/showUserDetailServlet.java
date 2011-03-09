package team1.videoplay.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.user.model.User;
import team1.videoplay.user.service.impl.UserServiceImpl;

public class showUserDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("userInfo");
		User user1 = UserServiceImpl.getInstance().getUser(user.getUser_id());
		System.out.println(user.getUser_name());
		request.setAttribute("userDetail",user1);
		request.getRequestDispatcher("/userModify.jsp").forward(request, response);
	}


}
