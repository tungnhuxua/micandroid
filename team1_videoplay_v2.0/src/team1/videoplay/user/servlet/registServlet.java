package team1.videoplay.user.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.user.model.User;
import team1.videoplay.user.service.impl.UserServiceImpl;

public class registServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// …Ë÷√“≥√Ê≤ªª∫¥Ê
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		response.setHeader("Pragma", "no-cache");
		response.setCharacterEncoding("UTF-8");

		String username = request.getParameter("username");
		username = URLDecoder.decode(username,"UTF-8");
		String password = request.getParameter("password");
		String account = request.getParameter("account");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String question = request.getParameter("question");	
		question = URLDecoder.decode(question,"UTF-8");
		String answer = request.getParameter("answer");	
		answer = URLDecoder.decode(answer,"UTF-8");
		User user= new User();	
		user.setUser_name(username);
		user.setUser_password(password);
		user.setUser_account(account);
		user.setUser_email(email);
		user.setUser_telephone(telephone);
		user.setQuestion(question);
		user.setAnswer(answer);
		UserServiceImpl.getInstance().addUser(user);
		
	}

}
