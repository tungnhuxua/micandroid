package team1.videoplay.payuser.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.payuser.service.impl.PayUserServiceImpl;
import team1.videoplay.user.model.User;
import team1.videoplay.user.service.impl.UserServiceImpl;

public class PayUserResult extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			int userId = Integer.parseInt(request.getParameter("userId"));
			int payUserId = Integer.parseInt(request.getParameter("payUserId"));
			float supplyMoney = Float.parseFloat(request.getParameter("supplyMoney"));
			float applyMoney = Float.parseFloat(request.getParameter("applyMoney"));
			User user = UserServiceImpl.getInstance().getUser(userId);
			float money = user.getUser_money()-applyMoney;
			user.setUser_money(money);
			UserServiceImpl.getInstance().userMoneyModify(money, userId);
			PayUserServiceImpl.getInstance().updatePayUser(supplyMoney, payUserId);
			response.sendRedirect("manage/payRequest.jsp");
	}
}
