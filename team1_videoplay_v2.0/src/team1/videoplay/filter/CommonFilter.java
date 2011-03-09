package team1.videoplay.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.category.service.impl.VideoTypeServiceImpl;
import team1.videoplay.playrecord.model.PlayRecord;
import team1.videoplay.playrecord.service.impl.PlayRecordServiceImpl;
import team1.videoplay.user.model.User;
import team1.videoplay.utils.FenYe;
import team1.videoplay.video.model.Video;
import team1.videoplay.video.service.impl.VideoServiceImpl;

public class CommonFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		request.setCharacterEncoding("utf-8") ;
		response.setCharacterEncoding("utf-8");
		
		String requestPath = request.getServletPath();
		
		
		if(requestPath.contains("/manage/")&& request.getSession().getAttribute("loginInfo")==null&&!requestPath.contains("/login.jsp")) {
			response.sendRedirect("../login.jsp");
			return;
		}
		
		if(!requestPath.contains("/manage/")) {
			
			ArrayList<Video> list1 = VideoServiceImpl.getInstance().getVideoOrderByPlaycount();
			ArrayList<Video> list2 = VideoServiceImpl.getInstance().getVideoOrderByPoint();
			ArrayList<Video> list3 = VideoServiceImpl.getInstance().getVideoOrderByTime();
			FenYe videoTypeList = VideoTypeServiceImpl.getInstance().searchVideoTypePage(1);
			
			request.setAttribute("hotVideo", list1);//»»√≈ ”∆µ
			request.setAttribute("commendVideo", list2);
			request.setAttribute("newVideo", list3);
			request.setAttribute("videoType", videoTypeList);
		}
		if(requestPath.contains("/index.jsp")){
			ArrayList<PlayRecord> playRecord = new ArrayList<PlayRecord>();
			ArrayList<Video> videoRecord= new ArrayList<Video>();
			User user = (User)request.getSession().getAttribute("userInfo");
			if(user!=null){
				playRecord = PlayRecordServiceImpl.getInstance().getPlayRecordByUserID(user.getUser_id());
			}

			for(PlayRecord record:playRecord){
				Video video = VideoServiceImpl.getInstance().findVideoID(record.getVideo_id());
				videoRecord.add(video);
			}
			request.setAttribute("videoRecord", videoRecord);
		}
		
	
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
