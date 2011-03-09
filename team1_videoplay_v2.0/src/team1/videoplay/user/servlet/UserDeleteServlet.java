package team1.videoplay.user.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.videoplay.favourite.model.Favourite;
import team1.videoplay.favourite.service.FavouriteService;
import team1.videoplay.favourite.service.impl.FavouriteServiceImpl;
import team1.videoplay.payuser.model.PayUser;
import team1.videoplay.payuser.service.PayUserService;
import team1.videoplay.payuser.service.impl.PayUserServiceImpl;
import team1.videoplay.playrecord.model.PlayRecord;
import team1.videoplay.playrecord.service.PlayRecordService;
import team1.videoplay.playrecord.service.impl.PlayRecordServiceImpl;
import team1.videoplay.review.model.Review;
import team1.videoplay.review.service.ReviewService;
import team1.videoplay.review.service.impl.ReviewServiceImpl;
import team1.videoplay.user.service.impl.UserServiceImpl;
import team1.videoplay.userPay.model.UserPay;
import team1.videoplay.userPay.service.UserPayService;
import team1.videoplay.userPay.service.impl.UserPayServiceImpl;
import team1.videoplay.video.model.Video;
import team1.videoplay.video.service.VideoService;
import team1.videoplay.video.service.impl.VideoServiceImpl;

public class UserDeleteServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userID = Integer.parseInt(request.getParameter("id"));
		UserPayService userPayService = UserPayServiceImpl.getInstance();
		PayUserService payUserService = PayUserServiceImpl.getInstance();
		ReviewService reviewService = ReviewServiceImpl.getInstance();
		PlayRecordService playRecordService = PlayRecordServiceImpl.getInstance();
		FavouriteService favouriteService = FavouriteServiceImpl.getInstance();
		VideoService videoService = VideoServiceImpl.getInstance();
		
		
		ArrayList<UserPay> userPayList = userPayService.getAllUserPayByUserID(userID);
		for(UserPay userPay:userPayList){
			userPayService.deleteUserPay(userPay.getUserpay_id());
		}
		
		ArrayList<PayUser> payUserList = payUserService.getAllPayUserByUserID(userID);
		for(PayUser payUser:payUserList){
			payUserService.deletePayUser(payUser.getPayuserId());
		}
		
		ArrayList<Review> reviewList = reviewService.getAllReviewByUserID(userID);
		for(Review review:reviewList){
			reviewService.deleteReview(review.getReview_id());
		}
	
		ArrayList<PlayRecord> playRecordList = playRecordService.getAllPlayRecordByUserID(userID);
		for(PlayRecord playRecord:playRecordList){
			playRecordService.deletePlayRecord(playRecord.getPlay_id());
		}
		ArrayList<Favourite> favouriteList = favouriteService.getAllFavouriteByUserID(userID);
		for(Favourite favourite:favouriteList){
			favouriteService.deleteFavourite(favourite.getFavourite_id());
		}
		ArrayList<Video> videoList = videoService.getVideoByUserID1(userID);
		if(videoList.size()!=0){
		for(Video video:videoList){
			ArrayList<Favourite> favoriteList = favouriteService.getAllFavouriteByVideoID(video.getVideo_id());
			for(Favourite favourite:favoriteList){
				favouriteService.deleteFavourite(favourite.getFavourite_id());
			}
			ArrayList<PlayRecord> playRecordList1 = playRecordService.getAllPlayRecordByVideoID(video.getVideo_id());
			for(PlayRecord playRecord:playRecordList1){
				playRecordService.deletePlayRecord(playRecord.getPlay_id());
			}
			ArrayList<Review> reviewList1 = reviewService.getAllReviewByVideoID(video.getVideo_id());
			for(Review review:reviewList1){
				reviewService.deleteReview(review.getReview_id());
				}
			videoService.deleteVideo(video);
		}}
		UserServiceImpl.getInstance().deleteUser(userID);
		response.sendRedirect("manage/memberManage.jsp");
	
}}
