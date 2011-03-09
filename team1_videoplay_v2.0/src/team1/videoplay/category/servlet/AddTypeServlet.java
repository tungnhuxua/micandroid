package team1.videoplay.category.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.category.model.VideoType;
import team1.videoplay.category.service.VideoTypeService;
import team1.videoplay.category.service.impl.VideoTypeServiceImpl;
import team1.videoplay.utils.MyException;

public class AddTypeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type_name = request.getParameter("type_name");
		String type_desc = request.getParameter("type_desc");
		VideoType videoType = new VideoType();
		videoType.setType_name(type_name);
		videoType.setType_desc(type_desc);
		VideoTypeService videoTypeService = VideoTypeServiceImpl.getInstance();
		try {
			videoTypeService.addVideoType(videoType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("manage/typeManage.jsp");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
