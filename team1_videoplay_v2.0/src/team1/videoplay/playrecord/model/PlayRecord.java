package team1.videoplay.playrecord.model;

import java.util.Date;








public class PlayRecord {
	private int play_id;
	private int video_id;
	private int user_id;
	private Date play_time;
	
	
	public int getPlay_id() {
		return play_id;
	}
	public void setPlay_id(int playId) {
		play_id = playId;
	}
	public int getVideo_id() {
		return video_id;
	}
	public void setVideo_id(int videoId) {
		video_id = videoId;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int userId) {
		user_id = userId;
	}
	public Date getPlay_time() {
		return play_time;
	}
	public void setPlay_time(Date playTime) {
		play_time = playTime;
	}
	
	
  }
