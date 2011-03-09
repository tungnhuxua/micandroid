package team1.videoplay.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.user.model.User;
import team1.videoplay.user.service.impl.UserServiceImpl;

public class UserStatusModifyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userID = Integer.parseInt(request.getParameter("id"));
		
		int tag = Integer.parseInt(request.getParameter("tag"));
		User user = UserServiceImpl.getInstance().getUser(userID);
		if(tag==0){
			//¶³½á
			user.setUserStatus(1);
			UserServiceImpl.getInstance().updateUserStatus(user);
		}else {
			user.setUserStatus(0);
			UserServiceImpl.getInstance().updateUserStatus(user);
		}
		response.sendRedirect("manage/memberManage.jsp");
	}

}
