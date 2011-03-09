package team1.videoplay.userPay.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.user.model.User;
import team1.videoplay.user.service.impl.UserServiceImpl;

public class UserPayServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		float payCount = Float.parseFloat(request.getParameter("payCount"));
		User user = (User)request.getSession().getAttribute("userInfo");
		float money = user.getUser_money();
		float allMoney = payCount+money;
		UserServiceImpl.getInstance().userMoneyModify(allMoney, user.getUser_id());
		User user1 = UserServiceImpl.getInstance().getUser(user.getUser_id());
		request.getSession().removeAttribute("userInfo");
		request.getSession().setAttribute("userInfo", user1);
		request.setAttribute("result", "充值成功，希望阁下再接再厉踊跃去充值...");
		request.setAttribute("title", "用户充值");
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

}
