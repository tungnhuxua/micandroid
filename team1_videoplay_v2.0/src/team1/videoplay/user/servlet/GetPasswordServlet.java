package team1.videoplay.user.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.user.model.User;
import team1.videoplay.user.service.impl.UserServiceImpl;

public class GetPasswordServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String email = request.getParameter("email");
			String question = request.getParameter("question");
			String answer = request.getParameter("answer");
			User user = UserServiceImpl.getInstance().getUserByEmail(email);
			if(user!=null){
				if(question.equals(user.getQuestion())&&answer.equals(user.getAnswer())){
					request.setAttribute("result", "成功找回密码，密码已经发送到您的邮箱里~");
				}else {
					request.setAttribute("result", "邮箱错误，请重新填写邮箱地址以及回答问题~");
				}
			}else {
				request.setAttribute("result", "邮箱错误，请重新填写邮箱地址以及回答问题~");
			}
			request.setAttribute("title", "找回密码");
			request.getRequestDispatcher("result.jsp").forward(request, response);
	}
}
