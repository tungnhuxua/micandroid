package ningbq.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable{
	
	
	private String id ;
	private String name_en ;
	private String name_cn ;
	private String address_en ;
	private String address_cn ;
	private String telephone ;
	private Double longitude ;
	private Double latitude ;
	
	public Location(){
		
	}
	
	public Location(Parcel source){
		this.id = source.readString() ;
		this.name_en = source.readString() ;
		this.name_cn = source.readString() ;
		this.address_en = source.readString() ;
		this.address_cn = source.readString() ;
		this.telephone = source.readString() ;
		this.longitude = source.readDouble() ;
		this.latitude = source.readDouble() ;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id) ;
		dest.writeString(name_en) ;
		dest.writeString(name_cn) ;
		dest.writeString(address_en) ;
		dest.writeString(address_cn) ;
		dest.writeString(telephone) ;
		dest.writeDouble(longitude) ;
		dest.writeDouble(latitude) ;
	}
	
	public final static Parcelable.Creator<Location> CREATOR = new Creator<Location>() {
		
		@Override
		public Location[] newArray(int size) {
			return new Location[size];
			//throw new UnsupportedOperationException();
		}
		
		@Override
		public Location createFromParcel(Parcel source) {
			return new Location(source) ;
		}
	};
	

}
