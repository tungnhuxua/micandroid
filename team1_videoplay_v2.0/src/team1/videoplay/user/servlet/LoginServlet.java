package team1.videoplay.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.exception.PasswordIsErrorException;
import team1.videoplay.exception.UserNotFoundException;
import team1.videoplay.user.model.User;
import team1.videoplay.user.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		response.setHeader("Pragma", "no-cache");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		username = URLDecoder.decode(username,"UTF-8");
		String password = request.getParameter("password");
		try{
			User user = UserServiceImpl.getInstance().Login(username, password);
			if(user.getUserStatus()==1){
				out.print("4");//被冻结
			}else {
				request.getSession().setAttribute("userInfo",user);
				out.print("3");//成功
			}
		
		}catch(UserNotFoundException e){
			out.print("1");
		}catch(PasswordIsErrorException e){
			out.print("2");
		}
	}

}
