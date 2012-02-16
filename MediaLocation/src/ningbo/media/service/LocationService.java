package ningbo.media.service;

import ningbo.media.util.UserSettings;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

/**
 * Description:定位的后台服务,随应用的开启而启动,设置一个监听器,监听位置的改变,并且每隔一段时间记录当前位置坐标.
 * 
 * @author Devon.Ning
 * @2012-2-15
 * 
 */
public class LocationService extends Service {

	// 定义一个广播的Action字符串
	public static final String LOCATION_CHANGE = "LOCATION_CHANGE";

	private LocationManager manager = null;
	private Location currLocate = null;
	private String iProvider = null;
	// 记录得到的地址位置列表数据
	// private List<Addresx> AddressList = null;
	private boolean isRunning = false;
	// for bind with activity
	private final Binder binder = new ServiceBinder();

	// 通过绑定binder,得到当前Service对象
	public class ServiceBinder extends Binder {
		public LocationService getService() {
			return LocationService.this;
		}
	}

	/**
	 * Service创建时初始化类内的成员变量
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		Object obj = getSystemService(Context.LOCATION_SERVICE);
		this.manager = (LocationManager) obj;
		Criteria criteria = UserSettings.getDefaultCriteriaSetting();
		this.iProvider = this.manager.getBestProvider(criteria, true);
		// this.AddressList = new ArrayList<Addresx>();
	}

	/**
	 * 在Service启动时获取用户设置, 得到最合适的位置提供器
	 */
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);

		this.isRunning = true;
		long minTime = 3000L;
		float minDistance = 10F;
		if (intent != null) {
			minTime = intent.getLongExtra("minTime", 3000L);
			minDistance = intent.getFloatExtra("minDistance", 10F);
		}
		LocationChangeListener listener = new LocationChangeListener();
		this.manager.requestLocationUpdates(this.iProvider, minTime,
				minDistance, listener);

	}

	@Override
	public void onDestroy() {
		this.isRunning = false;
		super.onDestroy();
	}

	/** 监听位置改变的监听器 */
	class LocationChangeListener implements LocationListener {
		/** 在位置改变时会回调本函数,将位置的改变广播出去 */
		@Override
		public void onLocationChanged(Location location) {
			currLocate = location;
			// 将数据广播出去
			Intent intent = new Intent(LOCATION_CHANGE);
			intent.putExtra("lat", location.getLatitude());
			intent.putExtra("lng", location.getLongitude());
			sendBroadcast(intent);
			// addNewLocation();
		}

		@Override
		public void onProviderDisabled(String provider) {
			currLocate = null;
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}

	/*
	 * private void addNewLocation() { Addresx address = new
	 * Addresx(this.currLocate); this.AddressList.add(address); }
	 * 
	 * public List<Addresx> getAddressList() { return this.AddressList; }
	 */

	/**
	 * 绑定时返回binder对象
	 */
	@Override
	public IBinder onBind(Intent intent) {
		return this.binder;
	}

	public boolean isRunning() {
		return this.isRunning;
	}

	public Location getNowLocation() {
		return this.currLocate;
	}

}
