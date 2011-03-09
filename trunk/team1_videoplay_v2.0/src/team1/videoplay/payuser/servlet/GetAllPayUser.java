package team1.videoplay.payuser.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.payuser.model.PayUser;
import team1.videoplay.payuser.service.PayUserService;
import team1.videoplay.payuser.service.impl.PayUserServiceImpl;
import team1.videoplay.user.model.User;
import team1.videoplay.user.service.UserService;
import team1.videoplay.user.service.impl.UserServiceImpl;
import team1.videoplay.utils.FenYe;

public class GetAllPayUser extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		PayUserService payUserService = PayUserServiceImpl.getInstance();
		UserService userService = UserServiceImpl.getInstance();
		FenYe fenYe = payUserService.getAllPayUser(page);
		ArrayList<PayUser> list = (ArrayList<PayUser>)fenYe.getList();
		 ArrayList<User> userList = new ArrayList<User>();
		for(PayUser payUser:list){
			int userID = payUser.getUserId();
			User userPayInfo = userService.getUser(userID);
			userList.add(userPayInfo);
		}
		request.setAttribute("userList", userList);
		request.setAttribute("fenYe", fenYe);
		request.getRequestDispatcher("manage/payRequest.jsp").forward(request, response);
	
	}

	
}
