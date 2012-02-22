package ningbq.bean;

import java.io.Serializable;

import android.graphics.drawable.Drawable;


public class Location  implements Serializable{
	
	private static final long serialVersionUID = 2623632300826868965L;
	private int id ;
	private String locationId ;
	private String secondId ;
	private String name_en ;
	private String name_cn ;
	private String address_en ;
	private String address_cn ;
	private String telephone ;
	private Double longitude ;
	private Double latitude ;
	private Drawable locationIcon ;
	
	public Location(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getSecondId() {
		return secondId;
	}

	public void setSecondId(String secondId) {
		this.secondId = secondId;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	public String getName_cn() {
		return name_cn;
	}

	public void setName_cn(String name_cn) {
		this.name_cn = name_cn;
	}

	public String getAddress_en() {
		return address_en;
	}

	public void setAddress_en(String address_en) {
		this.address_en = address_en;
	}

	public String getAddress_cn() {
		return address_cn;
	}

	public void setAddress_cn(String address_cn) {
		this.address_cn = address_cn;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Drawable getLocationIcon() {
		return locationIcon;
	}

	public void setLocationIcon(Drawable locationIcon) {
		this.locationIcon = locationIcon;
	}
	

	

}
