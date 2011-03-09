package team1.videoplay.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.user.model.User;
import team1.videoplay.user.service.impl.UserServiceImpl;

public class BuyScoreServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		long buyScore = Integer.parseInt(request.getParameter("buyStore"));
		User user = (User)request.getSession().getAttribute("userInfo");
		long allPoint = buyScore+user.getUser_point();
		float money1 = user.getUser_money();
		float money = money1-buyScore;
		UserServiceImpl.getInstance().userMoneyModify(money, user.getUser_id());
		UserServiceImpl.getInstance().userScoreModify(allPoint, user.getUser_id());
		
		User user1 = UserServiceImpl.getInstance().getUser(user.getUser_id());
		request.getSession().removeAttribute("userInfo");
		request.getSession().setAttribute("userInfo", user1);
		request.setAttribute("result", "购买成功，欢迎下次再来...");
		request.setAttribute("title", "积分购买");
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}
}
