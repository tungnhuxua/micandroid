package team1.videoplay.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.user.model.User;
import team1.videoplay.user.service.impl.UserServiceImpl;

public class UserModifyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String account = request.getParameter("account");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		
		User user = (User)request.getSession().getAttribute("userInfo");
		user.setAnswer(answer);
		user.setQuestion(question);
		user.setUser_account(account);
		user.setUser_email(email);
		user.setUser_password(password);
		user.setUser_telephone(telephone);
		user.setUser_name(username);
		
		UserServiceImpl.getInstance().updateUser(user);
		
		request.setAttribute("result", "用户资料修改成功~");
		request.getRequestDispatcher("result.jsp").forward(request, response);
	
	}
}
