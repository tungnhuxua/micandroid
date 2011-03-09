package team1.videoplay.video.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.category.model.VideoType;
import team1.videoplay.category.service.impl.VideoTypeServiceImpl;

public class TypeQuery extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ArrayList<VideoType> list = VideoTypeServiceImpl.getInstance().getAllVideoType();
		StringBuffer str = new StringBuffer("[");
		for(Iterator<VideoType> i = list.iterator();i.hasNext(); ){
			VideoType type = i.next();
			str.append("{\"id\":\"").append(type.getType_id()).append("\",\"typename\":\"").append(type.getType_name()).append("\"},");
		}
		str.deleteCharAt(str.lastIndexOf(","));
		str.append("]");
		out.print(str);
	}
}
