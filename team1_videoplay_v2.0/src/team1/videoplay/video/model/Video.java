package team1.videoplay.video.model;



import java.util.Date;

public class Video {
	   private int video_id;            
	   private int type_id;           
	   private int user_id;          
	   private String video_title;         
	   private String video_desc;           
	   private long video_point;         
	   private int video_playcount;     
	   private Date video_uploadtime ;   
	   private Date video_lastplaytime;
	   private String video_url;            
	   private int video_size;           
	   private String video_keywords;
	   //0未审核， 1为审核通过，2为审核未通过
	   private int video_checkstate;
	   private float video_money;   
	   private String video_pictureUrl;
	   private float video_requirdMoney;
	   
	public float getVideo_requirdMoney() {
		return video_requirdMoney;
	}
	public void setVideo_requirdMoney(float videoRequirdMoney) {
		video_requirdMoney = videoRequirdMoney;
	}
	public String getVideo_pictureUrl() {
		return video_pictureUrl;
	}
	public void setVideo_pictureUrl(String videoPictureUrl) {
		video_pictureUrl = videoPictureUrl;
	}
	public int getVideo_id() {
		return video_id;
	}
	public void setVideo_id(int videoId) {
		video_id = videoId;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int typeId) {
		type_id = typeId;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int userId) {
		user_id = userId;
	}
	public String getVideo_title() {
		return video_title;
	}
	public void setVideo_title(String videoTitle) {
		video_title = videoTitle;
	}
	public String getVideo_desc() {
		return video_desc;
	}
	public void setVideo_desc(String videoDesc) {
		video_desc = videoDesc;
	}
	public long getVideo_point() {
		return video_point;
	}
	public void setVideo_point(long videoPoint) {
		video_point = videoPoint;
	}
	public int getVideo_playcount() {
		return video_playcount;
	}
	public void setVideo_playcount(int videoPlaycount) {
		video_playcount = videoPlaycount;
	}
	public Date getVideo_uploadtime() {
		return video_uploadtime;
	}
	public void setVideo_uploadtime(Date videoUploadtime) {
		video_uploadtime = videoUploadtime;
	}
	public Date getVideo_lastplaytime() {
		return video_lastplaytime;
	}
	public void setVideo_lastplaytime(Date videoLastplaytime) {
		video_lastplaytime = videoLastplaytime;
	}
	public String getVideo_url() {
		return video_url;
	}
	public void setVideo_url(String videoUrl) {
		video_url = videoUrl;
	}
	public int getVideo_size() {
		return video_size;
	}
	public void setVideo_size(int videoSize) {
		video_size = videoSize;
	}
	public String getVideo_keywords() {
		return video_keywords;
	}
	public void setVideo_keywords(String videoKeywords) {
		video_keywords = videoKeywords;
	}
	public int getVideo_checkstate() {
		return video_checkstate;
	}
	public void setVideo_checkstate(int videoCheckstate) {
		video_checkstate = videoCheckstate;
	}
	public float getVideo_money() {
		return video_money;
	}
	public void setVideo_money(float videoMoney) {
		video_money = videoMoney;
	}


	
}
