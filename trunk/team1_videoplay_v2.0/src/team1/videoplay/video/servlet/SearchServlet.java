package team1.videoplay.video.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.utils.FenYe;
import team1.videoplay.video.model.Video;
import team1.videoplay.video.service.impl.VideoServiceImpl;

public class SearchServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		String keyWords = request.getParameter("search");
		String []keyword = new String[keyWords.length()];

		for(int i=0;i<keyWords.length();i++){
			keyword[i]=keyWords.substring(i, i+1);
		}
		FenYe searchResult = VideoServiceImpl.getInstance().getSearchVideo(page, keyword);
		request.setAttribute("fenYe", searchResult);
		request.getRequestDispatcher("searchResult.jsp").forward(request, response);
		
		
	}
}
