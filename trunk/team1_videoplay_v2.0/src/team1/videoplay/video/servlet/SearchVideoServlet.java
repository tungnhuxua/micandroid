package team1.videoplay.video.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.category.model.VideoType;
import team1.videoplay.category.service.VideoTypeService;
import team1.videoplay.category.service.impl.VideoTypeServiceImpl;
import team1.videoplay.user.model.User;
import team1.videoplay.user.service.UserService;
import team1.videoplay.user.service.impl.UserServiceImpl;
import team1.videoplay.utils.FenYe;
import team1.videoplay.video.model.Video;
import team1.videoplay.video.service.VideoService;
import team1.videoplay.video.service.impl.VideoServiceImpl;

public class SearchVideoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		String keyword = request.getParameter("keyword");
		VideoService videoService = VideoServiceImpl.getInstance();
		VideoTypeService videoTypeService = VideoTypeServiceImpl.getInstance();
		UserService userService = UserServiceImpl.getInstance();
		FenYe allVideo = videoService.searchVideoPageByKeyword(page, keyword);
		ArrayList<Video> list = (ArrayList<Video>)allVideo.getList();
		ArrayList<User> userList = new ArrayList<User>();
		ArrayList<VideoType> videoTypeList = new ArrayList<VideoType>();
		for(Video video : list){
			int videoTypeID = video.getType_id();
			int userID = video.getUser_id();
			try {
				videoTypeList.add(videoTypeService.findVideoTypeById(videoTypeID));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			userList.add(userService.getUser(userID));
			
		}
		request.setAttribute("allVideo", allVideo);
		request.setAttribute("videoTypeList", videoTypeList);
		request.setAttribute("userList", userList);
		request.setAttribute("flag", 1);
		request.setAttribute("keyword", keyword);
		request.getRequestDispatcher("manage/manageIndex.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
