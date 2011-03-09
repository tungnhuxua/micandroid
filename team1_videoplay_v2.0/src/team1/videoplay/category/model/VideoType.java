package team1.videoplay.category.model;



import java.util.Date;

public class VideoType {
	   private int type_id;             
	   private String type_name;            
	   private Date type_createtime;     
	   private Date type_updatetime;     
	   private String type_desc;          
	   private int type_creater;        
	   private int type_updater;
	   public VideoType(){
		   
	   }
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int typeId) {
		type_id = typeId;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String typeName) {
		type_name = typeName;
	}
	public Date getType_createtime() {
		return type_createtime;
	}
	public void setType_createtime(Date typeCreatetime) {
		type_createtime = typeCreatetime;
	}
	public Date getType_updatetime() {
		return type_updatetime;
	}
	public void setType_updatetime(Date typeUpdatetime) {
		type_updatetime = typeUpdatetime;
	}
	public String getType_desc() {
		return type_desc;
	}
	public void setType_desc(String typeDesc) {
		type_desc = typeDesc;
	}
	public int getType_creater() {
		return type_creater;
	}
	public void setType_creater(int typeCreater) {
		type_creater = typeCreater;
	}
	public int getType_updater() {
		return type_updater;
	}
	public void setType_updater(int typeUpdater) {
		type_updater = typeUpdater;
	}
	   
}
