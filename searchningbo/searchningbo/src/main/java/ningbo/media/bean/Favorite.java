package ningbo.media.bean;

import java.io.Serializable;

public class Favorite implements Serializable{

	private static final long serialVersionUID = 5891170734899084091L;
	
	private Integer id ;
	
	private Integer userId ;
	
	private Integer locationId ;
	
	private String locationName ;
	
	public Favorite(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	

}
