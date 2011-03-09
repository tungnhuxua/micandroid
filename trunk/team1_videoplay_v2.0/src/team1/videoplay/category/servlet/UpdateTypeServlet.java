package team1.videoplay.category.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.category.model.VideoType;
import team1.videoplay.category.service.VideoTypeService;
import team1.videoplay.category.service.impl.VideoTypeServiceImpl;

public class UpdateTypeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int typeID = Integer.parseInt(request.getParameter("typeID"));
		String type_name = request.getParameter("type_name");
		String type_desc = request.getParameter("type_desc");
		VideoTypeService videoTypeService = VideoTypeServiceImpl.getInstance();
		VideoType videoType = null;
		try {
			 videoType = videoTypeService.findVideoTypeById(typeID);
			 videoType.setType_name(type_name);
			 videoType.setType_desc(type_desc);
			 videoTypeService.updateVideoType(videoType);
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
