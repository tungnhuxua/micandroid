package team1.videoplay.category.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.category.service.VideoTypeService;
import team1.videoplay.category.service.impl.VideoTypeServiceImpl;
import team1.videoplay.favourite.model.Favourite;
import team1.videoplay.favourite.service.FavouriteService;
import team1.videoplay.favourite.service.impl.FavouriteServiceImpl;
import team1.videoplay.playrecord.model.PlayRecord;
import team1.videoplay.playrecord.service.PlayRecordService;
import team1.videoplay.playrecord.service.impl.PlayRecordServiceImpl;
import team1.videoplay.review.model.Review;
import team1.videoplay.review.service.ReviewService;
import team1.videoplay.review.service.impl.ReviewServiceImpl;
import team1.videoplay.utils.FenYe;
import team1.videoplay.video.model.Video;
import team1.videoplay.video.service.VideoService;
import team1.videoplay.video.service.impl.VideoServiceImpl;

public class DeleteTypeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int typeID = Integer.parseInt(request.getParameter("typeID"));
		VideoTypeService videoTypeService = VideoTypeServiceImpl.getInstance();
		VideoService videoService = VideoServiceImpl.getInstance();
		FavouriteService favouriteService = FavouriteServiceImpl.getInstance();
		PlayRecordService playRecordService = PlayRecordServiceImpl.getInstance();
		ReviewService reviewService = ReviewServiceImpl.getInstance();
		try {
			FenYe allVideo = videoService.getVideoByVideoTypeID(typeID, 0);
			ArrayList<Video> list = (ArrayList<Video>)allVideo.getList();
			for(Video video:list){
				ArrayList<Favourite> favoriteList = favouriteService.getAllFavouriteByVideoID(video.getVideo_id());
				for(Favourite favourite:favoriteList){
					favouriteService.deleteFavourite(favourite.getFavourite_id());
				}
				ArrayList<PlayRecord> playRecordList = playRecordService.getAllPlayRecordByVideoID(video.getVideo_id());
				for(PlayRecord playRecord:playRecordList){
					playRecordService.deletePlayRecord(playRecord.getPlay_id());
				}
				ArrayList<Review> reviewList = reviewService.getAllReviewByVideoID(video.getVideo_id());
				for(Review review:reviewList){
					reviewService.deleteReview(review.getReview_id());
				}
				videoService.deleteVideo(video);
			}
			videoTypeService.deleteVideoType(videoTypeService.findVideoTypeById(typeID));
		} catch (Exception e) {
		}
		response.sendRedirect("manage/typeManage.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
