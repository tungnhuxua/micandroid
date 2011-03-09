package team1.videoplay.video.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.playrecord.model.PlayRecord;
import team1.videoplay.playrecord.service.impl.PlayRecordServiceImpl;
import team1.videoplay.review.model.Review;
import team1.videoplay.review.service.impl.ReviewServiceImpl;
import team1.videoplay.video.model.Video;
import team1.videoplay.video.service.impl.VideoServiceImpl;

public class VideoDeleteServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int videoID = Integer.parseInt(request.getParameter("videoID"));//¼ÇÂ¼  ÆÀÂÛ
		Video video = VideoServiceImpl.getInstance().findVideoID(videoID);
		ArrayList<Review> reviewList =ReviewServiceImpl.getInstance().getReviewByVideoID(videoID);
		ArrayList<PlayRecord> recordList = PlayRecordServiceImpl.getInstance().getAllPlayRecordByVideoID(videoID);
		if(recordList!=null){
			for(PlayRecord p:recordList){
				PlayRecordServiceImpl.getInstance().deletePlayRecord(p.getPlay_id());
			}
		}
		if(reviewList!=null){
			for(Review p:reviewList){
				ReviewServiceImpl.getInstance().deleteReview(p.getReview_id());
			}
		}
		
		VideoServiceImpl.getInstance().deleteVideo(video);
		
		response.sendRedirect("myVideoManage.jsp");
	}
}
