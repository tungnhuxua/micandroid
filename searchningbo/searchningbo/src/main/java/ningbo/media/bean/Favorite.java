package ningbo.media.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "favorite")
@XmlRootElement
public class Favorite implements Serializable{

	private static final long serialVersionUID = 5891170734899084091L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Expose
	private Integer id ;
	
	@Expose
	private Integer userId ;
	
	@Expose
	private Integer locationId ;
	
	@Expose
	private String locationName ;
	
	public Favorite(){}
	
	public Favorite(boolean flag){
		flag  = false ;
	}

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
