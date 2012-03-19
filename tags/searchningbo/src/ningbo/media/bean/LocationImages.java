package ningbo.media.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "location_images")
@XmlRootElement
public class LocationImages implements Serializable {

	private static final long serialVersionUID = 8784589002443197429L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer id;

	@Expose
	private String ori_image;

	@Expose
	private String zoom_image;

	@Expose
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id")
	private Location location;
	
	public LocationImages() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOri_image() {
		return ori_image;
	}

	public void setOri_image(String ori_image) {
		this.ori_image = ori_image;
	}

	public String getZoom_image() {
		return zoom_image;
	}

	public void setZoom_image(String zoom_image) {
		this.zoom_image = zoom_image;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String toJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this, LocationImages.class);
		return json;
	}

	public static LocationImages fromJson(String jsonLocation) {
		Gson gson = new Gson();
		LocationImages flocation = gson.fromJson(jsonLocation,
				LocationImages.class);
		return flocation;
	}

}
