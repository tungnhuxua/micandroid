package ningbq.bean ;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;

import android.location.Location;

/**
 * 
 * 		用于存放XML文件里分析出来的地址数据
 * 		的专用数据结构,包含地址,坐标,(其他...)
 *
 */
public final class Addresx 
{
	private String LongAddress = null;
	private double lat = -0.0;
	private double lng = -0.0;
	private String Country = null;
	private String City = null;
	private String Road = null;
	private long PostCode = 0L;
	
	public Addresx ()
	{
	}
	
	public Addresx (Location location)
	{
		this.lat = location.getLatitude();
		this.lng = location.getLongitude();
	}
	
	public Addresx (GeoPoint point)
	{
		this.lat = (double)point.getLatitudeE6() / 1E6;
		this.lng = (double)point.getLongitudeE6() / 1E6;
	}
	
	public Addresx (double lat, double lng)
	{
		this.lat = lat;
		this.lng = lng;
	}
	
	public Addresx (String longAddress)
	{
		this.LongAddress = longAddress;
	}
	
	public Addresx (String country, String city, String road, long postCode)
	{
		this.Country = country;
		this.City = city;
		this.Road = road;
		this.PostCode = postCode;
	}
	
	/**
	 * 		静态方法, 
	 * 		将Addresx List转变为GeoPoint List返回
	 */
	public static List<GeoPoint> convertToGeoPointList (List<Addresx> addressList)
	{
		if (addressList == null)
			return null;
		List<GeoPoint> list = new ArrayList<GeoPoint> ();
		for (Addresx tAddr : addressList)
		{
			list.add(tAddr.getGeoPoint());
		}
		return list;
	}
	
	/**
	 * 		将LongAddress格式化
	 */
	public void formatAddress ()
	{
		StringBuilder addres = new StringBuilder ();
		addres.append(this.Road).append(",");
		addres.append(this.City).append(",");
		addres.append(this.Country);
		this.LongAddress = addres.toString();
	}
	
	/**
	 * 		将类内数据(经纬度)创建为GeoPoint对象返回
	 * @return
	 */
	public GeoPoint getGeoPoint ()
	{
		int iLat = (int)(this.lat * 1E6);
		int iLng = (int)(this.lng * 1E6);
		return new GeoPoint (iLat, iLng);
	}
	
	/**
	 * 		覆写方法,判断当坐标一致,
	 * 		或者地址全称一致时,说明两个地址相等
	 */
	@Override
	public boolean equals(Object o) 
	{
		Addresx obj = (Addresx)o;
		if (this.lat == obj.getLat() && this.lng == obj.getLng())
		{
			return true;
		}
		else if (obj.getLongAddress() != null 
				&& obj.getLongAddress().equals(this.LongAddress))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 		覆写了Object的toString方法
	 * 		用于将本类中的数据,组织成一个有效的字符串
	 * 
	 */
	@Override
	public String toString() 
	{
		return "lat: "+this.lat+", lng: "+
			this.lng+"\nAddress:"+this.LongAddress;
	}

	public String getLongAddress() {
		return LongAddress;
	}

	public void setLongAddress(String longAddress) {
		LongAddress = longAddress;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getRoad() {
		return Road;
	}

	public void setRoad(String road) {
		Road = road;
	}

	public long getPostCode() {
		return PostCode;
	}

	public void setPostCode(long postCode) {
		PostCode = postCode;
	}
	

}
